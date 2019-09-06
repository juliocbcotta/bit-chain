package br.com.bit.chain.charts.data.repository

import br.com.bit.chain.charts.data.models.ChartData
import br.com.bit.chain.charts.data.models.ChartDataValue
import br.com.bit.chain.charts.data.repository.cache.ChartDataLocalCache
import br.com.bit.chain.charts.data.repository.services.ChartDataRemoteService
import br.com.bit.chain.charts.data.repository.models.ChartDataResponse
import br.com.bit.chain.charts.data.repository.models.ChartDataValueResponse
import io.reactivex.Observable
import javax.inject.Inject

interface ChartRepository {
    /**
     * This method returns data from any available source (local and remote).
     * If data from each source is equals between emissions, only
     * one emission is seem be downstream observer.
     * */
    fun getChartData(): Observable<ChartData>
}

class ChartRepositoryImpl @Inject constructor(
    private val cache: ChartDataLocalCache,
    private val service: ChartDataRemoteService
) :
    ChartRepository {

    override fun getChartData(): Observable<ChartData> {
        // Remote updates downstream and local cache.
        // NOTE: This call is cached in this method to avoid unnecessary remote calls.
        val remote = service.fetchChart()
            .flatMap { response ->
                cache.save(response)
                    .toSingleDefault(response)
            }.cache()

        // Local cache defaults to remote if nothing is found.
        val localOrRemote = cache.get()
            .switchIfEmpty(remote)

        return localOrRemote.mergeWith(remote)
            .distinctUntilChanged()
            .toObservable()
            .map {
                it.toChartData()
            }
    }

    private fun ChartDataResponse.toChartData() =
        ChartData(
            name = name,
            description = description,
            unit = unit,
            values = values.map {
                it.toChartDataValue()
            }
        )

    private fun ChartDataValueResponse.toChartDataValue() =
        ChartDataValue(
            x = x,
            y = y
        )
}
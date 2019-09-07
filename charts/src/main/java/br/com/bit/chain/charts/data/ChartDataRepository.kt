package br.com.bit.chain.charts.data

import br.com.bit.chain.charts.data.cache.ChartDataLocalCache
import br.com.bit.chain.charts.data.service.ChartDataService
import br.com.bit.chain.charts.domain.toChartData
import br.com.bit.chain.charts.domain.ChartRepository
import br.com.bit.chain.charts.domain.models.ChartData
import io.reactivex.Observable
import javax.inject.Inject

internal class ChartRepositoryImpl @Inject constructor(
    private val cache: ChartDataLocalCache,
    private val service: ChartDataService
) :
    ChartRepository {

    /**
     * This method returns data from any available source (local and remote).
     * If data from each source is equals between emissions, only
     * one emission is seem be downstream observer.
     * */
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
}
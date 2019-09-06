package br.com.bit.chain.charts.data.repository

import br.com.bit.chain.charts.data.models.ChartData
import br.com.bit.chain.charts.data.models.ChartDataValue
import br.com.bit.chain.charts.data.repository.services.ChartDataRemoteService
import br.com.bit.chain.charts.data.repository.services.ChartDataResponse
import br.com.bit.chain.charts.data.repository.services.ChartDataValueResponse
import io.reactivex.Single
import javax.inject.Inject

interface ChartRepository {
    fun getChartData(): Single<ChartData>
}

class ChartRepositoryImpl @Inject constructor(
    private val remoteService: ChartDataRemoteService
) :
    ChartRepository {

    override fun getChartData(): Single<ChartData> {
        return remoteService.fetchChart()
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
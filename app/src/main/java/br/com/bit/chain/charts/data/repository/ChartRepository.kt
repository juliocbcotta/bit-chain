package br.com.bit.chain.charts.data.repository

import br.com.bit.chain.charts.data.models.Chart
import br.com.bit.chain.charts.data.models.ChartValue
import br.com.bit.chain.charts.data.repository.services.ChartRemoteService
import br.com.bit.chain.charts.data.repository.services.ChartResponse
import br.com.bit.chain.charts.data.repository.services.ChartValueResponse
import io.reactivex.Single
import javax.inject.Inject

interface ChartRepository {
    fun getChart(): Single<Chart>
}

class ChartRepositoryImpl @Inject constructor(private val remoteService: ChartRemoteService) :
    ChartRepository {

    override fun getChart(): Single<Chart> {
        return remoteService.fetchChart()
            .map {
                it.toChart()
            }
    }

    private fun ChartResponse.toChart() =
        Chart(
            name = name,
            description = description,
            unit = unit,
            values = values.map {
                it.toChartValue()
            }
        )

    private fun ChartValueResponse.toChartValue() =
        ChartValue(
            x = x,
            y = y
        )
}
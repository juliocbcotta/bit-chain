package br.com.bit.chain.charts.domain

import br.com.bit.chain.charts.data.models.ChartDataResponse
import br.com.bit.chain.charts.data.models.ChartDataValueResponse
import br.com.bit.chain.charts.domain.models.ChartData
import br.com.bit.chain.charts.domain.models.ChartDataValue

internal fun ChartDataResponse.toChartData() =
    ChartData(
        name = name,
        description = description,
        unit = unit,
        values = values.map {
            it.toChartDataValue()
        }
    )

internal fun ChartDataValueResponse.toChartDataValue() =
    ChartDataValue(
        x = x,
        y = y
    )
package br.com.bit.chain.charts.presentation

import br.com.bit.chain.charts.domain.models.ChartData
import br.com.bit.chain.charts.domain.models.ChartDataValue
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartValueUiModel
import java.util.*


internal fun ChartData.toChartUiModel(): ChartUiModel {
    return ChartUiModel(
        title = name,
        subtitle = description,
        start = Date(values.first().x.toLong() * 1000),
        end = Date(values.last().x.toLong() * 1000),
        values = values.map {
            it.toChartValueUiModel()
        }
    )
}

internal fun ChartDataValue.toChartValueUiModel(): ChartValueUiModel {
    return ChartValueUiModel(
        x = x,
        y = y
    )
}
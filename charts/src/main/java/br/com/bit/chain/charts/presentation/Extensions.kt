package br.com.bit.chain.charts.presentation

import br.com.bit.chain.charts.domain.models.ChartData
import br.com.bit.chain.charts.domain.models.ChartDataValue
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartValueUiModel


fun ChartData.toChartUiModel(): ChartUiModel {
    return ChartUiModel(
        title = name,
        subtitle = description,
        values = values.map {
            it.toChartValueUiModel()
        }
    )
}

fun ChartDataValue.toChartValueUiModel(): ChartValueUiModel {
    return ChartValueUiModel(
        x = x,
        y = y
    )
}
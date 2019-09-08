package br.com.bit.chain.charts.presentation

import br.com.bit.chain.charts.domain.models.ChartData
import br.com.bit.chain.charts.domain.models.ChartDataValue
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartValueUiModel
import java.util.Date
/**
 * MULTIPLIER takes a UNIX timestamp and makes it an appropriate input for Date constructor.
 * */
private const val MULTIPLIER = 1000
internal fun ChartData.toChartUiModel(): ChartUiModel {
    return ChartUiModel(
        title = name,
        subtitle = description,
        start = Date(values.first().x.toLong() * MULTIPLIER),
        end = Date(values.last().x.toLong() * MULTIPLIER),
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

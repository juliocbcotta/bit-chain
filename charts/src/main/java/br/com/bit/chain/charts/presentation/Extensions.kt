package br.com.bit.chain.charts.presentation

import br.com.bit.chain.charts.domain.models.ChartData
import br.com.bit.chain.charts.domain.models.ChartDataValue
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartValueUiModel
import java.text.SimpleDateFormat
import java.util.*


internal fun ChartData.toChartUiModel(): ChartUiModel {
    return ChartUiModel(
        title = name,
        subtitle = description,
        valuesDescription = formatValuesDescription(values, period),
        values = values.map {
            it.toChartValueUiModel()
        }
    )
}

private fun formatValuesDescription(
    values: List<ChartDataValue>,
    period: String
): String {

    return when (period) {
        "day" -> {
            val dateFormatter = SimpleDateFormat.getDateInstance()
            val start = Date(values.first().x.toLong() * 1000)
            val end = Date(values.last().x.toLong() * 1000)
            val formattedStart = dateFormatter.format(start)
            val formattedEnd = dateFormatter.format(end)
            "from $formattedStart to $formattedEnd"
        }
        else ->
            "N/A"
    }

}

internal fun ChartDataValue.toChartValueUiModel(): ChartValueUiModel {
    return ChartValueUiModel(
        x = x,
        y = y
    )
}
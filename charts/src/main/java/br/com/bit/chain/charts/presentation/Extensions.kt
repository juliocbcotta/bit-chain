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
            val start = formatAsDate(values.first().x.toLong())
            val end = formatAsDate(values.last().x.toLong())
            "from $start to $end"
        }
        else ->
            "N/A"
    }

}

private fun formatAsDate(x: Long): String {
    val dateFormatter = SimpleDateFormat.getDateInstance()
    val date = Date(x * 1000)
    return dateFormatter.format(date)
}

internal fun ChartDataValue.toChartValueUiModel(): ChartValueUiModel {
    return ChartValueUiModel(
        x = x,
        y = y
    )
}
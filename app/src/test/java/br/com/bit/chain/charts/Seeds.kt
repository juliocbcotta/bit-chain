package br.com.bit.chain.charts

import br.com.bit.chain.charts.data.models.ChartData
import br.com.bit.chain.charts.data.models.ChartDataValue
import br.com.bit.chain.charts.data.repository.models.ChartDataResponse
import br.com.bit.chain.charts.data.repository.models.ChartDataValueResponse
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartValueUiModel

val chartDataResponse = ChartDataResponse(
    name = "name",
    unit = "unit",
    description = "description",
    values = listOf(
        ChartDataValueResponse(
            x = 1.0f,
            y = 2.0f
        )
    )
)

val chartData = ChartData(
    name = "name",
    unit = "unit",
    description = "description",
    values = listOf(
        ChartDataValue(
            x = 1.0f,
            y = 2.0f
        )
    )
)

val chartUiModel = ChartUiModel(
    title = "name",
    subtitle = "description",
    values = listOf(
        ChartValueUiModel(
            x = 1.0f,
            y = 2.0f
        )
    )
)
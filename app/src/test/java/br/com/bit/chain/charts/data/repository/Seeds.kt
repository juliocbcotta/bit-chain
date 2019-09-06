package br.com.bit.chain.charts.data.repository

import br.com.bit.chain.charts.data.models.ChartData
import br.com.bit.chain.charts.data.models.ChartDataValue
import br.com.bit.chain.charts.data.repository.services.ChartDataResponse
import br.com.bit.chain.charts.data.repository.services.ChartDataValueResponse

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
package br.com.bit.chain.charts.data.repository

import br.com.bit.chain.charts.data.models.Chart
import br.com.bit.chain.charts.data.models.ChartValue
import br.com.bit.chain.charts.data.repository.services.ChartResponse
import br.com.bit.chain.charts.data.repository.services.ChartValueResponse

val chartResponse = ChartResponse(
    name = "name",
    unit = "unit",
    description = "description",
    values = listOf(
        ChartValueResponse(
            x = 1.0f,
            y = 2.0f
        )
    )
)

val chart = Chart(
    name = "name",
    unit = "unit",
    description = "description",
    values = listOf(
        ChartValue(
            x = 1.0f,
            y = 2.0f
        )
    )
)
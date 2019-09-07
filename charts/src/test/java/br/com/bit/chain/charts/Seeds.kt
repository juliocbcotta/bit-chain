package br.com.bit.chain.charts

import br.com.bit.chain.charts.domain.models.ChartData
import br.com.bit.chain.charts.domain.models.ChartDataValue
import br.com.bit.chain.charts.data.models.ChartDataResponse
import br.com.bit.chain.charts.data.models.ChartDataValueResponse
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartValueUiModel

val chartDataResponseJson = """
                    {
                        "name": "name",
                        "description" : "description",
                        "unit" : "unit",
                        "values" :[
                                {
                                 "x" : 1.0,
                                 "y" : 2.0
                                }
                            ]
                    }
            """.trimMargin()

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
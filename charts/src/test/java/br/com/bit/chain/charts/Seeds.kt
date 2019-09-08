package br.com.bit.chain.charts

import br.com.bit.chain.charts.data.cache.models.ChartDataDao
import br.com.bit.chain.charts.data.cache.models.ChartDataValueDao
import br.com.bit.chain.charts.domain.models.ChartData
import br.com.bit.chain.charts.domain.models.ChartDataValue
import br.com.bit.chain.charts.data.service.models.ChartDataResponse
import br.com.bit.chain.charts.data.service.models.ChartDataValueResponse
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartValueUiModel
import java.util.*

val date = Calendar.getInstance().apply {
    set(Calendar.YEAR, 2019)
    set(Calendar.MONTH, 8)
    set(Calendar.DAY_OF_MONTH, 8)
}.time

val x = (date.time / 1000).toFloat()

internal val chartDataResponseJson = """
                    {
                        "name": "name",
                        "description" : "description",
                        "unit" : "unit",
                        "period" : "day",
                        "values" :[
                                {
                                 "x" : $x,
                                 "y" : 2.0
                                }
                            ]
                    }
            """.trimMargin()

internal val chartDataResponse = ChartDataResponse(
    name = "name",
    unit = "unit",
    period = "day",
    description = "description",
    values = listOf(
        ChartDataValueResponse(
            x = x,
            y = 2.0f
        )
    )
)

internal val chartDataDao = ChartDataDao(
    name = "name",
    unit = "unit",
    period = "day",
    description = "description",
    values = listOf(
        ChartDataValueDao(
            x = x,
            y = 2.0f
        )
    )
)

internal val chartData = ChartData(
    name = "name",
    unit = "unit",
    description = "description",
    period = "day",
    values = listOf(
        ChartDataValue(
            x = x,
            y = 2.0f
        )
    )
)

internal val chartUiModel = ChartUiModel(
    title = "name",
    subtitle = "description",
    valuesDescription = "from 08/09/2019 to 08/09/2019",
    values = listOf(
        ChartValueUiModel(
            x = x,
            y = 2.0f
        )
    )
)
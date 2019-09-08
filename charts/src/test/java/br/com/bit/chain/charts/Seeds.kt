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

val calendar = Calendar.getInstance().apply {
    set(Calendar.YEAR, 2019)
    set(Calendar.MONTH, 8)
    set(Calendar.DAY_OF_MONTH, 8)
    set(Calendar.HOUR, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}

val x = (calendar.time.time / 1000).toFloat()
val date = Date(x.toLong() * 1000)

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
    start = date,
    end = date,
    values = listOf(
        ChartValueUiModel(
            x = x,
            y = 2.0f
        )
    )
)
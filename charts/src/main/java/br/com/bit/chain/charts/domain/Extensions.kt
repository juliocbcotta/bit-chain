package br.com.bit.chain.charts.domain

import br.com.bit.chain.charts.data.cache.models.ChartDataDao
import br.com.bit.chain.charts.data.cache.models.ChartDataValueDao
import br.com.bit.chain.charts.data.service.models.ChartDataResponse
import br.com.bit.chain.charts.data.service.models.ChartDataValueResponse
import br.com.bit.chain.charts.domain.models.ChartData
import br.com.bit.chain.charts.domain.models.ChartDataValue

internal fun ChartDataResponse.toCharDataDao() =
    ChartDataDao(
        name = name,
        description = description,
        unit = unit,
        values = values.map {
            it.toChartDataValue()
        }
    )

internal fun ChartDataValueResponse.toChartDataValue() =
    ChartDataValueDao(
        x = x,
        y = y
    )


internal fun ChartDataDao.toChartData() =
    ChartData(
        name = name,
        description = description,
        unit = unit,
        values = values.map {
            it.toChartDataValue()
        }
    )

internal fun ChartDataValueDao.toChartDataValue() =
    ChartDataValue(
        x = x,
        y = y
    )

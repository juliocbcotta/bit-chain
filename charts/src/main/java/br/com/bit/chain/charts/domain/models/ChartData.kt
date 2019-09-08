package br.com.bit.chain.charts.domain.models

internal data class ChartData(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<ChartDataValue>
)

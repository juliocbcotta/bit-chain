package br.com.bit.chain.charts.domain.models

data class ChartData(
    val name: String,
    val unit: String,
    val description: String,
    val values: List<ChartDataValue>
)
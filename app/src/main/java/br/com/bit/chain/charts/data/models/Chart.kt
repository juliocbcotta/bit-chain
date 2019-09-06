package br.com.bit.chain.charts.data.models

data class Chart(
    val name: String,
    val unit: String,
    val description: String,
    val values: List<ChartValue>
)
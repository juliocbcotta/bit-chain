package br.com.bit.chain.charts.data.cache.models

internal data class ChartDataDao(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<ChartDataValueDao>
)
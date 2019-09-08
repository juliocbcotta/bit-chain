package br.com.bit.chain.charts.data.service.models

internal data class ChartDataResponse(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<ChartDataValueResponse>
)

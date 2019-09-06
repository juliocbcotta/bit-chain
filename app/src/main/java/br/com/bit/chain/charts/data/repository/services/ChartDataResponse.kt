package br.com.bit.chain.charts.data.repository.services

data class ChartDataResponse(
    val name: String,
    val unit: String,
    val description: String,
    val values: List<ChartDataValueResponse>
)
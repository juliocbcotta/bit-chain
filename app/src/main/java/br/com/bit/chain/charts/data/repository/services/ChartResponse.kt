package br.com.bit.chain.charts.data.repository.services

data class ChartResponse(
    val name: String,
    val unit: String,
    val description: String,
    val values: List<ChartValueResponse>
)
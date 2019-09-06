package br.com.bit.chain.components.chart

data class ChartUiModel(
    val title: String,
    val subtitle: String,
    val values: List<ChartUiValueModel>
)
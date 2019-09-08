package br.com.bit.chain.components.chart

import java.util.*

data class ChartUiModel(
    val title: String,
    val subtitle: String,
    val start : Date,
    val end : Date,
    val values: List<ChartValueUiModel>
)
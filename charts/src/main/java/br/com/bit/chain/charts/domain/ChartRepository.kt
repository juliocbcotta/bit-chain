package br.com.bit.chain.charts.domain

import br.com.bit.chain.charts.domain.models.ChartData
import io.reactivex.Observable

internal interface ChartRepository {
    fun getChartData(): Observable<ChartData>
}

package br.com.bit.chain.charts.domain

import br.com.bit.chain.charts.domain.models.ChartData
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

internal interface FetchChartUseCase {
    fun execute(): Observable<ChartData>
}

internal class FetchChartUseCaseImpl @Inject constructor(
    @Named("IOScheduler")
    private val ioScheduler: Scheduler,
    private val repository: ChartRepository) :
    FetchChartUseCase {
    override fun execute(): Observable<ChartData> {
        return repository.getChartData()
            .subscribeOn(ioScheduler)
    }
}
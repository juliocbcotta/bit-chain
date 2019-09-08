package br.com.bit.chain.charts.domain

import br.com.bit.chain.charts.chartData
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchChartUseCaseImplTest {

    @Mock
    private lateinit var repository: ChartRepository

    private val scheduler: Scheduler = Schedulers.trampoline()

    @Test
    fun `should fetch ChartData`() {
        given { repository.getChartData() }
            .willReturn(Observable.just(chartData))

        FetchChartUseCaseImpl(scheduler, repository)
            .execute()
            .test()
            .assertValue(chartData)
            .assertComplete()
    }
}

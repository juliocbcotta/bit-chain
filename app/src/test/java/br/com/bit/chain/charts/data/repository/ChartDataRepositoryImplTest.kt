package br.com.bit.chain.charts.data.repository

import br.com.bit.chain.charts.chartData
import br.com.bit.chain.charts.chartDataResponse
import br.com.bit.chain.charts.data.repository.services.ChartDataRemoteService
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class ChartDataRepositoryImplTest {

    @Mock
    lateinit var remoteService: ChartDataRemoteService

    @InjectMocks
    lateinit var repository: ChartRepositoryImpl


    @Test
    fun shouldFetchChartData() {

        given { remoteService.fetchChart() }
            .willReturn(Single.just(chartDataResponse))

        repository.getChartData()
            .test()
            .assertValue(chartData)
            .assertComplete()
    }

    @Test
    fun shouldFailToFetchChartData() {
        val exception = IOException()

        given { remoteService.fetchChart() }
            .willReturn(Single.error(exception))

        repository.getChartData()
            .test()
            .assertError(exception)

    }


}
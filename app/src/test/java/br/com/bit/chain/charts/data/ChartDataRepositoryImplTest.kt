package br.com.bit.chain.charts.data

import br.com.bit.chain.charts.chartData
import br.com.bit.chain.charts.chartDataResponse
import br.com.bit.chain.charts.data.cache.ChartDataLocalCache
import br.com.bit.chain.charts.data.service.ChartDataService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Maybe
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
    lateinit var service: ChartDataService

    @Mock
    lateinit var localCache: ChartDataLocalCache

    @InjectMocks
    lateinit var repository: ChartRepositoryImpl


    @Test
    fun `should only fetch ChartDataResponse when local cache is empty`() {
        given { localCache.get() }
            .willReturn(Maybe.empty())
        given { localCache.save(any()) }
            .willReturn(Completable.complete())

        given { service.fetchChart() }
            .willReturn(Single.just(chartDataResponse))

        repository.getChartData()
            .test()
            .assertValue(chartData)
            .assertComplete()

        verify(service).fetchChart()
    }

    @Test
    fun `should fetch local and remote ChartDataResponse when local cache is not empty and the data is different`() {
        val localResponse = chartDataResponse.copy(name = "some other name")
        val localData = chartData.copy(name = "some other name")

        given { localCache.get() }
            .willReturn(Maybe.just(localResponse))
        given { localCache.save(any()) }
            .willReturn(Completable.complete())

        given { service.fetchChart() }
            .willReturn(Single.just(chartDataResponse))

        repository.getChartData()
            .test()
            .assertValues(localData, chartData)
            .assertComplete()

        verify(service).fetchChart()
    }

    @Test
    fun `should fetch local ChartDataResponse when local cache is not empty and equals to remote`() {
        val localResponse = chartDataResponse
        val localData = chartData

        given { localCache.get() }
            .willReturn(Maybe.just(localResponse))
        given { localCache.save(any()) }
            .willReturn(Completable.complete())

        given { service.fetchChart() }
            .willReturn(Single.just(chartDataResponse))

        repository.getChartData()
            .test()
            .assertValue(localData)
            .assertComplete()

        verify(service).fetchChart()
    }

    @Test
    fun `should fail to fetch ChartDataResponse`() {
        val exception = IOException()

        given { localCache.get() }
            .willReturn(Maybe.empty())

        given { service.fetchChart() }
            .willReturn(Single.error(exception))

        repository.getChartData()
            .test()
            .assertError(exception)

        verify(service).fetchChart()

    }


}
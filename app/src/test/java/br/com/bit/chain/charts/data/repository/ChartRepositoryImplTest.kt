package br.com.bit.chain.charts.data.repository

import br.com.bit.chain.charts.data.repository.services.ChartRemoteService
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class ChartRepositoryImplTest {

    @Mock
    lateinit var remoteService: ChartRemoteService

    @InjectMocks
    lateinit var repository: ChartRepositoryImpl


    @Test
    fun shouldFetchChart() {

        given { remoteService.fetchChart() }
            .willReturn(Single.just(chartResponse))

        repository.getChart()
            .test()
            .assertValue(chart)
            .assertComplete()
    }

    @Test
    fun shouldFailToFetchChart() {
        val exception = IOException()

        given { remoteService.fetchChart() }
            .willReturn(Single.error(exception))

        repository.getChart()
            .test()
            .assertError(exception)

    }


}
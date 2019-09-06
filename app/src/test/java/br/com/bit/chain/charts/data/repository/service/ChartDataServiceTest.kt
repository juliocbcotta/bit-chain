package br.com.bit.chain.charts.data.repository.service

import br.com.bit.chain.charts.chartDataResponse
import br.com.bit.chain.charts.chartDataResponseJson
import br.com.bit.chain.charts.data.repository.services.ChartDataRemoteService
import br.com.bit.chain.networking.di.NetworkingModule
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import retrofit2.Retrofit
import okhttp3.mockwebserver.MockResponse


class ChartDataServiceTest {
    private val module = NetworkingModule()


    private fun createRetrofit(url: String): Retrofit {
        return module.provideRetrofit(
            module.provideOkHttpClient(BODY),
            module.provideGson(),
            url
        )
    }

    @Test
    fun `should execute request properly`() {
        val server = MockWebServer()
        server.enqueue(
            MockResponse().setBody(chartDataResponseJson)
        )
        server.start()
        val retrofit = createRetrofit(server.url("/").toString())

        val service = retrofit.create(ChartDataRemoteService::class.java)

        service.fetchChart()
            .test()
            .assertValue(chartDataResponse)
            .assertComplete()

        server.shutdown()
    }

}
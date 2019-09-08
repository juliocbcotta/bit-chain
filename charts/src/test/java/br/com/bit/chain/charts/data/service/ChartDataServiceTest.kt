package br.com.bit.chain.charts.data.service

import br.com.bit.chain.charts.chartDataResponse
import br.com.bit.chain.charts.chartDataResponseJson
import br.com.bit.chain.networking.di.NetworkingModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import retrofit2.Retrofit

class ChartDataServiceTest {
    private val module = NetworkingModule()

    private fun createRetrofit(url: String): Retrofit {
        return module.provideRetrofit(
            module.provideOkHttpClient(true),
            module.provideGson(),
            url
        )
    }

    @Test
    fun `should execute request properly`() {
        val server = MockWebServer()
        val response = MockResponse().setBody(chartDataResponseJson)
        server.enqueue(response)
        server.start()

        val retrofit = createRetrofit(server.url("/").toString())

        val service = retrofit.create(ChartDataService::class.java)

        service.fetchChart()
            .test()
            .assertValue(chartDataResponse)
            .assertComplete()

        server.shutdown()
    }
}

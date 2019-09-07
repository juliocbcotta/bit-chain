package br.com.bit.chain.charts.data.service

import br.com.bit.chain.charts.data.models.ChartDataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ChartDataService {
    @GET("market-price")
    fun fetchChart(): Single<ChartDataResponse>
}
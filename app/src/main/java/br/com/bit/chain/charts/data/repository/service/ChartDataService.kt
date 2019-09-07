package br.com.bit.chain.charts.data.repository.service

import br.com.bit.chain.charts.data.repository.models.ChartDataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ChartDataService {
    @GET("market-price")
    fun fetchChart(): Single<ChartDataResponse>
}
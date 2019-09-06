package br.com.bit.chain.charts.data.repository.services

import io.reactivex.Single
import retrofit2.http.GET

interface ChartDataRemoteService {
    @GET("market-price")
    fun fetchChart(): Single<ChartDataResponse>
}
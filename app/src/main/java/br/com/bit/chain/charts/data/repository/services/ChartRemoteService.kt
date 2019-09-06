package br.com.bit.chain.charts.data.repository.services

import io.reactivex.Single
import retrofit2.http.GET

interface ChartRemoteService {
    @GET("/market-price")
    fun fetchChart(): Single<ChartResponse>
}
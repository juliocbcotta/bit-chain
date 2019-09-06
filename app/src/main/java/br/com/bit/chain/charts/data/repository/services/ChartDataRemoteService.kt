package br.com.bit.chain.charts.data.repository.services

import br.com.bit.chain.charts.data.repository.models.ChartDataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ChartDataRemoteService {
    @GET("market-price")
    fun fetchChart(): Single<ChartDataResponse>
}
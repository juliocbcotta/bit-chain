package br.com.bit.chain.charts.di

import br.com.bit.chain.charts.data.repository.ChartRepository
import br.com.bit.chain.charts.data.repository.ChartRepositoryImpl
import br.com.bit.chain.charts.data.repository.services.ChartRemoteService
import br.com.bit.chain.di.ActivityScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class MainActivityModule {

    @ActivityScope
    @Provides
    fun provideRepository(impl: ChartRepositoryImpl): ChartRepository = impl

    @ActivityScope
    @Provides
    fun provideService(retrofit: Retrofit): ChartRemoteService = retrofit.create()
}
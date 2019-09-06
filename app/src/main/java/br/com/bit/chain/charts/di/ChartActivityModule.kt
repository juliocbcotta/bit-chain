package br.com.bit.chain.charts.di

import androidx.lifecycle.ViewModel
import br.com.bit.chain.app.di.ViewModelKey
import br.com.bit.chain.charts.data.repository.ChartRepository
import br.com.bit.chain.charts.data.repository.ChartRepositoryImpl
import br.com.bit.chain.charts.data.repository.services.ChartDataRemoteService
import br.com.bit.chain.charts.presentation.ChartActivityViewModel
import br.com.bit.chain.di.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.create

@Module(includes = [ViewModelModule::class])
class ChartActivityModule {

    @ActivityScope
    @Provides
    fun provideRepository(impl: ChartRepositoryImpl): ChartRepository = impl

    @ActivityScope
    @Provides
    fun provideService(retrofit: Retrofit): ChartDataRemoteService = retrofit.create()
}

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ChartActivityViewModel::class)
    abstract fun bindsViewModel(viewModel: ChartActivityViewModel): ViewModel
}
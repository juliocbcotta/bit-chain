package br.com.bit.chain.charts.di

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.bit.chain.android.di.ActivityScope
import br.com.bit.chain.android.di.ViewModelKey
import br.com.bit.chain.charts.data.ChartRepositoryImpl
import br.com.bit.chain.charts.data.cache.ChartDataLocalCache
import br.com.bit.chain.charts.data.cache.ChartDataLocalCacheImpl
import br.com.bit.chain.charts.data.service.ChartDataService
import br.com.bit.chain.charts.domain.ChartRepository
import br.com.bit.chain.charts.presentation.ChartActivityViewModel
import br.com.bit.chain.charts.presentation.State
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.create

@Module(includes = [ViewModelModule::class])
internal class ChartActivityModule {

    @ActivityScope
    @Provides
    fun provideRepository(impl: ChartRepositoryImpl): ChartRepository = impl

    @ActivityScope
    @Provides
    fun provideRemoteService(retrofit: Retrofit): ChartDataService = retrofit.create()

    @ActivityScope
    @Provides
    fun provideLocalCache(impl: ChartDataLocalCacheImpl): ChartDataLocalCache = impl

    @ActivityScope
    @Provides
    fun provideStateLiveData(): MutableLiveData<State> = MutableLiveData()

    @ActivityScope
    @Provides
    fun provideDisposables(): CompositeDisposable = CompositeDisposable()
}

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ChartActivityViewModel::class)
    abstract fun bindsViewModel(viewModel: ChartActivityViewModel): ViewModel
}
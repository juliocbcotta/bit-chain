package br.com.bit.chain.charts.di

import br.com.bit.chain.charts.presentation.ChartActivity
import br.com.bit.chain.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(
        modules = [ChartActivityModule::class]
    )
    @ActivityScope
    abstract fun bindChartActivity(): ChartActivity
}
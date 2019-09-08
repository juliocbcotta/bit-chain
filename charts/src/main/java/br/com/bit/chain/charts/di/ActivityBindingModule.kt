package br.com.bit.chain.charts.di

import br.com.bit.chain.android.di.ActivityScope
import br.com.bit.chain.charts.presentation.ChartActivity
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

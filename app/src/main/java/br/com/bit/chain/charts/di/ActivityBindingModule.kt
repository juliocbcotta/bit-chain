package br.com.bit.chain.charts.di

import br.com.bit.chain.charts.MainActivity
import br.com.bit.chain.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class
    ])
    @ActivityScope
    abstract fun bindMainActivity(): MainActivity
}
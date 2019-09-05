package br.com.bit.chain.charts.di

import br.com.bit.chain.charts.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class
    ])
    abstract fun bindMainActivity(): MainActivity
}
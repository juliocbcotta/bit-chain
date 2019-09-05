package br.com.bit.chain.charts.di

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MainActivityModule {
    @Provides
    @Named("HELLO_WORLD")
    fun provideString(): String = "Hello world"
}
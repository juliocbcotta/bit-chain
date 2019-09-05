package br.com.bit.chain.charts.di

import br.com.bit.chain.di.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MainActivityModule {

    @ActivityScope
    @Provides
    @Named("HELLO_WORLD")
    fun provideString(): String = "Hello world"
}
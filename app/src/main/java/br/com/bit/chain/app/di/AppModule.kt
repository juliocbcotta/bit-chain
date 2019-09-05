package br.com.bit.chain.app.di

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Named("BASE_URL")
    @Provides
    fun provideBaseUrl(): String {
        // TODO: Extract to build.gradle buildTypes
        return "https://api.blockchain.info/charts/"
    }
}

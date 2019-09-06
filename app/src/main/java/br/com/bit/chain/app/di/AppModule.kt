package br.com.bit.chain.app.di

import br.com.bit.chain.BuildConfig
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    @Singleton
    @Named("LOGGABLE")
    @Provides
    fun provideLoggable(): Boolean = BuildConfig.DEBUG

    @Singleton
    @Named("MainScheduler")
    @Provides
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Named("IOScheduler")
    @Provides
    fun provideIOScheduler(): Scheduler = Schedulers.io()
}

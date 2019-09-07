package br.com.bit.chain.app.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Singleton
    @Provides
    @Named("MainScheduler")
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    @Named("IOScheduler")
    fun provideIOScheduler(): Scheduler = Schedulers.io()
}
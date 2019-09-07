package br.com.bit.chain.app.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class SchedulersModule {

    @Named("MainScheduler")
    @Provides
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Named("IOScheduler")
    @Provides
    fun provideIOScheduler(): Scheduler = Schedulers.io()
}
package br.com.bit.chain.app.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class SchedulersModule {

    @Provides
    @Named("MainScheduler")
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named("IOScheduler")
    fun provideIOScheduler(): Scheduler = Schedulers.io()
}
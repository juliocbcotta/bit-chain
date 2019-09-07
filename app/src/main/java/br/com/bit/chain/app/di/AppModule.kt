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
    @Named("LOGGABLE")
    @Provides
    fun provideLoggable(): Boolean = BuildConfig.DEBUG

}

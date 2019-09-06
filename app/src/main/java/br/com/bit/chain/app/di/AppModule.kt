package br.com.bit.chain.app.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import br.com.bit.chain.BuildConfig
import br.com.bit.chain.app.BitApplication
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


    @Singleton
    @Provides
    fun provideSharedPreferences(app: BitApplication): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app)


    @Singleton
    @Named("MainScheduler")
    @Provides
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Named("IOScheduler")
    @Provides
    fun provideIOScheduler(): Scheduler = Schedulers.io()
}

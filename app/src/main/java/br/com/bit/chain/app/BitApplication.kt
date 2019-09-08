package br.com.bit.chain.app

import android.app.Application
import br.com.bit.chain.BuildConfig
import br.com.bit.chain.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BitApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjection: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjection
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .factory()
            .create(this, BuildConfig.BASE_URL)
            .inject(this)
    }
}

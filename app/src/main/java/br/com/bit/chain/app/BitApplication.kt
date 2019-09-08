package br.com.bit.chain.app

import android.app.Application
import br.com.bit.chain.app.di.AppComponent
import br.com.bit.chain.BuildConfig
import br.com.bit.chain.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BitApplication : Application(), HasAndroidInjector {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var androidInjection: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjection
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        inject()
    }

    private fun inject() {
        appComponent = DaggerAppComponent.factory()
            .create(this, BuildConfig.BASE_URL)

        appComponent.inject(this)
    }

    companion object {
        lateinit var app: BitApplication
    }
}

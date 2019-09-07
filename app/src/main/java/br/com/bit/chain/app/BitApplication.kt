package br.com.bit.chain.app

import android.app.Application
import br.com.bit.chain.app.di.AppComponent
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
        appComponent = DaggerAppComponent.factory()
            .create(this, "https://api.blockchain.info/charts/")

        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
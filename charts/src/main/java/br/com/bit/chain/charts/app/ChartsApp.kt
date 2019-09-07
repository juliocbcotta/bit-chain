package br.com.bit.chain.charts.app

import android.app.Activity
import br.com.bit.chain.app.BitApplication
import br.com.bit.chain.charts.app.di.ChartsComponent
import br.com.bit.chain.charts.app.di.DaggerChartsComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ChartsApp : HasAndroidInjector {

    var moduleComponent: ChartsComponent? = null

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> =
        androidInjector


    private fun createApp() {
        moduleComponent = DaggerChartsComponent.factory()
            .create(BitApplication.app.appComponent)

        moduleComponent?.inject(this)
    }

    companion object {

        val app = ChartsApp()

        fun inject(activity: Activity) {
            if (app.moduleComponent == null) {
                app.createApp()
            }
            app.androidInjector().inject(activity)
        }
    }
}
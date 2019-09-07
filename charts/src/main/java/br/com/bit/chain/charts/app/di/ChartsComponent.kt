package br.com.bit.chain.charts.app.di

import br.com.bit.chain.android.di.ModuleScope
import br.com.bit.chain.app.di.AppComponent
import br.com.bit.chain.app.di.BaseModule
import br.com.bit.chain.charts.app.ChartsApp
import br.com.bit.chain.charts.di.ActivityBindingModule
import dagger.Component

@ModuleScope
@Component(
    modules = [
        BaseModule::class,
        ActivityBindingModule::class
    ],
    dependencies = [AppComponent::class]
)
interface ChartsComponent {

    fun inject(app: ChartsApp)

    @Component.Factory
    interface Factory {

        fun create(
            appComponent: AppComponent
        ): ChartsComponent
    }
}

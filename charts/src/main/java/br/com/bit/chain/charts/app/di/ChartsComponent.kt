package br.com.bit.chain.charts.app.di

import br.com.bit.chain.android.di.DaggerViewModelInjectionModule
import br.com.bit.chain.android.di.ModuleScope
import br.com.bit.chain.app.di.AppComponent
import br.com.bit.chain.charts.app.ChartsApp
import br.com.bit.chain.charts.di.ActivityBindingModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@ModuleScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        DaggerViewModelInjectionModule::class,
        ActivityBindingModule::class
    ],
    dependencies = [AppComponent::class]
)
interface ChartsComponent {
    fun inject(app: ChartsApp)
    @Component.Factory
    interface Factory {
        fun appComponent(
            appComponent: AppComponent
        ): ChartsComponent
    }
}

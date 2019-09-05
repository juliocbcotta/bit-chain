package br.com.bit.chain.app.di

import br.com.bit.chain.app.BitApplication
import br.com.bit.chain.charts.di.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent {
    fun inject(app: BitApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: BitApplication): AppComponent
    }

}
package br.com.bit.chain.app.di

import br.com.bit.chain.app.BitApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(
    modules = [AndroidInjectionModule::class]
)
interface AppComponent {
    fun inject(app: BitApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: BitApplication): AppComponent
    }

}
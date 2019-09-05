package br.com.bit.chain.app.di

import br.com.bit.chain.app.BitApplication
import br.com.bit.chain.charts.di.ActivityBindingModule
import br.com.bit.chain.networking.di.NetworkingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        NetworkingModule::class
    ]
)
interface AppComponent {
    fun inject(app: BitApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: BitApplication): AppComponent
    }

}
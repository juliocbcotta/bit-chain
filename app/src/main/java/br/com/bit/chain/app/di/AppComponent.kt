package br.com.bit.chain.app.di

import br.com.bit.chain.app.BitApplication
import br.com.bit.chain.networking.di.NetworkingModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        BaseModule::class,
        AppModule::class,
        NetworkingModule::class
    ]
)
interface AppComponent {

    fun inject(app: BitApplication)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            app: BitApplication,
            @Named("BASE_URL")
            @BindsInstance
            baseUrl: String
        ): AppComponent
    }

    fun provideBitApp(): BitApplication

    fun provideRetrofit(): Retrofit

}


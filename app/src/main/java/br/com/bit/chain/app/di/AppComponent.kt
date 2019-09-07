package br.com.bit.chain.app.di

import br.com.bit.chain.android.di.DaggerViewModelInjectionModule
import br.com.bit.chain.app.BitApplication
import br.com.bit.chain.networking.di.NetworkingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.Scheduler
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DaggerViewModelInjectionModule::class,
        AppModule::class,
        NetworkingModule::class
    ]
)
interface AppComponent {
    fun inject(app: BitApplication)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: BitApplication,
            @Named("BASE_URL") @BindsInstance baseUrl: String
        ): AppComponent
    }

    @Named("MainScheduler")
    fun provideMainScheduler(): Scheduler

    @Named("IOScheduler")
    fun provideIOScheduler(): Scheduler

    fun provideBitApp(): BitApplication

    fun provideRetrofit(): Retrofit

}
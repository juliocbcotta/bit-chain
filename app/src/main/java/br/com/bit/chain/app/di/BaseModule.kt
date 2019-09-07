package br.com.bit.chain.app.di

import br.com.bit.chain.android.di.DaggerViewModelInjectionModule
import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(
    includes = [
        AndroidInjectionModule::class,
        DaggerViewModelInjectionModule::class,
        SchedulersModule::class
    ]
)
abstract class BaseModule
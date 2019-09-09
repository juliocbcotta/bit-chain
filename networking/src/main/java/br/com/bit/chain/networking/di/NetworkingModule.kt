package br.com.bit.chain.networking.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkingModule {

    @Provides
    @Singleton
    @Named(NAMED_GSON_FOR_NETWORKING)
    fun provideGson(): Gson {
        return Gson()
    }

    // NOTE: This method is not using dagger on purpose
    // so we don't need to use api for logging-interceptor dependency in build.gradle file.
    private fun provideLogLevel(
        loggable: Boolean
    ): Level {
        return if (loggable) Level.BODY else Level.NONE
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("LOGGABLE")
        loggable: Boolean
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    level = provideLogLevel(loggable)
                })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        @Named("NETWORKING")
        gson: Gson,
        @Named("BASE_URL")
        baseUrl: String
    ): Retrofit {

        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    companion object {
        const val NAMED_GSON_FOR_NETWORKING = "NETWORKING"
    }
}

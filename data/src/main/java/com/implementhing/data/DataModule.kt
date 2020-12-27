package com.implementhing.data

import com.google.gson.Gson
import com.implementhing.common.infrastructure.log.LogBucket
import com.implementhing.data.core.FlowCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideApiClient(
        gson: Gson,
        logBucket: LogBucket,
    ): ApiClient {
        val log = HttpLoggingInterceptor {
            logBucket.debug(it)
        }

        log.level = HttpLoggingInterceptor.Level.BODY

        val http = OkHttpClient
            .Builder()
            .addInterceptor(log)
            .build()

        return Retrofit
            .Builder()
            .client(http)
            .baseUrl(BuildConfig.API_URL + "3/")
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiClient::class.java)
    }
}
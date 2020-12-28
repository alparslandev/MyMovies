package com.implementhing.data

import com.google.gson.Gson
import com.implementhing.common.config.Configuration
import com.implementhing.common.infrastructure.log.LogBucket
import com.implementhing.data.core.FlowCallAdapterFactory
import com.implementhing.data.core.interceptors.CommonQueryParamsInterceptor
import com.implementhing.data.core.interceptors.HeadersInterceptor
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
    fun provideHttp(logBucket: LogBucket, configuration: Configuration, gson: Gson): ApiService {
        val log = HttpLoggingInterceptor {
            logBucket.debug(it)
        }

        log.level = HttpLoggingInterceptor.Level.BODY

        val http = OkHttpClient
                .Builder()
                .addInterceptor(log)
                .addInterceptor(HeadersInterceptor())
                .addInterceptor(CommonQueryParamsInterceptor(configuration))
                .build()

        return Retrofit
            .Builder()
            .client(http)
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(configuration.apiUrl() + configuration.apiVersion())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(apiService: ApiService): ApiRepository {
        return ApiRepositoryImpl(apiService)
    }
}
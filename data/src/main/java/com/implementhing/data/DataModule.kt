package com.implementhing.data

import com.google.gson.Gson
import com.implementhing.common.config.Configuration
import com.implementhing.common.infrastructure.log.LogBucket
import com.implementhing.data.core.FlowCallAdapterFactory
import com.implementhing.data.core.interceptors.CommonQueryParamsInterceptor
import com.implementhing.data.core.interceptors.HeadersInterceptor
import com.implementhing.data.services.search.SearchRepository
import com.implementhing.data.services.search.SearchRepositoryImpl
import com.implementhing.data.services.search.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @MustBeDocumented
    @Qualifier
    annotation class Movies

    @MustBeDocumented
    @Qualifier
    annotation class Search

    @Provides
    fun provideRetrofitBuilder(gson: Gson, http: OkHttpClient): Retrofit.Builder {
        return Retrofit
                .Builder()
                .client(http)
                .addCallAdapterFactory(FlowCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    @Singleton
    @Movies
    fun provideMoviesService(retrofitBuilder: Retrofit.Builder, configuration: Configuration): MoviesService {
        return retrofitBuilder
                .baseUrl(configuration.apiUrl() + configuration.apiVersion() + "movie/")
                .build()
                .create(MoviesService::class.java)
    }

    @Provides
    @Singleton
    @Movies
    fun provideMoviesRepository(moviesService: MoviesService): MoviesRepository {
        return MoviesRepositoryImpl(moviesService)
    }

    @Provides
    @Singleton
    @Search
    fun provideSearchService(retrofitBuilder: Retrofit.Builder, configuration: Configuration): SearchService {
        return retrofitBuilder
                .baseUrl(configuration.apiUrl() + configuration.apiVersion() + "search/")
                .build()
                .create(SearchService::class.java)
    }

    @Provides
    @Singleton
    @Search
    fun provideSearchRepository(searchService: SearchService): SearchRepository {
        return SearchRepositoryImpl(searchService)
    }

    @Provides
    @Singleton
    fun provideHttp(logBucket: LogBucket, configuration: Configuration): OkHttpClient {
        val log = HttpLoggingInterceptor {
            logBucket.debug(it)
        }

        log.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient
                .Builder()
                .addInterceptor(log)
                .addInterceptor(HeadersInterceptor())
                .addInterceptor(CommonQueryParamsInterceptor(configuration))
                .build()
    }
}
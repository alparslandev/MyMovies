package com.implementhing.common.infrastructure

import android.content.Context
import com.google.gson.Gson
import com.implementhing.common.config.Configuration
import com.implementhing.common.config.DefaultConfigurationImpl
import com.implementhing.common.infrastructure.log.AndroidLogger
import com.implementhing.common.infrastructure.log.DefaultLogBucket
import com.implementhing.common.infrastructure.log.LogBucket
import com.implementhing.common.infrastructure.log.TimberLogger
import com.implementhing.common.infrastructure.storage.DefaultLocalStorageImpl
import com.implementhing.common.infrastructure.storage.LocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object InfrastructureModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonUtils.gson
    }

    @Provides
    @Singleton
    fun provideConfiguration(): Configuration {
        return DefaultConfigurationImpl()
    }

    @Provides
    @Singleton
    fun provideLocalStorage(@ApplicationContext context: Context): LocalStorage {
        return DefaultLocalStorageImpl(context)
    }

    @Provides
    @Singleton
    fun provideLogBucket(): LogBucket {
        return DefaultLogBucket().apply {
            add(AndroidLogger())
            add(TimberLogger())
        }
    }
}
package com.implementhing.common.config

import com.implementhing.common.BuildConfig

class DefaultConfigurationImpl : Configuration {

    override val apiKey = BuildConfig.API_KEY

    override fun apiUrl(): String {
        return BuildConfig.API_URL
    }

    override fun apiVersion(): String {
        return BuildConfig.API_VERSION
    }

}
package com.implementhing.common.config

import com.implementhing.common.BuildConfig

class DefaultConfigurationImpl : Configuration {

    override val apiKey = ""

    override fun apiUrl(): String {
        return BuildConfig.API_URL
    }

    override fun imageBaseUrl(): String {
        return ""
    }

    override fun apiVersion(): String {
        return ""
    }

}
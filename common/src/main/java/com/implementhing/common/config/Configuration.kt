package com.implementhing.common.config

interface Configuration {
    fun apiUrl(): String
    fun imageBaseUrl(): String
    fun apiVersion(): String
    val apiKey: String
}
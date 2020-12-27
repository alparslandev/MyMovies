package com.implementhing.data.core.interceptors

import com.implementhing.common.config.Configuration
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class CommonQueryParamsInterceptor @Inject constructor(
    private val configuration: Configuration
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("api_key", configuration.apiKey)
            .addQueryParameter("language", "en-US") // TODO Implement a LanguageManager
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
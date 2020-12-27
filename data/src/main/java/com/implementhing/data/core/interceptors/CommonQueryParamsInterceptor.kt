package com.implementhing.data.core.interceptors

import com.implementhing.data.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class CommonQueryParamsInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", "en-US") // TODO Implement a LanguageManager
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
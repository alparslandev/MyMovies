package com.implementhing.common.infrastructure

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtils {
    val gson: Gson
        get() = GsonBuilder().create()

    fun <T> toJson(classOfT: T): String = gson.toJson(classOfT)

    fun <T> parseTo(json: String, classOfT: Class<T>): T = gson.fromJson(json, classOfT)
}
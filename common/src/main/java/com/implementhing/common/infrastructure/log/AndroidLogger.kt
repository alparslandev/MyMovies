package com.implementhing.common.infrastructure.log

import android.annotation.SuppressLint
import android.util.Log
import org.json.JSONObject

@SuppressLint("LogNotTimber")
class AndroidLogger : Logger, LogTag {

    override val tag: String
        get() = "MyMovies"

    override fun verbose(message: CharSequence) {
        Log.v(tag, "$message")
    }

    override fun debug(message: CharSequence) {
        Log.d(tag, "$message")
    }

    override fun info(message: CharSequence) {
        Log.i(tag, "$message")
    }

    override fun warn(message: CharSequence) {
        Log.w(tag, "$message")
    }

    override fun error(message: CharSequence) {
        Log.e(tag, "$message")
    }

    override fun assert(message: CharSequence) {
        Log.e(tag, "$message")
    }

    override fun wtf(message: CharSequence) {
        Log.wtf(tag, "$message")
    }

    override fun json(message: CharSequence) {
        val pretty = JSONObject("$message").toString(3)
        Log.d(tag, pretty)
    }
}
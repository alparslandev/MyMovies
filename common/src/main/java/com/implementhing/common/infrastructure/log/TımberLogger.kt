package com.implementhing.common.infrastructure.log

import org.json.JSONObject
import timber.log.Timber

class TimberLogger : Logger {

    override fun verbose(message: CharSequence) {
        Timber.v("$message")
    }

    override fun debug(message: CharSequence) {
        Timber.d("$message")
    }

    override fun info(message: CharSequence) {
        Timber.i("$message")
    }

    override fun warn(message: CharSequence) {
        Timber.w("$message")
    }

    override fun error(message: CharSequence) {
        Timber.e("$message")
    }

    override fun assert(message: CharSequence) {
        Timber.e("$message")
    }

    override fun wtf(message: CharSequence) {
        Timber.wtf("$message")
    }

    override fun json(message: CharSequence) {
        val pretty = JSONObject("$message").toString(3)
        Timber.d(pretty)
    }
}
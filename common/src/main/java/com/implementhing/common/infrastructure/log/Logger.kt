package com.implementhing.common.infrastructure.log

interface Logger {
    fun verbose(message: CharSequence)
    fun debug(message: CharSequence)
    fun info(message: CharSequence)
    fun warn(message: CharSequence)
    fun error(message: CharSequence)
    fun assert(message: CharSequence)
    fun wtf(message: CharSequence)
    fun json(message: CharSequence)
}

internal interface LogTag {
    val tag: String
}
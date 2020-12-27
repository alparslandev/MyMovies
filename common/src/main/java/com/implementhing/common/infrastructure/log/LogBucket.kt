package com.implementhing.common.infrastructure.log

interface LogBucket : Logger {
    fun add(logger: Logger)
    fun remove(logger: Logger)
}
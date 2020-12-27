package com.implementhing.common.infrastructure.log

class DefaultLogBucket : LogBucket {

    private val workers = arrayListOf<Logger>()

    override fun verbose(message: CharSequence) {
        workers.forEach { it.verbose(message) }
    }

    override fun debug(message: CharSequence) {
        workers.forEach { it.debug(message) }
    }

    override fun info(message: CharSequence) {
        workers.forEach { it.info(message) }
    }

    override fun warn(message: CharSequence) {
        workers.forEach { it.warn(message) }
    }

    override fun error(message: CharSequence) {
        workers.forEach { it.error(message) }
    }

    override fun assert(message: CharSequence) {
        workers.forEach { it.assert(message) }
    }

    override fun wtf(message: CharSequence) {
        workers.forEach { it.wtf(message) }
    }

    override fun json(message: CharSequence) {
        workers.forEach { it.json(message) }
    }

    override fun add(logger: Logger) {
        workers.add(logger)
    }

    override fun remove(logger: Logger) {
        workers.remove(logger)
    }
}
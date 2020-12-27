package com.implementhing.common.infrastructure.storage


/**
 * Local storage
 *
 * @constructor Create empty Local storage
 */
interface LocalStorage {

    /**
     * Store int
     *
     * @param key
     * @param value
     */
    fun storeInt(key: String, value: Int)

    /**
     * Store long
     *
     * @param key
     * @param value
     */
    fun storeLong(key: String, value: Long)

    /**
     * Store object
     *
     * @param T
     * @param key
     * @param value
     */
    fun <T> storeObject(key: String, value: T)

    /**
     * Store float
     *
     * @param key
     * @param value
     */
    fun storeFloat(key: String, value: Float)

    /**
     * Store string
     *
     * @param key
     * @param value
     */
    fun storeString(key: String, value: String)

    /**
     * Store boolean
     *
     * @param key
     * @param value
     */
    fun storeBoolean(key: String, value: Boolean)

    /**
     * Get int
     *
     * @param key
     * @return int
     */
    fun getInt(key: String): Int

    /**
     * Get object
     *
     * @param T
     * @param key
     * @return Object T
     */
    fun <T> getObject(key: String): T?

    /**
     * Get long
     *
     * @param key
     * @return Long
     */
    fun getLong(key: String): Long

    /**
     * Get float
     *
     * @param key
     * @return Float
     */
    fun getFloat(key: String): Float

    /**
     * Get string
     *
     * @param key
     * @return String
     */
    fun getString(key: String): String

    /**
     * Get boolean
     *
     * @param key
     * @return Boolean
     */
    fun getBoolean(key: String): Boolean

    fun clear()
}
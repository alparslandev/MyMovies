package com.implementhing.common

import com.implementhing.common.extensions.Password
import com.implementhing.common.extensions.TCKN
import com.implementhing.common.extensions.isValid
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun isTCKNValid() {
        val tckn: TCKN = "66313300282"
        assertEquals(true, tckn.isValid)
    }

    @Test
    fun isPasswordValid() {
        val password: Password = "123456"
        assertEquals(true, password.isValid())
    }
}
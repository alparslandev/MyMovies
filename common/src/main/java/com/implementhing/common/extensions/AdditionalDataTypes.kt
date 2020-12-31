package com.implementhing.common.extensions

typealias TCKN = String
typealias Password = String
typealias CustomerNo = String

val TCKN.isValid: Boolean
    get() {
        fun isInt(): Boolean {
            for (index in 0 until length) {
                val char = this[index]
                if (index == 0 && char == '-') continue
                if (!Character.isDigit(char)) return false
            }
            return true
        }

        var isValid = false
        if (length == 11 && isInt()) {
            var aTcNo: Long
            val bTcNo: Long
            val n1: Long
            val n2: Long
            val n3: Long
            val n4: Long
            val n5: Long
            val n6: Long
            val n7: Long
            val n8: Long
            val n9: Long
            val control1: Long
            val control2: Long
            val tcNo: Long = toLong()
            aTcNo = tcNo / 100
            bTcNo = tcNo / 100
            n1 = aTcNo % 10
            aTcNo /= 10
            n2 = aTcNo % 10
            aTcNo /= 10
            n3 = aTcNo % 10
            aTcNo /= 10
            n4 = aTcNo % 10
            aTcNo /= 10
            n5 = aTcNo % 10
            aTcNo /= 10
            n6 = aTcNo % 10
            aTcNo /= 10
            n7 = aTcNo % 10
            aTcNo /= 10
            n8 = aTcNo % 10
            aTcNo /= 10
            n9 = aTcNo % 10
            control1 = (10 - ((n1 + n3 + n5 + n7 + n9) * 3 + (n2 + n4 + n6 + n8)) % 10) % 10
            control2 = (10 - ((n2 + n4 + n6 + n8 + control1) * 3 + (n1 + n3 + n5 + n7 + n9)) % 10) % 10
            isValid = bTcNo * 100 + control1 * 10 + control2 == tcNo
        }

        return isValid
    }

fun Password?.isValid(targetLength: Int = 6): Boolean {
    if (this == null || length != targetLength) {
        return false
    }

    for (index in 0 until length) {
        val char = this[index]
        if (!Character.isDigit(char)) return false
    }

    return true
}
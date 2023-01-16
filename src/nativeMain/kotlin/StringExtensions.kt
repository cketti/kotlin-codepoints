@file:Suppress("NOTHING_TO_INLINE")

package de.cketti.codepoints

actual inline fun String.codePointAt(index: Int): Int {
    return CommonStringFunctions.codePointAt(this, index)
}

actual inline fun String.codePointBefore(index: Int): Int {
    return CommonStringFunctions.codePointBefore(this, index)
}

actual inline fun String.codePointCount(beginIndex: Int, endIndex: Int): Int {
    return CommonStringFunctions.codePointCount(this, beginIndex, endIndex)
}

actual inline fun String.offsetByCodePoints(index: Int, codePointOffset: Int): Int {
    return CommonStringFunctions.offsetByCodePoints(this, index, codePointOffset)
}

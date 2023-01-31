@file:Suppress("NOTHING_TO_INLINE", "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package de.cketti.codepoints

actual inline fun String.codePointAt(index: Int): Int {
    return (this as java.lang.String).codePointAt(index)
}

actual inline fun String.codePointBefore(index: Int): Int {
    return (this as java.lang.String).codePointBefore(index)
}

actual inline fun String.codePointCount(beginIndex: Int, endIndex: Int): Int {
    return (this as java.lang.String).codePointCount(beginIndex, endIndex)
}

actual inline fun String.offsetByCodePoints(index: Int, codePointOffset: Int): Int {
    return (this as java.lang.String).offsetByCodePoints(index, codePointOffset)
}

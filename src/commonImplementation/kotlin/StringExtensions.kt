@file:Suppress("NOTHING_TO_INLINE")

package de.cketti.codepoints

import de.cketti.codepoints.internal.codePointAt as commonCodePointAt
import de.cketti.codepoints.internal.codePointBefore as commonCodePointBefore
import de.cketti.codepoints.internal.codePointCount as commonCodePointCount
import de.cketti.codepoints.internal.offsetByCodePoints as commonOffsetByCodePoints

actual fun String.codePointAt(index: Int): Int {
    return commonCodePointAt(this, index)
}

actual fun String.codePointBefore(index: Int): Int {
    return commonCodePointBefore(this, index)
}

actual fun String.codePointCount(beginIndex: Int, endIndex: Int): Int {
    return commonCodePointCount(this, beginIndex, endIndex)
}

actual fun String.offsetByCodePoints(index: Int, codePointOffset: Int): Int {
    return commonOffsetByCodePoints(this, index, codePointOffset)
}

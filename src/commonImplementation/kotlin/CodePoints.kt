@file:Suppress("NOTHING_TO_INLINE")

package de.cketti.codepoints

import de.cketti.codepoints.internal.charCount as commonCharCount
import de.cketti.codepoints.internal.highSurrogate as commonHighSurrogate
import de.cketti.codepoints.internal.isBmpCodePoint as commonIsBmpCodePoint
import de.cketti.codepoints.internal.isSupplementaryCodePoint as commonIsSupplementaryCodePoint
import de.cketti.codepoints.internal.isSurrogatePair as commonIsSurrogatePair
import de.cketti.codepoints.internal.isValidCodePoint as commonIsValidCodePoint
import de.cketti.codepoints.internal.lowSurrogate as commonLowSurrogate
import de.cketti.codepoints.internal.toChars as commonToChars
import de.cketti.codepoints.internal.toCodePoint as commonToCodePoint

actual object CodePoints {
    actual inline fun isValidCodePoint(codePoint: Int): Boolean {
        return commonIsValidCodePoint(codePoint)
    }

    actual inline fun isBmpCodePoint(codePoint: Int): Boolean {
        return commonIsBmpCodePoint(codePoint)
    }
    
    actual inline fun isSupplementaryCodePoint(codePoint: Int): Boolean {
        return commonIsSupplementaryCodePoint(codePoint)
    }

    actual inline fun charCount(codePoint: Int): Int {
        return commonCharCount(codePoint)
    }

    actual inline fun isSurrogatePair(highSurrogate: Char, lowSurrogate: Char): Boolean {
        return commonIsSurrogatePair(highSurrogate, lowSurrogate)
    }

    actual fun highSurrogate(codePoint: Int): Char {
        return commonHighSurrogate(codePoint)
    }
    
    actual fun lowSurrogate(codePoint: Int): Char {
        return commonLowSurrogate(codePoint)
    }

    actual inline fun toCodePoint(highSurrogate: Char, lowSurrogate: Char): Int {
        return commonToCodePoint(highSurrogate, lowSurrogate)
    }

    actual inline fun toChars(codePoint: Int): CharArray {
        return commonToChars(codePoint)
    }

    actual inline fun toChars(codePoint: Int, destination: CharArray, offset: Int): Int {
        return commonToChars(codePoint, destination, offset)
    }
}

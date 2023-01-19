@file:Suppress("NOTHING_TO_INLINE")

package de.cketti.codepoints

actual object CodePoints {
    actual inline fun isValidCodePoint(codePoint: Int): Boolean {
        return CommonCodePoints.isValidCodePoint(codePoint)
    }

    actual inline fun isBmpCodePoint(codePoint: Int): Boolean {
        return CommonCodePoints.isBmpCodePoint(codePoint)
    }
    
    actual inline fun isSupplementaryCodePoint(codePoint: Int): Boolean {
        return CommonCodePoints.isSupplementaryCodePoint(codePoint)
    }

    actual inline fun charCount(codePoint: Int): Int {
        return CommonCodePoints.charCount(codePoint)
    }

    actual inline fun isSurrogate(char: Char): Boolean {
        return CommonCodePoints.isSurrogate(char)
    }

    actual inline fun isHighSurrogate(char: Char): Boolean {
        return CommonCodePoints.isHighSurrogate(char)
    }
    
    actual inline fun isLowSurrogate(char: Char): Boolean {
        return CommonCodePoints.isLowSurrogate(char)
    }

    actual inline fun isSurrogatePair(highSurrogate: Char, lowSurrogate: Char): Boolean {
        return CommonCodePoints.isSurrogatePair(highSurrogate, lowSurrogate)
    }

    actual fun highSurrogate(codePoint: Int): Char {
        return CommonCodePoints.highSurrogate(codePoint)
    }
    
    actual fun lowSurrogate(codePoint: Int): Char {
        return CommonCodePoints.lowSurrogate(codePoint)
    }

    actual inline fun toCodePoint(highSurrogate: Char, lowSurrogate: Char): Int {
        return CommonCodePoints.toCodePoint(highSurrogate, lowSurrogate)
    }
}

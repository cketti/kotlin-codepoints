@file:Suppress("NOTHING_TO_INLINE")

package de.cketti.codepoints

actual object CodePoints {
    actual inline fun isValidCodePoint(codePoint: Int): Boolean {
        return Character.isValidCodePoint(codePoint)
    }
    
    actual inline fun isBmpCodePoint(codePoint: Int): Boolean {
        return Character.isBmpCodePoint(codePoint)
    }
    
    actual inline fun isSupplementaryCodePoint(codePoint: Int): Boolean {
        return Character.isSupplementaryCodePoint(codePoint)
    }
    
    actual inline fun charCount(codePoint: Int): Int {
        return Character.charCount(codePoint)
    }

    actual inline fun isSurrogatePair(highSurrogate: Char, lowSurrogate: Char): Boolean {
        return Character.isSurrogatePair(highSurrogate, lowSurrogate)
    }

    actual inline fun highSurrogate(codePoint: Int): Char {
        return Character.highSurrogate(codePoint)
    }
    
    actual inline fun lowSurrogate(codePoint: Int): Char {
        return Character.lowSurrogate(codePoint)
    }

    actual inline fun toCodePoint(highSurrogate: Char, lowSurrogate: Char): Int {
        return Character.toCodePoint(highSurrogate, lowSurrogate)
    }

    actual inline fun toChars(codePoint: Int): CharArray {
        return Character.toChars(codePoint)
    }

    actual inline fun toChars(codePoint: Int, destination: CharArray, offset: Int): Int {
        return Character.toChars(codePoint, destination, offset)
    }
}

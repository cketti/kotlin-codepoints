package de.cketti.codepoints

import de.cketti.codepoints.CommonCodePoints.isHighSurrogate
import de.cketti.codepoints.CommonCodePoints.isLowSurrogate
import de.cketti.codepoints.CommonCodePoints.toCodePoint

object CommonStringFunctions {
    fun codePointAt(text: String, index: Int): Int {
        if (index !in text.indices) throw IndexOutOfBoundsException()

        val firstChar = text[index]
        if (isHighSurrogate(firstChar) && index + 1 < text.length) {
            val nextChar = text[index + 1]
            if (isLowSurrogate(nextChar)) {
                return toCodePoint(firstChar, nextChar)
            }
        }
        
        return firstChar.code
    }
    
    fun codePointBefore(text: String, index: Int): Int {
        val startIndex = index - 1
        if (startIndex !in text.indices) throw IndexOutOfBoundsException()
    
        val firstChar = text[startIndex]
        if (isLowSurrogate(firstChar) && startIndex - 1 >= 0) {
            val previousChar = text[startIndex - 1]
            if (isHighSurrogate(previousChar)) {
                return toCodePoint(previousChar, firstChar)
            }
        }
    
        return firstChar.code
    }
    
    fun codePointCount(text: String, beginIndex: Int, endIndex: Int): Int {
        if (beginIndex < 0 || endIndex > text.length || beginIndex > endIndex) throw IndexOutOfBoundsException()
        
        var index = beginIndex
        var count = 0
        do {
            val firstChar = text[index]
            index++
            if (isHighSurrogate(firstChar) && index < endIndex) {
                val nextChar = text[index]
                if (isLowSurrogate(nextChar)) {
                    index++
                }
            }
    
            count++
        } while (index < endIndex)
    
        return count
    }
    
    fun offsetByCodePoints(text: String, index: Int, codePointOffset: Int): Int {
        if (index !in 0..text.length) throw IndexOutOfBoundsException()
        if (codePointOffset == 0) return index

        if (codePointOffset > 0) {
            var currentIndex = index
            repeat(codePointOffset) {
                if (currentIndex > text.lastIndex) throw IndexOutOfBoundsException()
                val firstChar = text[currentIndex]
                currentIndex++
                if (isHighSurrogate(firstChar) && currentIndex <= text.lastIndex) {
                    val nextChar = text[currentIndex]
                    if (isLowSurrogate(nextChar)) {
                        currentIndex++
                    }
                }
            }

            return currentIndex
        } else {
            var currentIndex = index - 1
            repeat(-codePointOffset) {
                if (currentIndex < 0) throw IndexOutOfBoundsException()
                val firstChar = text[currentIndex]
                currentIndex--
                if (isLowSurrogate(firstChar) && currentIndex >= 0) {
                    val previousChar = text[currentIndex]
                    if (isHighSurrogate(previousChar)) {
                        currentIndex--
                    }
                }
            }

            return currentIndex + 1
        }
    }
}

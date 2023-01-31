package de.cketti.codepoints

import de.cketti.codepoints.CodePoints.toCodePoint

actual fun String.codePointAt(index: Int): Int {
    if (index !in indices) throw IndexOutOfBoundsException()

    val firstChar = this[index]
    if (firstChar.isHighSurrogate() && index + 1 < length) {
        val nextChar = this[index + 1]
        if (nextChar.isLowSurrogate()) {
            return toCodePoint(firstChar, nextChar)
        }
    }

    return firstChar.code
}

actual fun String.codePointBefore(index: Int): Int {
    val startIndex = index - 1
    if (startIndex !in indices) throw IndexOutOfBoundsException()

    val firstChar = this[startIndex]
    if (firstChar.isLowSurrogate() && startIndex - 1 >= 0) {
        val previousChar = this[startIndex - 1]
        if (previousChar.isHighSurrogate()) {
            return toCodePoint(previousChar, firstChar)
        }
    }

    return firstChar.code
}

actual fun String.codePointCount(beginIndex: Int, endIndex: Int): Int {
    if (beginIndex < 0 || endIndex > length || beginIndex > endIndex) throw IndexOutOfBoundsException()

    var index = beginIndex
    var count = 0
    do {
        val firstChar = this[index]
        index++
        if (firstChar.isHighSurrogate() && index < endIndex) {
            val nextChar = this[index]
            if (nextChar.isLowSurrogate()) {
                index++
            }
        }

        count++
    } while (index < endIndex)

    return count
}

actual fun String.offsetByCodePoints(index: Int, codePointOffset: Int): Int {
    if (index !in 0..length) throw IndexOutOfBoundsException()
    if (codePointOffset == 0) return index

    if (codePointOffset > 0) {
        var currentIndex = index
        repeat(codePointOffset) {
            if (currentIndex > lastIndex) throw IndexOutOfBoundsException()
            val firstChar = this[currentIndex]
            currentIndex++
            if (firstChar.isHighSurrogate() && currentIndex <= lastIndex) {
                val nextChar = this[currentIndex]
                if (nextChar.isLowSurrogate()) {
                    currentIndex++
                }
            }
        }

        return currentIndex
    } else {
        var currentIndex = index - 1
        repeat(-codePointOffset) {
            if (currentIndex < 0) throw IndexOutOfBoundsException()
            val firstChar = this[currentIndex]
            currentIndex--
            if (firstChar.isLowSurrogate() && currentIndex >= 0) {
                val previousChar = this[currentIndex]
                if (previousChar.isHighSurrogate()) {
                    currentIndex--
                }
            }
        }

        return currentIndex + 1
    }
}

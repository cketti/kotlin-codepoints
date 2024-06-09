package de.cketti.codepoints

/**
 * Returns the Unicode code point at the specified index. 
 * 
 * The `index` parameter is the regular `CharSequence` index, i.e. the number of `Char`s from the start of the character
 * sequence.
 * 
 * If the code point at the specified index is part of the Basic Multilingual Plane (BMP), its value can be represented
 * using a single `Char` and this method will behave exactly like [CharSequence.get]. 
 * Code points outside the BMP are encoded using a surrogate pair – a `Char` containing a value in the high surrogate
 * range followed by a `Char` containing a value in the low surrogate range. Together these two `Char`s encode a single
 * code point in one of the supplementary planes. This method will do the necessary decoding and return the value of 
 * that single code point.
 * 
 * In situations where surrogate characters are encountered that don't form a valid surrogate pair starting at `index`,
 * this method will return the surrogate code point itself, behaving like [CharSequence.get].
 * 
 * If the `index` is out of bounds of this character sequence, this method throws an [IndexOutOfBoundsException].
 * 
 * To iterate over all code points in a character sequence the index has to be adjusted depending on the value of the
 * returned code point. Use [CodePoints.charCount] for this.
 * 
 * ```kotlin
 * // Text containing code points outside the BMP (encoded as a surrogate pairs)
 * val text = "\uD83E\uDD95\uD83E\uDD96"
 * 
 * var index = 0
 * while (index < text.length) {
 *     val codePoint = text.codePointAt(index)
 *     // Do something with codePoint
 *     
 *     index += CodePoints.charCount(codePoint)
 * }
 * ```
 */
fun CharSequence.codePointAt(index: Int): Int {
    if (index !in indices) throw IndexOutOfBoundsException()

    val firstChar = this[index]
    if (firstChar.isHighSurrogate() && index + 1 < length) {
        val nextChar = this[index + 1]
        if (nextChar.isLowSurrogate()) {
            return CodePoints.toCodePoint(firstChar, nextChar)
        }
    }

    return firstChar.code
}

/**
 * Returns the Unicode code point before the specified index.
 *
 * The `index` parameter is the regular `CharSequence` index, i.e. the number of `Char`s from the start of the character
 * sequence.
 * 
 * If the `Char` value at `index - 1` is in the low surrogate range and the `Char` value at `index - 2` is in the high
 * surrogate range, then the surrogate pair is decoded and the code point in one of the supplementary planes is 
 * returned. In all other cases this method behaves like [CharSequence.get] was called with an argument of `index - 1`.
 *
 * If the value `index - 1` is out of bounds of this character sequence, this method throws an 
 * [IndexOutOfBoundsException].
 */
fun CharSequence.codePointBefore(index: Int): Int {
    val startIndex = index - 1
    if (startIndex !in indices) throw IndexOutOfBoundsException()

    val firstChar = this[startIndex]
    if (firstChar.isLowSurrogate() && startIndex - 1 >= 0) {
        val previousChar = this[startIndex - 1]
        if (previousChar.isHighSurrogate()) {
            return CodePoints.toCodePoint(previousChar, firstChar)
        }
    }

    return firstChar.code
}

/**
 * Returns the number of Unicode code points in this `CharSequence`.
 */
fun CharSequence.codePointCount(): Int {
    return codePointCount(beginIndex = 0, endIndex = length)
}

/**
 * Returns the number of Unicode code points in the specified text range of this `CharSequence`.
 * 
 * The text range begins at the specified `beginIndex` and extends to the `Char` at index `endIndex - 1`. Thus, the 
 * length (in `Char`s) of the text range is `endIndex - beginIndex`. Unpaired surrogates within the text range count as
 * one code point each.
 * 
 * If `beginIndex` is negative, or `endIndex` is larger than the length of this string, or `beginIndex` is larger than
 * `endIndex`, this method throws an [IndexOutOfBoundsException].
 */
fun CharSequence.codePointCount(beginIndex: Int, endIndex: Int): Int {
    if (beginIndex < 0 || endIndex > length || beginIndex > endIndex) throw IndexOutOfBoundsException()

    var index = beginIndex
    var count = 0
    while (index < endIndex) {
        val firstChar = this[index]
        index++
        if (firstChar.isHighSurrogate() && index < endIndex) {
            val nextChar = this[index]
            if (nextChar.isLowSurrogate()) {
                index++
            }
        }

        count++
    }

    return count
}

/**
 * Returns the index within this `CharSequence` that is offset from the given `index` by `codePointOffset` code points. 
 * 
 * Unpaired surrogates within the text range given by `index` and `codePointOffset` count as one code point each.
 * 
 * If `index` is negative or larger than the length of this character sequence, or if `codePointOffset` is positive and
 * the subsequence starting with `index` has fewer than `codePointOffset` code points, or if `codePointOffset` is
 * negative and the subsequence before index has fewer than the absolute value of `codePointOffset` code points, this
 * method throws an [IndexOutOfBoundsException].
 */
fun CharSequence.offsetByCodePoints(index: Int, codePointOffset: Int): Int {
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

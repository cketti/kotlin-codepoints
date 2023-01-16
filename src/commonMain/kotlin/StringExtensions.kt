package de.cketti.codepoints

/**
 * Returns the Unicode code point at the specified index. 
 * 
 * The `index` parameter is the regular `String` index, i.e. the number of `Char`s from the start of the string.
 * 
 * If the code point at the specified index is part of the Basic Multilingual Plane (BMP), its value can be represented
 * using a single `Char` and this method will behave exactly like [String.get]. 
 * Code points outside the BMP are encoded using a surrogate pair – a `Char` containing a value in the high surrogate
 * range followed by a `Char` containing a value in the low surrogate range. Together these two `Char`s encode a single
 * code point in one of the supplementary planes. This method will do the necessary decoding and return the value of 
 * that single code point.
 * 
 * In situations where surrogate characters are encountered that don't form a valid surrogate pair starting at `index`,
 * this method will return the surrogate code point itself, behaving like [String.get].
 * 
 * If the `index` is out of bounds of this string, this method throws an [IndexOutOfBoundsException].
 * 
 * To iterate over all code points in a string the index has to be adjusted depending on the value of the returned code
 * point. Use [CodePoints.charCount] for this.
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
expect fun String.codePointAt(index: Int): Int

/**
 * Returns the Unicode code point before the specified index.
 *
 * The `index` parameter is the regular `String` index, i.e. the number of `Char`s from the start of the string.
 * 
 * If the `Char` value at `index - 1` is in the low surrogate range and the `Char` value at `index - 2` is in the high
 * surrogate range, then the surrogate pair is decoded and the code point in one of the supplementary planes is 
 * returned. In all other cases this method behaves like [String.get] was called with an argument of `index - 1`.
 *
 * If the value `index - 1` is out of bounds of this string, this method throws an [IndexOutOfBoundsException].
 */
expect fun String.codePointBefore(index: Int): Int

/**
 * Returns the number of Unicode code points in the specified text range of this `String`.
 * 
 * The text range begins at the specified `beginIndex` and extends to the `Char` at index `endIndex - 1`. Thus, the 
 * length (in `Char`s) of the text range is `endIndex - beginIndex`. Unpaired surrogates within the text range count as
 * one code point each.
 * 
 * If `beginIndex` is negative, or `endIndex` is larger than the length of this string, or `beginIndex` is larger than
 * `endIndex`, this method throws an [IndexOutOfBoundsException].
 */
expect fun String.codePointCount(beginIndex: Int, endIndex: Int): Int

/**
 * Returns the index within this `String` that is offset from the given `index` by `codePointOffset` code points. 
 * 
 * Unpaired surrogates within the text range given by `index` and `codePointOffset` count as one code point each.
 * 
 * If `index` is negative or larger than the length of this string, or if `codePointOffset` is positive and the 
 * substring starting with `index` has fewer than `codePointOffset` code points, or if `codePointOffset` is negative 
 * and the substring before index has fewer than the absolute value of `codePointOffset` code points, this method throws
 * an [IndexOutOfBoundsException].
 */
expect fun String.offsetByCodePoints(index: Int, codePointOffset: Int): Int

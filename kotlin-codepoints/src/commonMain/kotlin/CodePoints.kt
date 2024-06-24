package de.cketti.codepoints

expect object CodePoints {
    /**
     * Determines whether the specified code point is a valid Unicode code point value.
     */
    fun isValidCodePoint(codePoint: Int): Boolean
    
    /**
     * Determines whether the specified Unicode code point is in the Basic Multilingual Plane (BMP). 
     * 
     * Such code points can be represented using a single `Char`.
     */
    fun isBmpCodePoint(codePoint: Int): Boolean

    /**
     * Determines whether the specified Unicode code point is in the supplementary character range.
     * 
     * In a `String` such code points are represented using a surrogate pair, i.e. two `Char` values.
     */
    fun isSupplementaryCodePoint(codePoint: Int): Boolean

    /**
     * Determines the number of `Char` values needed to represent the specified Unicode code point.
     * 
     * If the specified code point is in the [BMP][isBmpCodePoint], this method will return `1`, otherwise the code 
     * point is in the [supplementary character range][isSupplementaryCodePoint] and this method will return `2`.
     *
     * This method doesn't validate the specified character to be a valid Unicode code point. The caller must validate
     * the character value using [isValidCodePoint] if necessary.
     */
    fun charCount(codePoint: Int): Int

    /**
     * Determines whether the specified pair of `Char` values is a valid Unicode surrogate pair.
     *
     * This method is equivalent to the expression:
     * ```kotlin
     * highSurrogate.isHighSurrogate() && lowSurrogate.isLowSurrogate()
     * ```
     */
    fun isSurrogatePair(highSurrogate: Char, lowSurrogate: Char): Boolean

    /**
     * Returns the leading surrogate (a high surrogate code unit) of the surrogate pair representing the specified
     * supplementary character (Unicode code point) in the UTF-16 encoding. 
     * 
     * If the specified character is not a supplementary character, an unspecified `Char` is returned.
     *
     * If [`isSupplementaryCodePoint(x)`][isSupplementaryCodePoint] is `true`, then 
     * [isHighSurrogate]`(x.highSurrogate())` and
     * [toCodePoint][toCodePoint]`(x.highSurrogate(), `[`x.lowSurrogate()`][lowSurrogate]`) == x` are also always `true`.
     */
    fun highSurrogate(codePoint: Int): Char

    /**
     * Returns the trailing surrogate (a low surrogate code unit) of the surrogate pair representing the specified 
     * supplementary character (Unicode code point) in the UTF-16 encoding. 
     * 
     * If the specified character is not a supplementary character, an unspecified char is returned.
     *
     * If [`isSupplementaryCodePoint(x)`][isSupplementaryCodePoint] is `true`, then
     * [isLowSurrogate]`(x.lowSurrogate())` and
     * [toCodePoint][toCodePoint]`(`[`x.highSurrogate()`][highSurrogate], `x.lowSurrogate()) == x` are also always `true`.
     */
    fun lowSurrogate(codePoint: Int): Char

    /**
     * Converts the specified surrogate pair to its supplementary code point value. 
     * 
     * This method does not validate the specified surrogate pair. The caller must validate it using [isSurrogatePair]
     * if necessary.
     */
    fun toCodePoint(highSurrogate: Char, lowSurrogate: Char): Int

    /**
     * Converts the specified character (Unicode code point) to its UTF-16 representation stored in a char array.
     * If the specified code point is a BMP (Basic Multilingual Plane or Plane 0) value, the resulting char array has
     * the same value as [codePoint]. If the specified code point is a supplementary code point, the resulting char
     * array has the corresponding surrogate pair.
     */
    fun toChars(codePoint: Int): CharArray

    /**
     * Converts the specified character (Unicode code point) to its UTF-16 representation. If the specified code point
     * is a BMP (Basic Multilingual Plane or Plane 0) value, the same value is stored in `destination[offset]`,
     * and 1 is returned. If the specified code point is a supplementary character, its surrogate values are stored in
     * `destination[offset]` (high-surrogate) and `destination[offset+1]` (low-surrogate), and 2 is returned.
     */
    fun toChars(codePoint: Int, destination: CharArray, offset: Int): Int

    /**
     * Converts the given code points to a string.
     * 
     * @param codePoints Array of code points.
     * 
     * @throws IllegalArgumentException If any invalid code point is found in [codePoints].
     */
    fun toString(vararg codePoints: Int): String
}

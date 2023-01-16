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
     * Determines if the given `Char` value is a Unicode surrogate code unit.
     *
     * Such values do not represent characters by themselves, but are used in the representation of supplementary
     * characters in the UTF-16 encoding.
     *
     * A `Char` value is a surrogate code unit if and only if it is either a [low surrogate][isLowSurrogate] code unit 
     * or a [high surrogate][isHighSurrogate] code unit.
     */
    fun isSurrogate(char: Char): Boolean

    /**
     * Determines if the given `Char` value is a Unicode high-surrogate code unit (also known as leading-surrogate 
     * code unit).
     *
     * Such values do not represent characters by themselves, but are used in the representation of 
     * [supplementary characters][isSupplementaryCodePoint] in the UTF-16 encoding.
     */
    fun isHighSurrogate(char: Char): Boolean

    /**
     * Determines if the given `Char` value is a Unicode low-surrogate code unit (also known as trailing-surrogate 
     * code unit).
     *
     * Such values do not represent characters by themselves, but are used in the representation of
     * [supplementary characters][isSupplementaryCodePoint] in the UTF-16 encoding.
     */
    fun isLowSurrogate(char: Char): Boolean

    /**
     * Determines whether the specified pair of `Char` values is a valid Unicode surrogate pair.
     *
     * This method is equivalent to the expression:
     * ```kotlin
     * isHighSurrogate(highSurrogate) && isLowSurrogate(lowSurrogate)
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
     * [isHighSurrogate]`(highSurrogate(x))` and 
     * [toCodePoint][toCodePoint]`(highSurrogate(x), `[`lowSurrogate(x)`][lowSurrogate]`) == x` are also always `true`.
     */
    fun highSurrogate(codePoint: Int): Char

    /**
     * Returns the trailing surrogate (a low surrogate code unit) of the surrogate pair representing the specified 
     * supplementary character (Unicode code point) in the UTF-16 encoding. 
     * 
     * If the specified character is not a supplementary character, an unspecified char is returned.
     *
     * If [`isSupplementaryCodePoint(x)`][isSupplementaryCodePoint] is `true`, then
     * [isLowSurrogate]`(lowSurrogate(x))` and
     * [toCodePoint][toCodePoint]`(`[`highSurrogate(x)`][highSurrogate], `lowSurrogate(x)) == x` are also always `true`.
     */
    fun lowSurrogate(codePoint: Int): Char

    /**
     * Converts the specified surrogate pair to its supplementary code point value. 
     * 
     * This method does not validate the specified surrogate pair. The caller must validate it using [isSurrogatePair]
     * if necessary.
     */
    fun toCodePoint(highSurrogate: Char, lowSurrogate: Char): Int
}

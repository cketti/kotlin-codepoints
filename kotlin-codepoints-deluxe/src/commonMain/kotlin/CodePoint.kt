package de.cketti.codepoints.deluxe

import de.cketti.codepoints.CodePoints
import kotlin.jvm.JvmInline

/**
 * Represents a Unicode code point.
 * 
 * You can create/retrieve instances of this class by using the following functions:
 * - [Int.toCodePoint]
 * - [Char.toCodePoint]
 * - [String.codePointSequence]
 * - [String.codePointIterator]
 */
@JvmInline
value class CodePoint internal constructor(val value: Int) {
    init {
        require(CodePoints.isValidCodePoint(value)) { "Not a valid code point" }
    }

    /**
     * `true` if this Unicode code point is in the Basic Multilingual Plane (BMP).
     *
     * Such code points can be represented using a single `Char`.
     */
    val isBasic: Boolean
        get() = CodePoints.isBmpCodePoint(value)

    /**
     * `true` if this Unicode code point is in the supplementary character range.
     *
     * In a `String` such a code point is represented using a surrogate pair, i.e. two `Char` values.
     */
    val isSupplementary: Boolean
        get() = CodePoints.isSupplementaryCodePoint(value)

    /**
     * The number of `Char` values needed to represent this Unicode code point.
     *
     * If the specified code point is in the [BMP][CodePoint.isBasic], this property is `1`, otherwise it is `2`.
     */
    val charCount: Int
        get() = CodePoints.charCount(value)

    /**
     * `true` if this code point is a surrogate code unit.
     */
    val isSurrogate: Boolean
        get() = !isSupplementary && value.toChar().isSurrogate()

    /**
     * `true` if this code point is a high surrogate code unit.
     */
    val isHighSurrogate: Boolean
        get() = !isSupplementary && value.toChar().isHighSurrogate()

    /**
     * `true` if this code point is a low surrogate code unit.
     */
    val isLowSurrogate: Boolean
        get() = !isSupplementary && value.toChar().isLowSurrogate()

    /**
     * The leading surrogate (a high surrogate code unit) of the surrogate pair representing this supplementary
     * Unicode code point.
     *
     * If this code point is not a supplementary character, an unspecified `Char` is returned.
     */
    val highSurrogateChar: Char
        get() = CodePoints.highSurrogate(value)

    /**
     * The trailing surrogate (a low surrogate code unit) of the surrogate pair representing this supplementary
     * Unicode code point.
     *
     * If this code point is not a supplementary character, an unspecified `Char` is returned.
     */
    val lowSurrogateChar: Char
        get() = CodePoints.lowSurrogate(value)

    /**
     * Converts this Unicode code point to its UTF-16 representation stored in a char array.
     *
     * If this code point is a BMP (Basic Multilingual Plane or Plane 0) value, the resulting char array has the same 
     * value as [value]. If the specified code point is a supplementary code point, the resulting char array has the 
     * corresponding surrogate pair.
     */
    fun toChars(): CharArray {
        return CodePoints.toChars(value)
    }

    /**
     * Converts this Unicode code point to its UTF-16 representation. 
     * 
     * If this code point is a BMP (Basic Multilingual Plane or Plane 0) value, the same value is stored in 
     * `destination[offset]`, and 1 is returned. If this code point is a supplementary character, its surrogate values 
     * are stored in `destination[offset]` (high-surrogate) and `destination[offset+1]` (low-surrogate), and 2 is 
     * returned.
     */
    fun toChars(destination: CharArray, offset: Int): Int {
        return CodePoints.toChars(value, destination, offset)
    }

    /**
     * Returns the standard Unicode notation of this code point.
     * 
     * "U+" followed by the code point value in hexadecimal (using upper case letters), which is prepended with leading
     * zeros to a minimum of four digits.
     */
    fun toUnicodeNotation(): String {
        return "U+${value.toString(16).uppercase().padStart(4, '0')}"
    }

    /**
     * Returns the string representation of this code point.
     * 
     * The returned string consists of the sequence of characters returned by [toChars].
     */
    override fun toString(): String {
        return toChars().concatToString()
    }
}

/**
 * Returns a [CodePoint] with this value.
 * 
 * Throws [IllegalArgumentException] if this value falls outside the range of valid code points.
 */
fun Int.toCodePoint(): CodePoint {
    return CodePoint(this)
}

/**
 * Returns a [CodePoint] with the same value as this `Char`.
 */
fun Char.toCodePoint(): CodePoint {
    return CodePoint(this.code)
}

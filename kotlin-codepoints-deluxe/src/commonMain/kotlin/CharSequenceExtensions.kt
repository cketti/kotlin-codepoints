@file:Suppress(
    "INVISIBLE_MEMBER", // Required to be able to use kotlin.internal.HidesMembers
    "INVISIBLE_REFERENCE", // Required to be able to use kotlin.internal.HidesMembers
)
package de.cketti.codepoints.deluxe

import de.cketti.codepoints.CodePoints
import de.cketti.codepoints.codePointAt as intCodePointAt
import de.cketti.codepoints.codePointBefore as intCodePointBefore

/**
 * Returns the Unicode code point at the specified index.
 *
 * The `index` parameter is the regular `CharSequence` index, i.e. the number of `Char`s from the start of the character
 * sequence.
 *
 * If the `index` is out of bounds of this character sequence, this method throws an [IndexOutOfBoundsException].
 * 
 * See [codePointAt][intCodePointAt].
 * ```
 */
@kotlin.internal.HidesMembers
fun CharSequence.codePointAt(index: Int): CodePoint {
    return intCodePointAt(index).toCodePoint()
}

/**
 * Returns the Unicode code point before the specified index.
 *
 * The `index` parameter is the regular `CharSequence` index, i.e. the number of `Char`s from the start of the character
 * sequence.
 *
 * If the value `index - 1` is out of bounds of this character sequence, this method throws an
 * [IndexOutOfBoundsException].
 * 
 * See [codePointBefore][intCodePointBefore].
 */
fun CharSequence.codePointBefore(index: Int): CodePoint {
    return intCodePointBefore(index).toCodePoint()
}

/**
 * Sequence of [CodePoint]s in this character sequence.
 */
fun CharSequence.codePointSequence(): CodePointSequence {
    return CodePointSequence(this)
}

/**
 * Iterator for [CodePoint]s in this character sequence.
 */
fun CharSequence.codePointIterator(startIndex: Int = 0, endIndex: Int = length): CodePointIterator {
    return CodePointIterator(this, startIndex, endIndex)
}

/**
 * Performs given [block] for each [CodePoint] in the [CharSequence]
 * between [startIndex] (inclusive) and [endIndex] (exclusive).
 *
 * @see forEachCodePointIndexed
 */
inline fun CharSequence.forEachCodePoint(
    startIndex: Int = 0,
    endIndex: Int = length,
    block: (codePoint: CodePoint) -> Unit,
) = forEachCodePointIndexed(startIndex, endIndex) { _, codePoint -> block(codePoint) }

/**
 * Performs given [block] for each [CodePoint] in the [CharSequence]
 * between [startIndex] (inclusive) and [endIndex] (exclusive).
 * Provides the start index for the given codepoint
 *
 * @param startIndex index of the first codepoint in CharSequence to start with (defaults to `0`)
 * @param endIndex index of the last codepoint in CharSequence to stop at (defaults to `length`)
 */
inline fun CharSequence.forEachCodePointIndexed(
    startIndex: Int = 0,
    endIndex: Int = length,
    block: (index: Int, codePoint: CodePoint) -> Unit,
) {
    require(startIndex <= endIndex) {
        "startIndex ($startIndex) must be less than or equal to endIndex ($endIndex)"
    }
    require(endIndex <= length) {
        "endIndex ($endIndex) must be less than or equal to char sequence's length ($length)"
    }
    val str = this
    var index = startIndex
    while (index < endIndex) {
        val codePointStartIndex = index
        val firstChar = str[index]
        index++
        if (firstChar.isHighSurrogate() && index < endIndex) {
            val nextChar = str[index]
            if (nextChar.isLowSurrogate()) {
                block(codePointStartIndex, CodePoints.toCodePoint(firstChar, nextChar).toCodePoint())
                index++
                continue
            }
        }
        block(codePointStartIndex, firstChar.toCodePoint())
    }
}
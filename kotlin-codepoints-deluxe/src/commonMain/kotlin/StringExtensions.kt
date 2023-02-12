@file:Suppress(
    "INVISIBLE_MEMBER", // Required to be able to use kotlin.internal.HidesMembers
    "INVISIBLE_REFERENCE", // Required to be able to use kotlin.internal.HidesMembers
)
package de.cketti.codepoints.deluxe

import de.cketti.codepoints.codePointAt as intCodePointAt
import de.cketti.codepoints.codePointBefore as intCodePointBefore

/**
 * Returns the Unicode code point at the specified index.
 *
 * The `index` parameter is the regular `String` index, i.e. the number of `Char`s from the start of the string.
 *
 * If the `index` is out of bounds of this string, this method throws an [IndexOutOfBoundsException].
 * 
 * See [codePointAt][intCodePointAt].
 * ```
 */
@kotlin.internal.HidesMembers
fun String.codePointAt(index: Int): CodePoint {
    return intCodePointAt(index).toCodePoint()
}

/**
 * Returns the Unicode code point before the specified index.
 *
 * The `index` parameter is the regular `String` index, i.e. the number of `Char`s from the start of the string.
 *
 * If the value `index - 1` is out of bounds of this string, this method throws an [IndexOutOfBoundsException].
 * 
 * See [codePointBefore][intCodePointBefore].
 */
fun String.codePointBefore(index: Int): CodePoint {
    return intCodePointBefore(index).toCodePoint()
}

/**
 * Sequence of [CodePoint]s in this string.
 */
fun String.codePointSequence(): CodePointSequence {
    return CodePointSequence(this)
}

/**
 * Iterator for [CodePoint]s in this string.
 */
fun String.codePointIterator(startIndex: Int = 0, endIndex: Int = length): CodePointIterator {
    return CodePointIterator(this, startIndex, endIndex)
}

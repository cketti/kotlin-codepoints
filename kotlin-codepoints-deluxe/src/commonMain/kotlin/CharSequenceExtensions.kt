@file:Suppress(
    "INVISIBLE_MEMBER", // Required to be able to use kotlin.internal.HidesMembers
    "INVISIBLE_REFERENCE", // Required to be able to use kotlin.internal.HidesMembers
)
package de.cketti.codepoints.deluxe

import de.cketti.codepoints.codePointAt as intCodePointAt
import de.cketti.codepoints.codePointBefore as intCodePointBefore
import de.cketti.codepoints.forEachCodePoint as intForEachCodePoint
import de.cketti.codepoints.forEachCodePointIndexed as intForEachCodePointIndexed

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
 * Performs given [action] for each [CodePoint] in the [CharSequence].
 *
 * @see forEachCodePointIndexed
 */
inline fun CharSequence.forEachCodePoint(
    action: (codePoint: CodePoint) -> Unit,
) = intForEachCodePoint { action(it.toCodePoint()) }

/**
 * Performs given [action] for each [CodePoint] in the [CharSequence].
 * Provides the start index for the given codepoint
 */
inline fun CharSequence.forEachCodePointIndexed(
    action: (index: Int, codePoint: CodePoint) -> Unit,
) = intForEachCodePointIndexed { index, codePoint -> action(index, codePoint.toCodePoint()) }
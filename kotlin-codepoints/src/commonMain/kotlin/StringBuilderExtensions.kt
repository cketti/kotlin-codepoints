package de.cketti.codepoints

import de.cketti.codepoints.CodePoints.highSurrogate
import de.cketti.codepoints.CodePoints.isBmpCodePoint
import de.cketti.codepoints.CodePoints.isSupplementaryCodePoint
import de.cketti.codepoints.CodePoints.lowSurrogate
import de.cketti.codepoints.CodePoints.toChars

/**
 * The code point at the specified index is set to `codePoint`. 
 * 
 * This sequence is altered to represent a new character sequence that is identical to the old character sequence, 
 * except that the UTF-16 representation of the code point at position `index` is replaced by the UTF-16 representation 
 * of `codePoint`.
 * If the old and the new code point differ in [charCount][CodePoints.charCount], the length of this character sequence 
 * grows or shrinks by one character.
 *
 * @throws IndexOutOfBoundsException if [index] is out of bounds of this string builder.
 */
fun StringBuilder.setCodePointAt(index: Int, codePoint: Int): StringBuilder = apply {
    val oldCodePoint = codePointAt(index)
    if (isBmpCodePoint(oldCodePoint)) {
        if (isBmpCodePoint(codePoint)) {
            set(index, codePoint.toChar())
        } else {
            set(index, highSurrogate(codePoint))
            insert(index + 1, lowSurrogate(codePoint))
        }
    } else {
        if (isBmpCodePoint(codePoint)) {
            set(index, codePoint.toChar())
            deleteAt(index + 1)
        } else {
            set(index, highSurrogate(codePoint))
            set(index + 1, lowSurrogate(codePoint))
        }
    }
}

/**
 * Insert `codePoint` at the specified index.
 *
 * The UTF-16 representation of `codePoint` is inserted into this sequence at the position `index`, moving up any 
 * characters originally above that position and increasing the length of this sequence by the code point's 
 * [charCount][CodePoints.charCount].
 * 
 * @throws IndexOutOfBoundsException if [index] is less than zero or greater than the length of this string builder.
 */
fun StringBuilder.insertCodePointAt(index: Int, codePoint: Int): StringBuilder = apply {
    if (isBmpCodePoint(codePoint)) {
        insert(index, codePoint.toChar())
    } else {
        insert(index, toChars(codePoint))
    }
}

/**
 * Removes the code point at the specified index.
 *
 * If the code point at the position `index` is encoded using a surrogate pair, both `Char´s of the surrogate pair are
 * removed.
 *
 * @throws IndexOutOfBoundsException if [index] is out of bounds of this string builder.
 */
fun StringBuilder.deleteCodePointAt(index: Int): StringBuilder = apply {
    val codePoint = codePointAt(index)
    if (isBmpCodePoint(codePoint)) {
        deleteAt(index)
    } else {
        deleteRange(index, index + 2)
    }
}

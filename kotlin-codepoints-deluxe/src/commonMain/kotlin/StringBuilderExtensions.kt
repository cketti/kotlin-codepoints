package de.cketti.codepoints.deluxe

import de.cketti.codepoints.setCodePointAt as intSetCodePointAt
import de.cketti.codepoints.insertCodePointAt as intInsertCodePointAt

/**
 * The code point at the specified index is set to `codePoint`. 
 * 
 * This sequence is altered to represent a new character sequence that is identical to the old character sequence, 
 * except that the UTF-16 representation of the code point at position `index` is replaced by the UTF-16 representation 
 * of `codePoint`.
 * If the old and the new code point differ in [charCount][CodePoint.charCount], the length of this character sequence 
 * grows or shrinks by one character.
 *
 * @throws IndexOutOfBoundsException if [index] is out of bounds of this string builder.
 */
fun StringBuilder.setCodePointAt(index: Int, codePoint: CodePoint): StringBuilder { 
    return intSetCodePointAt(index, codePoint.value)
}

/**
 * Insert `codePoint` at the specified index.
 *
 * The UTF-16 representation of `codePoint` is inserted into this sequence at the position `index`, moving up any 
 * characters originally above that position and increasing the length of this sequence by the code point's 
 * [charCount][CodePoint.charCount].
 * 
 * @throws IndexOutOfBoundsException if [index] is less than zero or greater than the length of this string builder.
 */
fun StringBuilder.insertCodePointAt(index: Int, codePoint: CodePoint): StringBuilder {
    return intInsertCodePointAt(index, codePoint.value)
}

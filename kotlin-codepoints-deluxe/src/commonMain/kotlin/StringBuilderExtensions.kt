package de.cketti.codepoints.deluxe

import de.cketti.codepoints.appendCodePoint as intAppendCodePoint

/**
 * Appends the string representation of the [codePoint] argument to this sequence.
 *
 * The argument is appended to the contents of this sequence.
 * The length of this sequence increases by [CodePoint.charCount].
 *
 * The overall effect is exactly as if the argument were converted to a char array by the function
 * [CodePoint.toChars] and the characters in that array were then appended to this sequence.
 */
fun StringBuilder.appendCodePoint(codePoint: CodePoint): StringBuilder = intAppendCodePoint(codePoint.value)

package de.cketti.codepoints.deluxe

import de.cketti.codepoints.CodePoints
import de.cketti.codepoints.appendCodePoint as intAppendCodePoint

/**
 * Appends the string representation of the [codePoint] argument to this Appendable and returns this instance.
 *
 * To append the codepoint, [Appendable.append(Char)][Appendable.append] is called [CodePoints.charCount] times.
 *
 * The overall effect is exactly as if the argument were converted to a char array by the function
 * [CodePoints.toChars] and the characters in that array were then appended to this Appendable.
 */
fun <T : Appendable> T.appendCodePoint(codePoint: CodePoint): T = intAppendCodePoint(codePoint.value)

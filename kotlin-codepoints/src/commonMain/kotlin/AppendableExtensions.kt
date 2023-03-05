package de.cketti.codepoints

import de.cketti.codepoints.CodePoints.highSurrogate
import de.cketti.codepoints.CodePoints.isBmpCodePoint
import de.cketti.codepoints.CodePoints.lowSurrogate

/**
 * Appends the string representation of the [codePoint] argument to this Appendable and returns this instance.
 *
 * To append the codepoint, [Appendable.append(Char)][Appendable.append] is called [CodePoints.charCount] times.
 *
 * The overall effect is exactly as if the argument were converted to a char array by the function
 * [CodePoints.toChars] and the characters in that array were then appended to this Appendable.
 */
fun <T : Appendable> T.appendCodePoint(codePoint: Int): T = apply {
    if (isBmpCodePoint(codePoint)) {
        append(codePoint.toChar())
    } else {
        append(highSurrogate(codePoint))
        append(lowSurrogate(codePoint))
    }
}

package de.cketti.codepoints

import de.cketti.codepoints.CodePoints.highSurrogate
import de.cketti.codepoints.CodePoints.isBmpCodePoint
import de.cketti.codepoints.CodePoints.lowSurrogate
import kotlin.text.StringBuilder

actual fun StringBuilder.appendCodePoint(codePoint: Int): StringBuilder = apply {
    if (isBmpCodePoint(codePoint)) {
        append(codePoint.toChar())
    } else {
        append(highSurrogate(codePoint))
        append(lowSurrogate(codePoint))
    }
}

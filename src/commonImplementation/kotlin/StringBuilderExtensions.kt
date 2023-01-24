package de.cketti.codepoints

import kotlin.text.StringBuilder
import de.cketti.codepoints.internal.appendCodePoint as commonAppendCodePoint

actual fun StringBuilder.appendCodePoint(codePoint: Int): StringBuilder = apply {
    commonAppendCodePoint(this, codePoint)
}

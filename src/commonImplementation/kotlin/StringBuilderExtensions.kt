package de.cketti.codepoints

import kotlin.text.StringBuilder

actual fun StringBuilder.appendCodePoint(codePoint: Int): StringBuilder = apply {
    CommonStringBuilderFunctions.appendCodePoint(this, codePoint)
}

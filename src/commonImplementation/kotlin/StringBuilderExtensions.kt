package de.cketti.codepoints

import de.cketti.codepoints.internal.CommonStringBuilderFunctions
import kotlin.text.StringBuilder

actual fun StringBuilder.appendCodePoint(codePoint: Int): StringBuilder = apply {
    CommonStringBuilderFunctions.appendCodePoint(this, codePoint)
}

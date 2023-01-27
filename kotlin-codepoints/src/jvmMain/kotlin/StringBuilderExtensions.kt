@file:Suppress(
    "NOTHING_TO_INLINE",
    "EXTENSION_SHADOWED_BY_MEMBER",
)

package de.cketti.codepoints

import kotlin.text.StringBuilder

actual inline fun StringBuilder.appendCodePoint(codePoint: Int): StringBuilder {
    return appendCodePoint(codePoint)!!
}

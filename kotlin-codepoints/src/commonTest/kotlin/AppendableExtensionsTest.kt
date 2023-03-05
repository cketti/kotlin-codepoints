package de.cketti.codepoints

import kotlin.test.assertEquals
import kotlin.test.Test

class AppendableExtensionsTest {
    @Test
    fun appendCodePoint() {
        val actual = buildString {
            appendCodePoint('a'.code)
            append('b')
            appendCodePoint("\uFFFF".codePointAt(0))
            append('c')
            appendCodePoint("\uD83E\uDD95".codePointAt(0))
        }
        assertEquals("ab\uFFFFc\uD83E\uDD95", actual)
    }
}

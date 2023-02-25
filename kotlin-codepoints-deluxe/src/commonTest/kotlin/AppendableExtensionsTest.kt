package de.cketti.codepoints.deluxe

import kotlin.test.assertEquals
import kotlin.test.Test

class AppendableExtensionsTest {
    @Test
    fun appendCodePoint() {
        val actual = buildString {
            appendCodePoint('a'.toCodePoint())
            append('b')
            appendCodePoint('\uFFFF'.toCodePoint())
            append('c')
            appendCodePoint("\uD83E\uDD95".codePointAt(0))
        }
        
        assertEquals("ab\uFFFFc\uD83E\uDD95", actual)
    }
}

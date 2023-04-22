package de.cketti.codepoints.deluxe

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class StringBuilderExtensionsTest {
    @Test
    fun setCodePointAt() {
        val stringBuilder = StringBuilder().apply {
            append('a')
            append("\uD83E\uDD95")
            append('c')
        }

        stringBuilder.setCodePointAt(0, 'A'.toCodePoint())
        assertEquals(stringBuilder.toString(), "A\uD83E\uDD95c")

        stringBuilder.setCodePointAt(1, "\uD83E\uDD96".codePointAt(0))
        assertEquals(stringBuilder.toString(), "A\uD83E\uDD96c")

        stringBuilder.setCodePointAt(3, 'C'.toCodePoint())
        assertEquals(stringBuilder.toString(), "A\uD83E\uDD96C")

        stringBuilder.setCodePointAt(0, "\uD83E\uDD95".codePointAt(0))
        assertEquals(stringBuilder.toString(), "\uD83E\uDD95\uD83E\uDD96C")

        stringBuilder.setCodePointAt(2, 'Y'.toCodePoint())
        assertEquals(stringBuilder.toString(), "\uD83E\uDD95YC")

        stringBuilder.setCodePointAt(3, "\uD83E\uDD96".codePointAt(0))
        assertEquals(stringBuilder.toString(), "\uD83E\uDD95Y\uD83E\uDD96")

        stringBuilder.setCodePointAt(2, "\uD83D\uDC4B".codePointAt(0))
        assertEquals(stringBuilder.toString(), "\uD83E\uDD95\uD83D\uDC4B\uD83E\uDD96")

        stringBuilder.setCodePointAt(0, "\uD83D\uDC56".codePointAt(0))
        assertEquals(stringBuilder.toString(), "\uD83D\uDC56\uD83D\uDC4B\uD83E\uDD96")

        stringBuilder.setCodePointAt(4, "\uD83D\uDC57".codePointAt(0))
        assertEquals(stringBuilder.toString(), "\uD83D\uDC56\uD83D\uDC4B\uD83D\uDC57")

        stringBuilder.setCodePointAt(0, 'X'.toCodePoint())
        assertEquals(stringBuilder.toString(), "X\uD83D\uDC4B\uD83D\uDC57")

        stringBuilder.setCodePointAt(3, 'Z'.toCodePoint())
        assertEquals(stringBuilder.toString(), "X\uD83D\uDC4BZ")
    }

    @Test
    fun setCodePointAt_between_surrogate_pairs() {
        val stringBuilder = StringBuilder().apply {
            append("\uD83E\uDD95")
        }

        stringBuilder.setCodePointAt(1, "\uD83E\uDD96".codePointAt(0))
        assertEquals(stringBuilder.toString(), "\uD83E\uD83E\uDD96")

        stringBuilder.setCodePointAt(2, 'X'.toCodePoint())
        assertEquals(stringBuilder.toString(), "\uD83E\uD83EX")
    }

    @Test
    fun setCodePointAt_unmatched_surrogates() {
        val stringBuilder = StringBuilder().apply {
            append("\uDD95\uD83E\uD83E")
        }

        stringBuilder.setCodePointAt(0, 'X'.toCodePoint())
        assertEquals(stringBuilder.toString(), "X\uD83E\uD83E")

        stringBuilder.setCodePointAt(1, 'Y'.toCodePoint())
        assertEquals(stringBuilder.toString(), "XY\uD83E")

        stringBuilder.setCodePointAt(2, "\uD83D\uDC4B".codePointAt(0))
        assertEquals(stringBuilder.toString(), "XY\uD83D\uDC4B")
    }

    @Test
    fun setCodePointAt_with_invalid_index() {
        val stringBuilder = StringBuilder().apply { 
            append(" ")
        }
        
        assertFailsWith<IndexOutOfBoundsException> {
            stringBuilder.setCodePointAt(-1, 'a'.toCodePoint())
        }
        
        assertFailsWith<IndexOutOfBoundsException> {
            stringBuilder.setCodePointAt(1, 'a'.toCodePoint())
        }
    }
    
    @Test
    fun insertCodePointAt() {
        val stringBuilder = StringBuilder()
        
        stringBuilder.insertCodePointAt(0, "\uD83E\uDD95".codePointAt(0))
        assertEquals(stringBuilder.toString(), "\uD83E\uDD95")
        
        stringBuilder.insertCodePointAt(0, 'a'.toCodePoint())
        assertEquals(stringBuilder.toString(), "a\uD83E\uDD95")
        
        stringBuilder.insertCodePointAt(3, "\uD83E\uDD96".codePointAt(0))
        assertEquals(stringBuilder.toString(), "a\uD83E\uDD95\uD83E\uDD96")
    }

    @Test
    fun insertCodePointAt_between_surrogate_pairs() {
        val stringBuilder = StringBuilder().apply {
            append("\uD83E\uDD95")
        }
        
        stringBuilder.insertCodePointAt(1, "\uD83E\uDD96".codePointAt(0))
        assertEquals(stringBuilder.toString(), "\uD83E\uD83E\uDD96\uDD95")
        
        stringBuilder.insertCodePointAt(2, 'a'.toCodePoint())
        assertEquals(stringBuilder.toString(), "\uD83E\uD83Ea\uDD96\uDD95")
    }

    @Test
    fun insertCodePointAt_with_invalid_index() {
        val stringBuilder = StringBuilder()

        assertFailsWith<IndexOutOfBoundsException> {
            stringBuilder.insertCodePointAt(-1, 'a'.toCodePoint())
        }

        assertFailsWith<IndexOutOfBoundsException> {
            stringBuilder.insertCodePointAt(1, 'a'.toCodePoint())
        }
    }
}

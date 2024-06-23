package de.cketti.codepoints.deluxe

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CodePointTest {
    @Test
    fun toCodePoint() {
        assertEquals(0, 0.toCodePoint().value)
        assertEquals(0xFFFF, 0xFFFF.toCodePoint().value)
        assertEquals(0x10000, 0x10000.toCodePoint().value)
        assertEquals(0x10FFFF, 0x10FFFF.toCodePoint().value)

        assertFailsWith<IllegalArgumentException> {
            (-1).toCodePoint()
        }
        assertFailsWith<IllegalArgumentException> {
            0x110000.toCodePoint()
        }
    }

    @Test
    fun isBasic() {
        assertTrue(0.toCodePoint().isBasic)
        assertTrue(0xFFFF.toCodePoint().isBasic)

        assertFalse(0x10000.toCodePoint().isBasic)
        assertFalse(0x10FFFF.toCodePoint().isBasic)
    }

    @Test
    fun isSupplementary() {
        assertTrue(0x10000.toCodePoint().isSupplementary)
        assertTrue(0x10FFFF.toCodePoint().isSupplementary)

        assertFalse(0.toCodePoint().isSupplementary)
        assertFalse(0xFFFF.toCodePoint().isSupplementary)
    }

    @Test
    fun charCount() {
        assertEquals(1, 0.toCodePoint().charCount)
        assertEquals(1, 0xFFFF.toCodePoint().charCount)

        assertEquals(2, 0x10000.toCodePoint().charCount)
        assertEquals(2, 0x10FFFF.toCodePoint().charCount)
    }

    @Test
    fun isSurrogate() {
        assertTrue(0xD800.toCodePoint().isSurrogate)
        assertTrue(0xDFFF.toCodePoint().isSurrogate)

        assertFalse(0.toCodePoint().isSurrogate)
        assertFalse(0xD7FF.toCodePoint().isSurrogate)
        assertFalse(0xC000.toCodePoint().isSurrogate)
        assertFalse(0x10000.toCodePoint().isSurrogate)
    }

    @Test
    fun isHighSurrogate() {
        assertTrue(0xD800.toCodePoint().isHighSurrogate)
        assertTrue(0xDBFF.toCodePoint().isHighSurrogate)

        assertFalse(0.toCodePoint().isHighSurrogate)
        assertFalse(0xD7FF.toCodePoint().isHighSurrogate)
        assertFalse(0xDC00.toCodePoint().isHighSurrogate)
        assertFalse(0xDFFF.toCodePoint().isHighSurrogate)
        assertFalse(0x10000.toCodePoint().isHighSurrogate)
    }

    @Test
    fun isLowSurrogate() {
        assertTrue(0xDC00.toCodePoint().isLowSurrogate)
        assertTrue(0xDFFF.toCodePoint().isLowSurrogate)

        assertFalse(0.toCodePoint().isLowSurrogate)
        assertFalse(0xDBFF.toCodePoint().isLowSurrogate)
        assertFalse(0xC000.toCodePoint().isLowSurrogate)
        assertFalse(0x10000.toCodePoint().isLowSurrogate)
    }

    @Test
    fun highSurrogate() {
        assertEquals('\uD83E', 0x1F995.toCodePoint().highSurrogateChar)
    }

    @Test
    fun lowSurrogate() {
        assertEquals('\uDD95', 0x1F995.toCodePoint().lowSurrogateChar)
    }

    @Test
    fun toChars() {
        assertContentEquals(charArrayOf('a'), 'a'.toCodePoint().toChars())
        assertContentEquals(charArrayOf('\uFFFF'), 0xFFFF.toCodePoint().toChars())
        assertContentEquals(charArrayOf('\uD83E', '\uDD95'), "\uD83E\uDD95".codePointAt(0).toChars())
    }

    @Test
    fun toCharsDestination() {
        val chars = charArrayOf('z', 'z', 'z')

        'a'.toCodePoint().toChars(chars, 0)
        assertContentEquals(charArrayOf('a', 'z', 'z'), chars)

        'a'.toCodePoint().toChars(chars, 2)
        assertContentEquals(charArrayOf('a', 'z', 'a'), chars)

        "\uD83E\uDD95".codePointAt(0).toChars(chars, 0)
        assertContentEquals(charArrayOf('\uD83E', '\uDD95', 'a'), chars)

        "\uD83E\uDD95".codePointAt(0).toChars(chars, 1)
        assertContentEquals(charArrayOf('\uD83E', '\uD83E', '\uDD95'), chars)
    }

    @Test
    fun toCharsDestinationTooSmall() {
        val chars = charArrayOf()

        assertFailsWith<IndexOutOfBoundsException> {
            'a'.toCodePoint().toChars(chars, 0)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            "\uD83E\uDD95".codePointAt(0).toChars(chars, 0)
        }
    }

    @Test
    fun toCharsDestinationOffsetInvalid() {
        val chars = charArrayOf('z', 'z')

        assertFailsWith<IndexOutOfBoundsException> {
            'a'.toCodePoint().toChars(chars, 2)
        }
        assertContentEquals(charArrayOf('z', 'z'), chars)

        assertFailsWith<IndexOutOfBoundsException> {
            'a'.toCodePoint().toChars(chars, -1)
        }
        assertContentEquals(charArrayOf('z', 'z'), chars)

        assertFailsWith<IndexOutOfBoundsException> {
            "\uD83E\uDD95".codePointAt(0).toChars(chars, 2)
        }
        assertContentEquals(charArrayOf('z', 'z'), chars)

        assertFailsWith<IndexOutOfBoundsException> {
            "\uD83E\uDD95".codePointAt(0).toChars(chars, -1)
        }
        // We're bug-compatible with the Java stdlib implementation 
        assertContentEquals(charArrayOf('\uDD95', 'z'), chars)
    }

    @Test
    fun toCharsDestinationOffsetTooSmall() {
        val chars = charArrayOf('z', 'z')

        assertFailsWith<IndexOutOfBoundsException> {
            "\uD83E\uDD95".codePointAt(0).toChars(chars, 1)
        }
        assertContentEquals(charArrayOf('z', 'z'), chars)
    }

    @Test
    fun toUnicodeNotation() {
        assertEquals("U+0000", 0.toCodePoint().toUnicodeNotation())
        assertEquals("U+0061", 'a'.toCodePoint().toUnicodeNotation())
        assertEquals("U+0FFF", 0xFFF.toCodePoint().toUnicodeNotation())
        assertEquals("U+FFFF", 0xFFFF.toCodePoint().toUnicodeNotation())
        assertEquals("U+1F995", 0x1F995.toCodePoint().toUnicodeNotation())
    }

    @Test
    fun toString_test() {
        assertEquals("a", 'a'.toCodePoint().toString())
        assertEquals("\uD83E\uDD95", 0x1F995.toCodePoint().toString())
    }
}

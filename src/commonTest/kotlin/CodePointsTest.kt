package de.cketti.codepoints

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CodePointsTest {
    @Test
    fun isValidCodePoint() {
        assertTrue(CodePoints.isValidCodePoint(0))
        assertTrue(CodePoints.isValidCodePoint(0xFFFF))
        assertTrue(CodePoints.isValidCodePoint(0x10000))
        assertTrue(CodePoints.isValidCodePoint(0x10FFFF))
        
        assertFalse(CodePoints.isValidCodePoint(-1))
        assertFalse(CodePoints.isValidCodePoint(0x110000))
    }

    @Test
    fun isBmpCodePoint() {
        assertTrue(CodePoints.isBmpCodePoint(0))
        assertTrue(CodePoints.isBmpCodePoint(0xFFFF))

        assertFalse(CodePoints.isBmpCodePoint(0x10000))
        assertFalse(CodePoints.isBmpCodePoint(0x10FFFF))

        assertFalse(CodePoints.isBmpCodePoint(-1))
        assertFalse(CodePoints.isBmpCodePoint(0x110000))
    }

    @Test
    fun isSupplementaryCodePoint() {
        assertTrue(CodePoints.isSupplementaryCodePoint(0x10000))
        assertTrue(CodePoints.isSupplementaryCodePoint(0x10FFFF))

        assertFalse(CodePoints.isSupplementaryCodePoint(0))
        assertFalse(CodePoints.isSupplementaryCodePoint(0xFFFF))

        assertFalse(CodePoints.isSupplementaryCodePoint(-1))
        assertFalse(CodePoints.isSupplementaryCodePoint(0x110000))
    }

    @Test
    fun charCount() {
        assertEquals(1, CodePoints.charCount(0))
        assertEquals(1, CodePoints.charCount(0xFFFF))

        assertEquals(2, CodePoints.charCount(0x10000))
        assertEquals(2, CodePoints.charCount(0x10FFFF))
    }

    @Test
    fun isSurrogatePair() {
        assertTrue(CodePoints.isSurrogatePair('\uD800', '\uDC00'))
        assertTrue(CodePoints.isSurrogatePair('\uDBFF', '\uDC00'))
        assertTrue(CodePoints.isSurrogatePair('\uD800', '\uDFFF'))
        assertTrue(CodePoints.isSurrogatePair('\uDBFF', '\uDFFF'))

        assertFalse(CodePoints.isSurrogatePair('\uDC00', '\uD800'))
        assertFalse(CodePoints.isSurrogatePair('\uDC00', '\uDBFF'))
        assertFalse(CodePoints.isSurrogatePair('\uDFFF', '\uD800'))
        assertFalse(CodePoints.isSurrogatePair('\uDFFF', '\uDBFF'))
        assertFalse(CodePoints.isSurrogatePair('\uD800', 'a'))
        assertFalse(CodePoints.isSurrogatePair('a', '\uDC00'))
    }

    @Test
    fun highSurrogate() {
        assertEquals('\uD83E', CodePoints.highSurrogate(0x1F995))
    }

    @Test
    fun lowSurrogate() {
        assertEquals('\uDD95', CodePoints.lowSurrogate(0x1F995))
    }

    @Test
    fun toCodePoint() {
        assertEquals(0x1F995, CodePoints.toCodePoint('\uD83E', '\uDD95'))
    }

    @Test
    fun toChars() {
        assertContentEquals(charArrayOf('a'), CodePoints.toChars('a'.code))
        assertContentEquals(charArrayOf('\uFFFF'), CodePoints.toChars(0xFFFF))
        assertContentEquals(charArrayOf('\uD83E', '\uDD95'), CodePoints.toChars("\uD83E\uDD95".codePointAt(0)))
    }

    @Test
    fun toCharsDestination() {
        val chars = charArrayOf('z', 'z', 'z')

        CodePoints.toChars('a'.code, chars, 0)
        assertContentEquals(charArrayOf('a', 'z', 'z'), chars)

        CodePoints.toChars('a'.code, chars, 2)
        assertContentEquals(charArrayOf('a', 'z', 'a'), chars)

        CodePoints.toChars("\uD83E\uDD95".codePointAt(0), chars, 0)
        assertContentEquals(charArrayOf('\uD83E', '\uDD95', 'a'), chars)

        CodePoints.toChars("\uD83E\uDD95".codePointAt(0), chars, 1)
        assertContentEquals(charArrayOf('\uD83E', '\uD83E', '\uDD95'), chars)
    }

    @Test
    fun toCharsDestinationTooSmall() {
        val chars = charArrayOf()

        assertFailsWith<IndexOutOfBoundsException> {
            CodePoints.toChars('a'.code, chars, 0)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            CodePoints.toChars("\uD83E\uDD95".codePointAt(0), chars, 0)
        }
    }

    @Test
    fun toCharsDestinationOffsetInvalid() {
        val chars = charArrayOf('z', 'z')

        assertFailsWith<IndexOutOfBoundsException> {
            CodePoints.toChars('a'.code, chars, 2)
        }
        assertContentEquals(charArrayOf('z', 'z'), chars)

        assertFailsWith<IndexOutOfBoundsException> {
            CodePoints.toChars('a'.code, chars, -1)
        }
        assertContentEquals(charArrayOf('z', 'z'), chars)

        assertFailsWith<IndexOutOfBoundsException> {
            CodePoints.toChars("\uD83E\uDD95".codePointAt(0), chars, 2)
        }
        assertContentEquals(charArrayOf('z', 'z'), chars)

        assertFailsWith<IndexOutOfBoundsException> {
            CodePoints.toChars("\uD83E\uDD95".codePointAt(0), chars, -1)
        }
        // We're bug-compatible with the Java stdlib implementation 
        assertContentEquals(charArrayOf('\uDD95', 'z'), chars)
    }

    @Test
    fun toCharsDestinationOffsetTooSmall() {
        val chars = charArrayOf('z', 'z')

        assertFailsWith<IndexOutOfBoundsException> {
            CodePoints.toChars("\uD83E\uDD95".codePointAt(0), chars, 1)
        }
        assertContentEquals(charArrayOf('z', 'z'), chars)
    }
}

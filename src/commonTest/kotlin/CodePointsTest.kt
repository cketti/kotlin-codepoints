package de.cketti.codepoints

import kotlin.test.Test
import kotlin.test.assertEquals
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
    fun isSurrogate() {
        assertTrue(CodePoints.isSurrogate('\uD800'))
        assertTrue(CodePoints.isSurrogate('\uDBFF'))
        assertTrue(CodePoints.isSurrogate('\uDC00'))
        assertTrue(CodePoints.isSurrogate('\uDFFF'))

        assertFalse(CodePoints.isSurrogate('a'))
        assertFalse(CodePoints.isSurrogate(0xFFFF.toChar()))
        assertFalse(CodePoints.isSurrogate('\uD7FF'))
        assertFalse(CodePoints.isSurrogate('\uE000'))
    }

    @Test
    fun isHighSurrogate() {
        assertTrue(CodePoints.isHighSurrogate('\uD800'))
        assertTrue(CodePoints.isHighSurrogate('\uDBFF'))
        
        assertFalse(CodePoints.isHighSurrogate('\uDC00'))
        assertFalse(CodePoints.isHighSurrogate('\uDFFF'))

        assertFalse(CodePoints.isHighSurrogate('a'))
        assertFalse(CodePoints.isHighSurrogate(0xFFFF.toChar()))
        assertFalse(CodePoints.isHighSurrogate('\uD7FF'))
        assertFalse(CodePoints.isHighSurrogate('\uE000'))
    }

    @Test
    fun isLowSurrogate() {
        assertTrue(CodePoints.isLowSurrogate('\uDC00'))
        assertTrue(CodePoints.isLowSurrogate('\uDFFF'))

        assertFalse(CodePoints.isLowSurrogate('\uD800'))
        assertFalse(CodePoints.isLowSurrogate('\uDBFF'))
        
        assertFalse(CodePoints.isLowSurrogate('a'))
        assertFalse(CodePoints.isLowSurrogate(0xFFFF.toChar()))
        assertFalse(CodePoints.isLowSurrogate('\uD7FF'))
        assertFalse(CodePoints.isLowSurrogate('\uE000'))
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
}

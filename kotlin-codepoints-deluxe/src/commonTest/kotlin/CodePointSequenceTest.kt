package de.cketti.codepoints.deluxe

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CodePointSequenceTest {
    @Test
    fun codePointSequence() {
        val codePoints = "a\uD83E\uDD95b\uD83E\uDD96c".codePointSequence().map { it.value }.toList()
        
        assertEquals(listOf(0x0061, 0x1F995, 0x0062, 0x1F996, 0x0063), codePoints)
    }

    @Test
    fun codePointIterator() {
        val iterator = "a\uD83E\uDD95b".codePointIterator()
        
        assertTrue(iterator.hasNext())
        assertEquals('a'.toCodePoint(), iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals("\uD83E\uDD95".codePointAt(0), iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals('b'.toCodePoint(), iterator.next())
        assertFalse(iterator.hasNext())
        assertFailsWith<IndexOutOfBoundsException> {
            iterator.next()
        }
    }

    @Test
    fun codePointIterator_with_start_index() {
        val iterator = "a\uD83E\uDD95b".codePointIterator(startIndex = 1)

        assertTrue(iterator.hasNext())
        assertEquals("\uD83E\uDD95".codePointAt(0), iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals('b'.toCodePoint(), iterator.next())
        assertFalse(iterator.hasNext())
        assertFailsWith<IndexOutOfBoundsException> {
            iterator.next()
        }
    }

    @Test
    fun codePointIterator_with_end_index() {
        val iterator = "a\uD83E\uDD95b".codePointIterator(endIndex = 3)

        assertTrue(iterator.hasNext())
        assertEquals('a'.toCodePoint(), iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals("\uD83E\uDD95".codePointAt(0), iterator.next())
        assertFalse(iterator.hasNext())
        assertFailsWith<IndexOutOfBoundsException> {
            iterator.next()
        }
    }

    @Test
    fun codePointIterator_with_start_and_end_index() {
        val iterator = "ab\uD83E\uDD95c".codePointIterator(startIndex = 1, endIndex = 4)

        assertTrue(iterator.hasNext())
        assertEquals('b'.toCodePoint(), iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals("\uD83E\uDD95".codePointAt(0), iterator.next())
        assertFalse(iterator.hasNext())
        assertFailsWith<IndexOutOfBoundsException> {
            iterator.next()
        }
    }

    @Test
    fun codePointIterator_with_start_index_inside_surrogate_pair() {
        val iterator = "a\uD83E\uDD95b".codePointIterator(startIndex = 2)

        assertTrue(iterator.hasNext())
        assertEquals('\uDD95'.toCodePoint(), iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals('b'.toCodePoint(), iterator.next())
        assertFalse(iterator.hasNext())
        assertFailsWith<IndexOutOfBoundsException> {
            iterator.next()
        }
    }
    
    @Test
    fun codePointIterator_with_end_index_inside_surrogate_pair() {
        val iterator = "a\uD83E\uDD95b".codePointIterator(endIndex = 2)

        assertTrue(iterator.hasNext())
        assertEquals('a'.toCodePoint(), iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals('\uD83E'.toCodePoint(), iterator.next())
        assertFalse(iterator.hasNext())
        assertFailsWith<IndexOutOfBoundsException> {
            iterator.next()
        }
    }
}

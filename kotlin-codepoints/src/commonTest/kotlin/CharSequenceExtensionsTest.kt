package de.cketti.codepoints

import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith

class CharSequenceExtensionsTest {
    @Test
    fun codePointAt() {
        assertEquals('a'.code, "a".codePointAt(0))
        assertEquals(0xFFFF, "\uFFFF".codePointAt(0))
        assertEquals(0x1F995, "\uD83E\uDD95".codePointAt(0))

        assertEquals('b'.code, "ab".codePointAt(1))
        assertEquals(0x1F996, "\uD83E\uDD95\uD83E\uDD96".codePointAt(2))
    }

    @Test
    fun codePointAt_with_unmatched_surrogates() {
        assertEquals(0xD83E, "\uD83E".codePointAt(0))
        assertEquals(0xDD95, "\uDD95".codePointAt(0))

        assertEquals(0xD83E, "a\uD83Eb".codePointAt(1))
        assertEquals(0xDD95, "a\uDD95b".codePointAt(1))

        assertEquals(0xDD96, "\uDD96\uD83E".codePointAt(0))
    }

    @Test
    fun codePointAt_between_surrogate_pairs() {
        assertEquals(0xDD95, "\uD83E\uDD95\uD83E\uDD96".codePointAt(1))
        assertEquals(0xDD96, "\uD83E\uDD95\uD83E\uDD96".codePointAt(3))
    }

    @Test
    fun codePointBefore() {
        assertEquals('a'.code, "a".codePointBefore(1))
        assertEquals(0xFFFF, "\uFFFF".codePointBefore(1))
        assertEquals(0x1F995, "\uD83E\uDD95".codePointBefore(2))

        assertEquals('a'.code, "ab".codePointBefore(1))
        assertEquals(0x1F995, "\uD83E\uDD95b".codePointBefore(2))
    }

    @Test
    fun codePointBefore_with_unmatched_surrogates() {
        assertEquals(0xD83E, "\uD83E".codePointBefore(1))
        assertEquals(0xDD95, "\uDD95".codePointBefore(1))

        assertEquals(0xD83E, "a\uD83Eb".codePointBefore(2))
        assertEquals(0xDD95, "a\uDD95b".codePointBefore(2))

        assertEquals(0xD83E, "\uDD96\uD83E".codePointBefore(2))
    }

    @Test
    fun codePointBefore_between_surrogate_pairs() {
        assertEquals(0xD83E, "\uD83E\uDD95\uD83E\uDD96".codePointBefore(1))
        assertEquals(0xD83E, "\uD83E\uDD95\uD83E\uDD96".codePointBefore(3))
    }

    @Test
    fun codePointCount() {
        assertEquals(0, "".codePointCount())
        assertEquals(0, "".codePointCount(beginIndex = 0, endIndex = 0))
        assertEquals(0, "abc".codePointCount(beginIndex = 1, endIndex = 1))

        assertEquals(3, "abc".codePointCount())
        assertEquals(3, "abc".codePointCount(beginIndex = 0, endIndex = 3))
        assertEquals(2, "a\uFFFF".codePointCount())
        assertEquals(2, "a\uFFFF".codePointCount(beginIndex = 0, endIndex = 2))
        assertEquals(1, "\uD83E\uDD95".codePointCount())
        assertEquals(1, "\uD83E\uDD95".codePointCount(beginIndex = 0, endIndex = 2))
        assertEquals(2, "\uD83E\uDD95\uD83E\uDD96".codePointCount())
        assertEquals(2, "\uD83E\uDD95\uD83E\uDD96".codePointCount(beginIndex = 0, endIndex = 4))

        assertEquals(2, "abc".codePointCount(beginIndex = 1, endIndex = 3))
        assertEquals(1, "a\uFFFF".codePointCount(beginIndex = 1, endIndex = 2))
        assertEquals(1, "\uD83E\uDD95\uD83E\uDD96".codePointCount(beginIndex = 2, endIndex = 4))

        assertEquals(2, "abc".codePointCount(beginIndex = 0, endIndex = 2))
        assertEquals(1, "a\uFFFF".codePointCount(beginIndex = 0, endIndex = 1))
        assertEquals(1, "\uD83E\uDD95\uD83E\uDD96".codePointCount(beginIndex = 0, endIndex = 2))
    }

    @Test
    fun codePointCount_with_unmatched_surrogates() {
        assertEquals(2, "\uDD95\uD83E".codePointCount())
        assertEquals(2, "\uDD95\uD83E".codePointCount(beginIndex = 0, endIndex = 2))
        assertEquals(3, "\uDD95\uD83E\uDD95\uD83E".codePointCount())
        assertEquals(3, "\uDD95\uD83E\uDD95\uD83E".codePointCount(beginIndex = 0, endIndex = 4))

        assertEquals(1, "\uDD95\uD83E".codePointCount(beginIndex = 1, endIndex = 2))
        assertEquals(2, "\uDD95\uD83E\uDD95\uD83E".codePointCount(beginIndex = 1, endIndex = 4))

        assertEquals(1, "\uDD95\uD83E".codePointCount(beginIndex = 0, endIndex = 1))
        assertEquals(2, "\uDD95\uD83E\uDD95\uD83E".codePointCount(beginIndex = 0, endIndex = 3))
    }

    @Test
    fun codePointCount_between_surrogate_pairs() {
        assertEquals(2, "\uD83E\uDD95\uD83E\uDD96".codePointCount(beginIndex = 1, endIndex = 4))
        assertEquals(2, "\uD83E\uDD95\uD83E\uDD96".codePointCount(beginIndex = 0, endIndex = 3))
    }

    @Test
    fun offsetByCodePoints() {
        assertEquals(2, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 0, codePointOffset = 1))
        assertEquals(4, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 0, codePointOffset = 2))

        assertEquals(0, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 2, codePointOffset = -1))
        assertEquals(0, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 4, codePointOffset = -2))
    }

    @Test
    fun offsetByCodePoints_with_unmatched_surrogates() {
        assertEquals(1, "\uDD95\uD83E".offsetByCodePoints(index = 0, codePointOffset = 1))
        assertEquals(2, "\uDD95\uD83E".offsetByCodePoints(index = 0, codePointOffset = 2))

        assertEquals(1, "\uDD95\uD83E".offsetByCodePoints(index = 2, codePointOffset = -1))
        assertEquals(0, "\uDD95\uD83E".offsetByCodePoints(index = 2, codePointOffset = -2))
    }

    @Test
    fun offsetByCodePoints_between_surrogate_pairs() {
        assertEquals(2, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 1, codePointOffset = 1))
        assertEquals(4, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 1, codePointOffset = 2))
        assertEquals(4, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 3, codePointOffset = 1))

        assertEquals(2, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 3, codePointOffset = -1))
        assertEquals(0, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 3, codePointOffset = -2))
        assertEquals(0, "\uD83E\uDD95\uD83E\uDD96".offsetByCodePoints(index = 1, codePointOffset = -1))
    }

    @Test
    fun offsetByCodePoints_with_invalid_index() {
        assertFailsWith<IndexOutOfBoundsException> {
            "a".offsetByCodePoints(index = -1, codePointOffset = 0)
        }

        assertFailsWith<IndexOutOfBoundsException> {
            "a".offsetByCodePoints(index = 2, codePointOffset = 0)
        }
    }

    @Test
    fun offsetByCodePoints_with_invalid_codePointOffset() {
        assertFailsWith<IndexOutOfBoundsException> {
            "a".offsetByCodePoints(index = 0, codePointOffset = 2)
        }

        assertFailsWith<IndexOutOfBoundsException> {
            "a".offsetByCodePoints(index = 1, codePointOffset = -2)
        }

        assertFailsWith<IndexOutOfBoundsException> {
            "\uD83E\uDD95".offsetByCodePoints(index = 0, codePointOffset = 2)
        }

        assertFailsWith<IndexOutOfBoundsException> {
            "\uD83E\uDD95".offsetByCodePoints(index = 2, codePointOffset = -2)
        }
    }
}

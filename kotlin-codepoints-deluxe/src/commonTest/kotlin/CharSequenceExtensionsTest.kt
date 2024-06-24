package de.cketti.codepoints.deluxe

import kotlin.test.assertEquals
import kotlin.test.Test

class CharSequenceExtensionsTest {
    @Test
    fun codePointAt() {
        assertEquals('a'.toCodePoint(), "a".codePointAt(0))
        assertEquals(0xFFFF.toCodePoint(), "\uFFFF".codePointAt(0))
        assertEquals(0x1F995.toCodePoint(), "\uD83E\uDD95".codePointAt(0))

        assertEquals('b'.toCodePoint(), "ab".codePointAt(1))
        assertEquals(0x1F996.toCodePoint(), "\uD83E\uDD95\uD83E\uDD96".codePointAt(2))
    }

    @Test
    fun codePointAt_with_unmatched_surrogates() {
        assertEquals(0xD83E.toCodePoint(), "\uD83E".codePointAt(0))
        assertEquals(0xDD95.toCodePoint(), "\uDD95".codePointAt(0))

        assertEquals(0xD83E.toCodePoint(), "a\uD83Eb".codePointAt(1))
        assertEquals(0xDD95.toCodePoint(), "a\uDD95b".codePointAt(1))

        assertEquals(0xDD96.toCodePoint(), "\uDD96\uD83E".codePointAt(0))
    }

    @Test
    fun codePointAt_between_surrogate_pairs() {
        assertEquals(0xDD95.toCodePoint(), "\uD83E\uDD95\uD83E\uDD96".codePointAt(1))
        assertEquals(0xDD96.toCodePoint(), "\uD83E\uDD95\uD83E\uDD96".codePointAt(3))
    }

    @Test
    fun codePointBefore() {
        assertEquals('a'.toCodePoint(), "a".codePointBefore(1))
        assertEquals(0xFFFF.toCodePoint(), "\uFFFF".codePointBefore(1))
        assertEquals(0x1F995.toCodePoint(), "\uD83E\uDD95".codePointBefore(2))

        assertEquals('a'.toCodePoint(), "ab".codePointBefore(1))
        assertEquals(0x1F995.toCodePoint(), "\uD83E\uDD95b".codePointBefore(2))
    }

    @Test
    fun codePointBefore_with_unmatched_surrogates() {
        assertEquals(0xD83E.toCodePoint(), "\uD83E".codePointBefore(1))
        assertEquals(0xDD95.toCodePoint(), "\uDD95".codePointBefore(1))

        assertEquals(0xD83E.toCodePoint(), "a\uD83Eb".codePointBefore(2))
        assertEquals(0xDD95.toCodePoint(), "a\uDD95b".codePointBefore(2))

        assertEquals(0xD83E.toCodePoint(), "\uDD96\uD83E".codePointBefore(2))
    }

    @Test
    fun codePointBefore_between_surrogate_pairs() {
        assertEquals(0xD83E.toCodePoint(), "\uD83E\uDD95\uD83E\uDD96".codePointBefore(1))
        assertEquals(0xD83E.toCodePoint(), "\uD83E\uDD95\uD83E\uDD96".codePointBefore(3))
    }

    @Test
    fun forEachCodepoint() {
        fun CharSequence.collectCodepoints(): List<CodePoint> = buildList { forEachCodePoint { add(it) } }

        assertEquals(
            emptyList(),
            "".collectCodepoints(),
        )
        assertEquals(
            listOf('a'.toCodePoint()),
            "a".collectCodepoints(),
        )
        assertEquals(
            listOf('a'.toCodePoint(), 0xFFFF.toCodePoint()),
            "a\uFFFF".collectCodepoints(),
        )
        assertEquals(
            listOf(0x1F995.toCodePoint(), 'a'.toCodePoint(), 0x1F996.toCodePoint()),
            "\uD83E\uDD95a\uD83E\uDD96".collectCodepoints(),
        )
    }

    @Test
    fun forEachCodepointIndexed() {
        fun CharSequence.collectCodepoints(): List<Pair<Int, CodePoint>> =
            buildList { forEachCodePointIndexed { index, codepoint -> add(index to codepoint) } }

        assertEquals(
            emptyList(),
            "".collectCodepoints(),
        )
        assertEquals(
            listOf(0 to 'a'.toCodePoint()),
            "a".collectCodepoints(),
        )
        assertEquals(
            listOf(0 to 'a'.toCodePoint(), 1 to 0x1F995.toCodePoint()),
            "a\uD83E\uDD95".collectCodepoints(),
        )
        assertEquals(
            listOf(
                0 to 0x1F995.toCodePoint(),
                2 to 'a'.toCodePoint(),
                3 to 0x1F996.toCodePoint(),
            ),
            "\uD83E\uDD95a\uD83E\uDD96".collectCodepoints(),
        )
    }
}

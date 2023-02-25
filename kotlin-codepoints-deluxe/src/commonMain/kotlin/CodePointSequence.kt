package de.cketti.codepoints.deluxe

import kotlin.jvm.JvmInline

/**
 * Sequence of [CodePoint]s in the given [CharSequence].
 */
@JvmInline
value class CodePointSequence(private val text: CharSequence) : Sequence<CodePoint> {
    override fun iterator(): CodePointIterator {
        return text.codePointIterator()
    }
}

/**
 * Iterator for [CodePoint]s in the given [CharSequence].
 * 
 * The `startIndex` and `endIndex` parameters are the regular `CharSequence` indices, i.e. the number of `Char`s from 
 * the start of the character sequence.
 */
class CodePointIterator(
    private val text: CharSequence,
    startIndex: Int,
    private val endIndex: Int
) : Iterator<CodePoint> {
    private var index = startIndex
    
    override fun hasNext(): Boolean {
        return index < endIndex
    }

    override fun next(): CodePoint {
        return if (index + 1 == endIndex) {
            text[index].toCodePoint().also { 
                index++ 
            }
        } else if (hasNext()) {
            text.codePointAt(index).also { codePoint ->
                index += codePoint.charCount
            }
        } else {
            throw IndexOutOfBoundsException()
        }
    }
}

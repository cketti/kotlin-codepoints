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
 */
class CodePointIterator internal constructor(private val text: CharSequence) : Iterator<CodePoint> {

    @Deprecated(
        message = "Call CharSequence.codePointIterator() on a sub-sequence instead",
        replaceWith = ReplaceWith("text.subSequence(startIndex, endIndex).codePointIterator()"),
    )
    constructor(text: CharSequence, startIndex: Int, endIndex: Int) : this(text.subSequence(startIndex, endIndex))

    private var index = 0

    override fun hasNext(): Boolean {
        return index < text.length
    }

    override fun next(): CodePoint {
        return if (hasNext()) {
            text.codePointAt(index).also { codePoint ->
                index += codePoint.charCount
            }
        } else {
            throw IndexOutOfBoundsException()
        }
    }
}

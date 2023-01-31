package de.cketti.codepoints.deluxe

import kotlin.jvm.JvmInline

/**
 * Sequence of [CodePoint]s in the given [String].
 */
@JvmInline
value class CodePointSequence(private val text: String) : Sequence<CodePoint> {
    override fun iterator(): CodePointIterator {
        return CodePointIterator(text)
    }
}

/**
 * Iterator for [CodePoint]s in the given [String].
 */
class CodePointIterator(private val text: String) : Iterator<CodePoint> {
    private var index = 0
    
    override fun hasNext(): Boolean {
        return index < text.length
    }

    override fun next(): CodePoint {
        return text.codePointAt(index).also { codePoint ->
            index += codePoint.charCount
        }
    }
}

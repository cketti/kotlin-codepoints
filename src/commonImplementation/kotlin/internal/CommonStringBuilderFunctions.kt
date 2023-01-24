package de.cketti.codepoints.internal

import de.cketti.codepoints.CodePoints

object CommonStringBuilderFunctions {
    fun appendCodePoint(builder: StringBuilder, codePoint: Int) {
        if (CodePoints.isBmpCodePoint(codePoint)) {
            builder.append(codePoint.toChar())
        } else {
            builder.append(CodePoints.highSurrogate(codePoint))
            builder.append(CodePoints.lowSurrogate(codePoint))
        }
    }
}

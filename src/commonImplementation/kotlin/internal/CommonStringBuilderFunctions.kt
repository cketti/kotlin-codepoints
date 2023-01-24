package de.cketti.codepoints.internal

internal fun appendCodePoint(builder: StringBuilder, codePoint: Int) {
    if (isBmpCodePoint(codePoint)) {
        builder.append(codePoint.toChar())
    } else {
        builder.append(highSurrogate(codePoint))
        builder.append(lowSurrogate(codePoint))
    }
}

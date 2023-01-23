@file:Suppress(
    "EXTENSION_SHADOWED_BY_MEMBER", // Kotlin/JVM aliases StringBuilder to j.l.StringBuilder.
    "KotlinRedundantDiagnosticSuppress", // Above suppression only needed for JVM.
)

package de.cketti.codepoints

/**
 * Appends the string representation of the [codePoint] argument to this sequence.
 *
 * The argument is appended to the contents of this sequence.
 * The length of this sequence increases by [CodePoints.charCount].
 *
 * The overall effect is exactly as if the argument were converted to a char array by the function
 * [CodePoints.toChars] and the characters in that array were then appended to this sequence.
 */
expect fun StringBuilder.appendCodePoint(codePoint: Int): StringBuilder

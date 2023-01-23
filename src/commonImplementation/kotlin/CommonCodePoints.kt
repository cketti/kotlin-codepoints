package de.cketti.codepoints

object CommonCodePoints {
    private const val MIN_SUPPLEMENTARY_CODE_POINT = 0x10000
    private const val MAX_CODE_POINT = 0x10FFFF

    private const val MIN_HIGH_SURROGATE = 0xD800
    private const val MAX_HIGH_SURROGATE = 0xDBFF
    private const val MIN_LOW_SURROGATE = 0xDC00
    private const val MAX_LOW_SURROGATE = 0xDFFF

    private const val SURROGATE_DECODE_OFFSET =
        MIN_SUPPLEMENTARY_CODE_POINT - (MIN_HIGH_SURROGATE shl 10) - MIN_LOW_SURROGATE

    private const val HIGH_SURROGATE_ENCODE_OFFSET =
        (MIN_HIGH_SURROGATE - (MIN_SUPPLEMENTARY_CODE_POINT ushr 10))

    fun isValidCodePoint(codePoint: Int): Boolean {
        return codePoint in 0..MAX_CODE_POINT
    }
    
    fun isBmpCodePoint(codePoint: Int): Boolean {
        return codePoint ushr 16 == 0
    }
    
    fun isSupplementaryCodePoint(codePoint: Int): Boolean {
        return codePoint in MIN_SUPPLEMENTARY_CODE_POINT..MAX_CODE_POINT
    }
    
    fun charCount(codePoint: Int): Int {
        return if (codePoint < MIN_SUPPLEMENTARY_CODE_POINT) 1 else 2
    }
    
    fun isSurrogate(char: Char): Boolean {
        return char.code in MIN_HIGH_SURROGATE..MAX_LOW_SURROGATE
    }
    
    fun isHighSurrogate(char: Char): Boolean {
        return char.code in MIN_HIGH_SURROGATE..MAX_HIGH_SURROGATE 
    }
    
    fun isLowSurrogate(char: Char): Boolean {
        return char.code in MIN_LOW_SURROGATE..MAX_LOW_SURROGATE
    }
    
    fun isSurrogatePair(highSurrogate: Char, lowSurrogate: Char): Boolean {
        return isHighSurrogate(highSurrogate) && isLowSurrogate(lowSurrogate)
    }

    fun highSurrogate(codePoint: Int): Char {
        return ((codePoint ushr 10) + HIGH_SURROGATE_ENCODE_OFFSET).toChar() 
    }
    
    fun lowSurrogate(codePoint: Int): Char {
        return ((codePoint and 0x3FF) + MIN_LOW_SURROGATE).toChar()
    }
    
    fun toCodePoint(highSurrogate: Char, lowSurrogate: Char): Int {
        return (highSurrogate.code shl 10) + lowSurrogate.code + SURROGATE_DECODE_OFFSET
    }

    fun toChars(codePoint: Int): CharArray {
        return if (isBmpCodePoint(codePoint)) {
            charArrayOf(codePoint.toChar())
        } else {
            charArrayOf(highSurrogate(codePoint), lowSurrogate(codePoint))
        }
    }

    fun toChars(codePoint: Int, destination: CharArray, offset: Int): Int {
        val size = destination.size
        if (offset >= 0) {
            if (isBmpCodePoint(codePoint)) {
                if (offset < size) {
                    destination[offset] = codePoint.toChar()
                    return 1
                }
            } else if (offset < size - 1) {
                destination[offset] = highSurrogate(codePoint)
                destination[offset + 1] = lowSurrogate(codePoint)
                return 2
            }
        }
        throw IndexOutOfBoundsException("Size: $size, offset: $offset")
    }
}

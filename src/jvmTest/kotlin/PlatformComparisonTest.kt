@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package de.cketti.codepoints

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlatformComparisonTest {
    @Test
    fun codePointAt() {
        val text = "a\uD83E\uDD95\uD83E\uDD96b\uDD96\uD83Ec\uD83Ed\uDD96e\uD83E"

        for (index in text.indices) {
            val jvmCodePointAt = (text as java.lang.String).codePointAt(index)
            val commonCodePointAt = CommonStringFunctions.codePointAt(text, index)
            assertEquals(jvmCodePointAt, commonCodePointAt, "codePointAt($index)")
        }
    }
    
    @Test
    fun codePointBefore() {
        val text = "\uDD95a\uD83E\uDD95\uD83E\uDD96b\uDD96\uD83Ec\uD83Ed\uDD96"

        for (index in 1..text.length) {
            val jvmCodePointBefore = (text as java.lang.String).codePointBefore(index)
            val commonCodePointBefore = CommonStringFunctions.codePointBefore(text, index)
            assertEquals(jvmCodePointBefore, commonCodePointBefore, "codePointBefore($index)")
        }
    }
    
    @Test
    fun codePointCount() {
        val text = "\uDD95a\uD83E\uDD95\uD83E\uDD96b\uDD96\uD83Ec\uD83Ed\uDD96"

        for (beginIndex in 0 until text.lastIndex) {
            for (endIndex in (beginIndex + 1)..text.lastIndex) {
                val jvmCodePointCount = (text as java.lang.String).codePointCount(beginIndex, endIndex)
                val commonCodePointCount = CommonStringFunctions.codePointCount(text, beginIndex, endIndex)
                assertEquals(jvmCodePointCount, commonCodePointCount, "codePointCount($beginIndex, $endIndex)")
            }
        }
    }

    @Test
    fun offsetByCodePoints() {
        val text = "\uDD95a\uD83E\uDD95\uD83E\uDD96b\uDD96\uD83Ec\uD83Ed\uDD96"

        for (index in 0 until text.lastIndex) {
            for (codePointOffset in 0..(text.length - index)) {
                val jvmResult = try {
                    Result.success((text as java.lang.String).offsetByCodePoints(index, codePointOffset))
                } catch (e: Exception) {
                    Result.failure<Exception>(e)
                }
                
                val commonResult = try {
                    Result.success(CommonStringFunctions.offsetByCodePoints(text, index, codePointOffset))
                } catch (e: Exception) {
                    Result.failure<Exception>(e)
                }
                
                assertEquals(jvmResult.isFailure, commonResult.isFailure)
                if (jvmResult.isSuccess) {
                    assertEquals(jvmResult, commonResult, "codePointCount($index, $codePointOffset)")
                }
            }
        }

        for (index in text.length downTo 1) {
            for (codePointOffset in 0 downTo -(text.length - index)) {
                val jvmResult = try {
                    Result.success((text as java.lang.String).offsetByCodePoints(index, codePointOffset))
                } catch (e: Exception) {
                    Result.failure<Exception>(e)
                }
                
                val commonResult = try {
                    Result.success(CommonStringFunctions.offsetByCodePoints(text, index, codePointOffset))
                } catch (e: Exception) {
                    Result.failure<Exception>(e)
                }
                
                assertEquals(jvmResult.isFailure, commonResult.isFailure)
                if (jvmResult.isSuccess) {
                    assertEquals(jvmResult, commonResult, "codePointCount($index, $codePointOffset)")
                }
            }
        }
    }
}

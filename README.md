# kotlin-codepoints

Kotlin Multiplatform (KMP) library that adds basic support for Unicode code points. 

**Note:** This library is a side project and work in progress. When evaluating whether to use this in production, please
consider this project unmaintained. That being said, contributions are welcome. But don't expect fast responses.

## Installation
kotlin-codepoints is distributed through Maven Central.

```kotlin
dependencies {
    // Basic API
    implementation("de.cketti.unicode:kotlin-codepoints:0.6.0")

    // or
    
    // Nice API
    implementation("de.cketti.unicode:kotlin-codepoints-deluxe:0.6.0")
}
```

## Features

### kotlin-codepoints

This library aims to make some methods of the Java standard library available to Kotlin multiplatform projects.

Methods found in `java.lang.String`:
* `CharSequence.codePointAt(index)`
* `CharSequence.codePointBefore(index)`
* `CharSequence.codePointCount(beginIndex, endIndex)`
* `CharSequence.offsetByCodePoints(index, codePointOffset)`

Methods found in `java.lang.StringBuilder`:
* `Appendable.appendCodePoint(codePoint)`

Methods found in `java.lang.Character`: 
* `CodePoints.isValidCodePoint(codePoint)`
* `CodePoints.isBmpCodePoint(codePoint)`
* `CodePoints.isSupplementaryCodePoint(codePoint)`
* `CodePoints.charCount(codePoint)`
* `CodePoints.isSurrogatePair(highSurrogate, lowSurrogate)`
* `CodePoints.highSurrogate(codePoint)`
* `CodePoints.lowSurrogate(codePoint)`
* `CodePoints.toCodePoint(highSurrogate, lowSurrogate)`
* `CodePoints.toChars(codePoint)`
* `CodePoints.toChars(codePoint, destination, offset)`

On the JVM the platform implementation (`java.lang.Character.*`) is used. On all other platforms the 
[implementation in this library](src/commonImplementation/kotlin) is used.

### kotlin-codepoints-deluxe

This library builds on top of `kotlin-codepoints`. It adds a the `CodePoint` class to make working with code points a 
bit less painful.

Until someone gets around to building and publishing proper documentation, 
see [CodePoint.kt](https://github.com/cketti/kotlin-codepoints/blob/main/kotlin-codepoints-deluxe/src/commonMain/kotlin/CodePoint.kt)
to get and idea of what's available.

#### Example:
```kotlin
val text = "🦕&🦖"

for (codePoint in text.codePointSequence()) {
    print("code point: $codePoint, char count: ${codePoint.charCount}")

    if (codePoint.isBasic) {
        println(" - boring!")
    } else {
        println()
    }
}

// Output:
// -------
// code point: U+1F995, char count: 2
// code point: U+0026, char count: 1 - boring!
// code point: U+1F996, char count: 2
```


## License

```text
MIT License

Copyright (c) 2023 cketti

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

# kotlin-codepoints

Kotlin Multiplatform (KMP) library that adds basic support for Unicode code points. 

**Note:** This library is a side project and work in progress. When evaluating whether to use this in production, please
consider this project unmaintained. That being said, contributions are welcome. But don't expect fast responses.

## Installation
kotlin-codepoints is distributed through Maven Central.

```kotlin
dependencies {
    implementation("de.cketti.unicode:kotlin-codepoints:0.2.0")
}
```

## Features

This library aims to make some methods of the Java standard library available to Kotlin multiplatform projects.

Methods found in `java.lang.String`:
* `String.codePointAt(index)`
* `String.codePointBefore(index)`
* `String.codePointCount(beginIndex, endIndex)`
* `String.offsetByCodePoints(index, codePointOffset)`

Methods found in `java.lang.Character`: 
* `CodePoints.isValidCodePoint(codePoint)`
* `CodePoints.isBmpCodePoint(codePoint)`
* `CodePoints.isSupplementaryCodePoint(codePoint)`
* `CodePoints.charCount(codePoint)`
* `CodePoints.isSurrogate(char)`
* `CodePoints.isHighSurrogate(char)`
* `CodePoints.isLowSurrogate(char)`
* `CodePoints.isSurrogatePair(highSurrogate, lowSurrogate)`
* `CodePoints.highSurrogate(codePoint)`
* `CodePoints.lowSurrogate(codePoint)`
* `CodePoints.toCodePoint(highSurrogate, lowSurrogate)`

On the JVM the platform implementation is used. On all other platforms the 
[implementation in this library](src/commonImplementation/kotlin) is used.


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

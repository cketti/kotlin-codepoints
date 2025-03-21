# Changelog

## [Unreleased]
### Changed
- Updated to Kotlin 2.1.20

## [0.9.0] - 2024-06-24
### Changed
- kotlin-codepoints-deluxe
  - `CodePoint.toString()` now returns the string representation of a code point.

### Added
- kotlin-codepoints
  - Added `CharSequence.codePointCount()` variant without parameters.
  - Added `CodePoints.toString(…)` that creates a string from the given code points.
  - Added `CharSequence.forEachCodePoint()` and `CharSequence.forEachCodePointIndexed()` to iterate over code points in
    a character sequence.
- kotlin-codepoints-deluxe
  - `CodePoint.toUnicodeNotation()` returns the standard Unicode notation of a code point, e.g. `U+1F4E7`.
  - Added `CharSequence.forEachCodePoint()` and `CharSequence.forEachCodePointIndexed()` to iterate over code points in
    a character sequence.

## [0.8.0] - 2024-06-09
### Changed
- Updated to Kotlin 2.0.0

## [0.7.0] - 2024-03-02
### Added
- Added `StringBuilder.setCodePointAt()`, `StringBuilder.insertCodePointAt()`, and `StringBuilder.deleteCodePointAt()` 
  to both `kotlin-codepoints` and `kotlin-codepoints-deluxe`.
- Added support for wasmJs and wasmWasi targets.

### Removed
- Removed targets no longer supported by Kotlin 1.9.20+ (iosArm32, linuxArm32Hfp, linuxMips32, linuxMipsel32, mingwX86,
  wasm32, watchosX86).

## [0.6.1] - 2023-03-11
### Fixed
- `CharSequence.codePointCount()` now returns the correct result for a zero-length range.

## [0.6.0] - 2023-03-05
### Changed
- Use `CharSequence` extension functions instead of `String` extension functions in both `kotlin-codepoints` and
  `kotlin-codepoints-deluxe`.
- Change `StringBuilder.appendCodePoint()` to `Appendable.appendCodePoint()` in both `kotlin-codepoints` and
  `kotlin-codepoints-deluxe`.

## [0.5.0] - 2023-02-19
### Changed
- Added support for iterating over parts of a string when using `CodePointIterator`

### Added
- Added `StringBuilder.appendCodePoint()` to `kotlin-codepoints-deluxe`

## [0.4.0] - 2023-01-31
### Changed
- `CodePoints.toChars(Int, CharArray, Int)` is now bug-compatible with `java.lang.Character.toChars(int, char[], int)`. 
  Using an offset of -1 will lead to an exception, but the first element of the destination might be modified. 

### Added
- Support for MIPS targets
- Separate library `kotlin-codepoints-deluxe` that builds a nicer API on top of `kotlin-codepoints`

## [0.3.0] - 2023-01-24
### Changed
- Changed JVM target version from 11 to 1.8

### Added
- `StringBuilder.appendCodePoint(codePoint)`
- `CodePoints.toChars(codePoint)`
- `CodePoints.toChars(codePoint, destination, offset)`

### Removed
- `CodePoints.isSurrogate(char)`. Use `Char.isSurrogate()` instead.
- `CodePoints.isHighSurrogate(char)`. Use `Char.isHighSurrogate()` instead.
- `CodePoints.isLowSurrogate(char)`. Use `Char.isLowSurrogate()` instead.


## [0.2.0] - 2023-01-19
### Added
- Build all possible targets (except for MIPS)


## [0.1.0] - 2023-01-18
Initial release

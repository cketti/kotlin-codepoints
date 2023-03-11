# Changelog

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

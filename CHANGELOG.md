# Changelog

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

# jQuickDraw
A pure-Java implementation of Apple QuickDraw

## Scope
This piece of code is mainly setup to parse the binary QuickDraw PICT format specified in "Inside Macintosh - Imaging with QuickDraw".
In particular, version 1 of the PICT file format is targeted for now.
The goal is to be able to convert this type of vector image file into SVG or (E)PS files.

## Details
A PICTv1 file consists of a 512-byte proprietary header (which is skipped by default for now).
The size of the picture (in bytes) as well as a frame Rect are defined next.
After that (until the end of the file) follows an opcode and potential parameter data.
The opcodes define drawing operations available in the QuickDraw API.

## Code
The parser entry is in the [`Picture`](src/main/java/de/labathome/jQuickDraw/Picture.java) class.

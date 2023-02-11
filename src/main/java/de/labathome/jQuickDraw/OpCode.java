package de.labathome.jQuickDraw;

public enum OpCode {
	NOP            ((byte)0x00),
	ClipRgn        ((byte)0x01),
	BkPat          ((byte)0x02),
	TxFont         ((byte)0x03),
	TxFace         ((byte)0x04),
	TxMode         ((byte)0x05),
	SpExtra        ((byte)0x06),
	PnSize         ((byte)0x07),
	PnMode         ((byte)0x08),
	PnPat          ((byte)0x09),
	FillPat        ((byte)0x0A),
	OvSize         ((byte)0x0B),
	Origin         ((byte)0x0C),
	TxSize         ((byte)0x0D),
	FgColor        ((byte)0x0E),
	BkColor        ((byte)0x0F),
	TxRatio        ((byte)0x10),
	picVersion     ((byte)0x11),
	Line           ((byte)0x20),
	LineFrom       ((byte)0x21),
	ShortLine      ((byte)0x22),
	ShortLineFrom  ((byte)0x23),
	LongText       ((byte)0x28),
	DHText         ((byte)0x29),
	DVText         ((byte)0x2A),
	DHDVText       ((byte)0x2B),

	fontName       ((byte)0x2C), // back-ported from PICTv2
	glyphState     ((byte)0x2E), // back-ported from PICTv2

	frameRect      ((byte)0x30),
	paintRect      ((byte)0x31),
	eraseRect      ((byte)0x32),
	invertRect     ((byte)0x33),
	fillRect       ((byte)0x34),
	frameSameRect  ((byte)0x38),
	paintSameRect  ((byte)0x39),
	eraseSameRect  ((byte)0x3A),
	invertSameRect ((byte)0x3B),
	fillSameRect   ((byte)0x3C),
	frameRRect     ((byte)0x40),
	paintRRect     ((byte)0x41),
	eraseRRect     ((byte)0x42),
	invertRRect    ((byte)0x43),
	fillRRect      ((byte)0x44),
	frameSameRRect ((byte)0x48),
	paintSameRRect ((byte)0x49),
	eraseSameRRect ((byte)0x4A),
	invertSameRRect((byte)0x4B),
	fillSameRRect  ((byte)0x4C),
	frameOval      ((byte)0x50),
	paintOval      ((byte)0x51),
	eraseOval      ((byte)0x52),
	invertOval     ((byte)0x53),
	fillOval       ((byte)0x54),
	frameSameOval  ((byte)0x58),
	paintSameOval  ((byte)0x59),
	eraseSameOval  ((byte)0x5A),
	invertSameOval ((byte)0x5B),
	fillSameOval   ((byte)0x5C),
	frameArc       ((byte)0x60),
	paintArc       ((byte)0x61),
	eraseArc       ((byte)0x62),
	invertArc      ((byte)0x63),
	fillArc        ((byte)0x64),
	frameSameArc   ((byte)0x68),
	paintSameArc   ((byte)0x69),
	eraseSameArc   ((byte)0x6A),
	invertSameArc  ((byte)0x6B),
	fillSameArc    ((byte)0x6C),
	framePoly      ((byte)0x70),
	paintPoly      ((byte)0x71),
	erasePoly      ((byte)0x72),
	invertPoly     ((byte)0x73),
	fillPoly       ((byte)0x74),
	frameSamePoly  ((byte)0x78),
	paintSamePoly  ((byte)0x79),
	eraseSamePoly  ((byte)0x7A),
	invertSamePoly ((byte)0x7B),
	fillSamePoly   ((byte)0x7C),
	frameRgn       ((byte)0x80),
	paintRgn       ((byte)0x81),
	eraseRgn       ((byte)0x82),
	invertRgn      ((byte)0x83),
	fillRgn        ((byte)0x84),
	frameSameRgn   ((byte)0x88),
	paintSameRgn   ((byte)0x89),
	eraseSameRgn   ((byte)0x8A),
	invertSameRgn  ((byte)0x8B),
	fillSameRgn    ((byte)0x8C),
	BitsRect       ((byte)0x90),
	BitsRgn        ((byte)0x91),
	PackBitsRect   ((byte)0x98),
	PackBitsRgn    ((byte)0x99),
	ShortComment   ((byte)0xA0),
	LongComment    ((byte)0xA1),

	reservedForApple_00((byte)0xB2),
	reservedForApple_01((byte)0xB7),

	EndOfPicture   ((byte)0xFF);

	private OpCode(byte id) {
		this.id = id;
	}

	private byte id;

	public byte id() {
		return id;
	}

	public static OpCode find(byte id) {
		for (OpCode op: OpCode.values()) {
			if (id == op.id()) {
				return op;
			}
		}
		return null;
	}
}

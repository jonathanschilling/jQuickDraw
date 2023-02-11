package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class FontName {

	public FontName(short dataLength, short oldFontId, short nameLength, String fontName) {
		this.dataLength = dataLength;
		this.oldFontId = oldFontId;
		this.nameLength = nameLength;
		this.fontName = fontName;
	}

	public static FontName fromByteBuffer(ByteBuffer buf) {
		short dataLength = buf.getShort();
		short oldFontId = buf.getShort();
		short nameLength = buf.get();
		if (nameLength < 0) {
			nameLength += 256;
		}
		String fontName = "";
		for (int i = 0; i < nameLength; ++i) {
			fontName += (char) (buf.get());
		}
		return new FontName(dataLength, oldFontId, nameLength, fontName);
	}

	@Override
	public String toString() {
		return String.format("FontName{dataLength=%d, oldFontId=%d, nameLength=%d, fontName=%s}",
				dataLength, oldFontId, nameLength, fontName);
	}

	public short dataLength;
	public short oldFontId;
	public short nameLength;
	public String fontName;

}

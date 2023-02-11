package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class DHText {

	public DHText(short dh, short count, String text) {
		this.dh = dh;
		this.count = count;
		this.text = text;
	}

	public static DHText fromByteBuffer(ByteBuffer buf) {

		short dh  =  buf.get();
		if (dh < 0) { dh += 256; }

		short count = buf.get();
		if (count < 0) { count += 256; }

		String text = "";
		for (int i=0; i < count; ++i) {
			text += (char) buf.get();
		}
		return new DHText(dh, count, text);
	}

	@Override
	public String toString() {
		return String.format("DHText{dh=%d, count=%d, text='%s'}", dh, count, text);
	}

	public short dh;
	public short count;
	public String text;
}

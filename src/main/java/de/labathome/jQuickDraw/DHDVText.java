package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class DHDVText {

	public DHDVText(short dh, short dv, short count, String text) {
		this.dh = dh;
		this.dv = dv;
		this.count = count;
		this.text = text;
	}

	public static DHDVText fromByteBuffer(ByteBuffer buf) {

		short dh  =  buf.get();
		if (dh < 0) { dh += 256; }
		short dv  =  buf.get();
		if (dv < 0) { dv += 256; }

		short count = buf.get();
		if (count < 0) { count += 256; }

		String text = "";
		for (int i=0; i < count; ++i) {
			text += (char) buf.get();
		}
		return new DHDVText(dh, dv, count, text);
	}

	@Override
	public String toString() {
		return String.format("DHDVText{dh=%d, dv=%d, count=%d, text='%s'}", dh, dv, count, text);
	}

	public short dh;
	public short dv;
	public short count;
	public String text;

}

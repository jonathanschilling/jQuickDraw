package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class LongText {

	public LongText(Point txLoc, short count, String text) {
		this.txLoc = txLoc;
		this.count = count;
		this.text = text;
	}

	public static LongText fromByteBuffer(ByteBuffer buf) {
		Point txLoc = new Point(buf);
		short count = buf.get();
		if (count < 0) {
			count += 256;
		}
		System.out.printf("text count: %d\n", count);
		String text = "";
		for (int i=0; i < count; ++i) {
			text += (char) buf.get();
		}
		return new LongText(txLoc, count, text);
	}

	@Override
	public String toString() {
		return String.format("LongText{txLoc=%s, count=%d, text='%s'}", txLoc, count, text);
	}

	public Point txLoc;
	public short count;
	public String text;
}

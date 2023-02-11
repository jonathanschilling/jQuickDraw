package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class ShortLine {

	public ShortLine(Point pnLoc, byte dh, byte dv) {
		this.pnLoc = pnLoc;
		this.dh = dh;
		this.dv = dv;
	}

	public static ShortLine fromByteBuffer(ByteBuffer buf) {
		Point pnLoc = new Point(buf);
		byte dh = buf.get();
		byte dv = buf.get();
		return new ShortLine(pnLoc, dh, dv);
	}

	@Override
	public String toString() {
		return String.format("ShortLine{pnLoc=%s, dh=%d, dv=%d}", pnLoc, dh, dv);
	}

	Point pnLoc;
	byte dh;
	byte dv;
}

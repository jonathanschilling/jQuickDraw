package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class Point {

	public static int BYTES = 4;

	public Point(short v, short h) {
		this.v = v;
		this.h = h;
	}

	public Point(ByteBuffer buf) {
		v = buf.getShort();
		h = buf.getShort();
	}

	@Override
	public Point clone() {
		return new Point(v, h);
	}

	@Override
	public String toString() {
		return String.format("[%d, %d]", v, h);
	}

	public short v;
	public short h;
}

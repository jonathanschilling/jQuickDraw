package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class Rect {

	public static int BYTES = 8;

	public Rect(Point topLeft, Point bottomRight) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}

	public Rect(short top, short left, short bottom, short right) {
		topLeft = new Point(top, left);
		bottomRight = new Point(bottom, right);
	}

	public Rect(ByteBuffer buf) {
		topLeft = new Point(buf);
		bottomRight = new Point(buf);
	}

	@Override
	public Rect clone() {
		return new Rect(topLeft.clone(), bottomRight.clone());
	}

	@Override
	public String toString() {
		return String.format("{%s, %s}", topLeft.toString(), bottomRight.toString());
	}

	short top() {
		return topLeft.v;
	}

	short left() {
		return topLeft.h;
	}

	short bottom() {
		return bottomRight.v;
	}

	short right() {
		return bottomRight.h;
	}

	void top(short top) {
		topLeft.v = top;
	}

	void left(short left) {
		topLeft.h = left;
	}

	void bottom(short bottom) {
		bottomRight.v = bottom;
	}

	void right(short right) {
		bottomRight.h = right;
	}

	public Point topLeft;
	public Point bottomRight;
}

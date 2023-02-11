package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class Region {

	public Region(short rgnSize, Rect rgnBBox, byte[] data) {
		this.rgnSize = rgnSize;
		this.rgnBBox = rgnBBox;
		this.data = data;
	}

	public Region(Rect rgnBBox) {
		this((short) 10, rgnBBox, null);
	}

	public Region(ByteBuffer buf) {
		rgnSize = buf.getShort();
		rgnBBox = new Rect(buf);
		if (rgnSize > 10) {
			int dataSize = rgnSize - 10;
			data = new byte[dataSize];
			buf.get(data, 0, dataSize);
		}
	}

	@Override
	public String toString() {
		return String.format("Region{size=%d, frame=%s}", rgnSize, rgnBBox.toString());
	}

	/** size of Region object in bytes */
	public short rgnSize;

	public Rect rgnBBox;

	/** Apple proprietary format for non-rectangular regions */
	byte[] data;
}

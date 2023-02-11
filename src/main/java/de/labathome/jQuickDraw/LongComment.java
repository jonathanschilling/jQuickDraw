package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class LongComment {

	public LongComment(short kind, short size, byte[] data) {
		this.kind = kind;
		this.size = size;
		this.data = data;
	}

	public static LongComment fromByteBuffer(ByteBuffer buf) {
		short kind = buf.getShort();
		short size = buf.getShort();
		byte[] data = new byte[size];
		buf.get(data);
		return new LongComment(kind, size, data);
	}

	@Override
	public String toString() {
		return String.format("LongComment{kind=%d, size=%d}", kind, size);
	}


	public short kind;
	public short size;
	public byte[] data;
}

package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class GlyphState {

	public GlyphState(int dataLength, byte[] data) {
		this.dataLength = dataLength;
		this.data = data;
	}

	public static GlyphState fromByteByffer(ByteBuffer buf) {

		/**
		 * NOTE:
		 * InsideMacintosh, Appendix A lists glyphState only for PICTv2,
		 * but the demo file is indicated as PICTv1, even though it contains this opcode.
		 * However, a successful parsing works only if dataLength is assumed to be "Integer"
		 * (short in Java), but it is listed as "word" in IM.
		 * Also, the total size for this structure then is 6 bytes rather than 8 bytes.
		 */

		short dataLength = buf.getShort();
		byte[] data = new byte[4];
		buf.get(data);
		return new GlyphState(dataLength, data);
	}

	@Override
	public String toString() {
		return String.format("GlyphState{dataLength=%d, outlinePreferred=%d, preserveGlyph=%d, fractionalWidths=%d, scalingDisabled=%d}",
				dataLength, data[0], data[1], data[2], data[3]);
	}

	int dataLength;
	byte[] data;
}

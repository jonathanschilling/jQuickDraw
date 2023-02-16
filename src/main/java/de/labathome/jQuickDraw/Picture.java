package de.labathome.jQuickDraw;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Picture {

	public static byte[] DEMO_1 = new byte[] {
			/* picture size; this value is reliable for version 1 pictures */
			(byte) 0x00, (byte) 0x4f,
			/* bounding rectangle of picture */
			(byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x6E, (byte) 0x00, (byte) 0xAA,
			/* picVersion opcode for version 1 */
			(byte) 0x11,
			/* version number 1 */
			(byte) 0x01,
			/* ClipRgn opcode to define clipping region for picture */
			(byte) 0x01,
			/* region size */
			(byte) 0x00, (byte) 0x0A,
			/* bounding rectangle for region */
			(byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x6E, (byte) 0x00, (byte) 0xAA,
			/* FillPat opcode; fill pattern specified in next 8 bytes */
			(byte) 0x0A,
			/* fill pattern */
			(byte) 0x77, (byte) 0xDD, (byte) 0x77, (byte) 0xDD, (byte) 0x77, (byte) 0xDD, 0x77, (byte) 0xDD,
			/* fillRect opcode; rectangle specified in next 8 bytes */
			(byte) 0x34,
			/* rectangle to fill */
			(byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x6E, (byte) 0x00, (byte) 0xAA,
			/* FillPat opcode; fill pattern specified in next 8 bytes */
			(byte) 0x0A,
			/* fill pattern */
			(byte) 0x88, (byte) 0x22, (byte) 0x88, (byte) 0x22, (byte) 0x88, (byte) 0x22, (byte) 0x88, (byte) 0x22,
			/* fillSameOval opcode */
			(byte) 0x5C,
			/* paintPoly opcode */
			(byte) 0x71,
			/* size of polygon */
			(byte) 0x00, (byte) 0x1A,
			/* bounding rectangle for polygon */
			(byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x6E, (byte) 0x00, (byte) 0xAA,
			/* polygon points */
			(byte) 0x00, (byte) 0x6E, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x54,
			(byte) 0x00, (byte) 0x6E, (byte) 0x00, (byte) 0xAA, (byte) 0x00, (byte) 0x6E, (byte) 0x00, (byte) 0x02,
			/* EndOfPicture opcode; end of picture */
			(byte) 0xFF };

	public Picture(byte[] pictData) {
		this(ByteBuffer.wrap(pictData));
	}

	public Picture(ByteBuffer pictData) {

		int startPos = pictData.position();

		// Motorola 68k is big-endian
		pictData.order(ByteOrder.BIG_ENDIAN);

		// first two bytes are picture size in bytes
		picSize = pictData.getShort();
		System.out.printf("pict size: %d bytes\n", picSize);

		// get bounding Rect
		picFrame = new Rect(pictData);
		System.out.printf("pict frame: %s\n", picFrame.toString());

		//port = new GrafPort(picFrame);
		port = new JyPlotDevice(picFrame);

		boolean endOfPictureFound = false;
		int pos = pictData.position() - startPos;
		while (pos < picSize && !endOfPictureFound) {

			// try to identify next opcode
			byte opcode = pictData.get();
			OpCode op = OpCode.find(opcode);
			if (op == null) {
				throw new RuntimeException(String.format("opcode not recognized: 0x%02x", opcode));
			}

			System.out.printf("[OPCODE: 0x%02x]\n", opcode);

			// interpret opcode
			switch (op) {
			case NOP:
				// no operation --> ignore
				break;
			case ClipRgn:
				port.setClipRegion(new Region(pictData));
				break;
			case BkPat:
				port.setBackgroundPattern(Pattern.fromByteBuffer(pictData));
				break;
			case TxFont:
				port.setTextFont(pictData.getShort());
				break;
			case TxFace:
				short txFace = pictData.get();
				if (txFace < 0) {
					txFace += 256;
				}
				port.setTextFace(txFace);
				break;
			case TxMode:
				break;
			case SpExtra:
				break;
			case PnSize:
				port.setPenSize(new Point(pictData));
				break;
			case PnMode:
				break;
			case PnPat:
				port.setPenPattern(Pattern.fromByteBuffer(pictData));
				break;
			case FillPat:
				port.setFillPattern(Pattern.fromByteBuffer(pictData));
				break;
			case OvSize:
				break;
			case Origin:
				break;
			case TxSize:
				port.setTextSize(pictData.getShort());
				break;
			case FgColor:
				break;
			case BkColor:
				break;
			case TxRatio:
				break;
			case picVersion:
				byte version = pictData.get();
				System.out.printf("PICT version: %d\n", version);
				break;
			case Line:
				Point pnLoc = new Point(pictData);
				Point newPt = new Point(pictData);
				port.line(pnLoc, newPt);
				break;
			case LineFrom:
				port.lineFrom(new Point(pictData));
				break;
			case ShortLine:
				port.shortLine(ShortLine.fromByteBuffer(pictData));
				break;
			case ShortLineFrom:
				port.shortLineFrom(pictData.get(), pictData.get());
				break;
			case LongText:
				LongText txt = LongText.fromByteBuffer(pictData);
				port.longText(txt);
				break;
			case DHText:
				port.dhText(DHText.fromByteBuffer(pictData));
				break;
			case DVText:
				break;
			case DHDVText:
				port.dhdvText(DHDVText.fromByteBuffer(pictData));
				break;

			case fontName:
				port.setFontByName(FontName.fromByteBuffer(pictData));
				break;
			case glyphState:
				port.setGlyphState(GlyphState.fromByteByffer(pictData));
				break;

			case frameRect:
				break;
			case paintRect:
				break;
			case eraseRect:
				port.eraseRect(new Rect(pictData));
				break;
			case invertRect:
				break;
			case fillRect:
				port.fillRect(new Rect(pictData));
				break;
			case frameSameRect:
				port.frameSameRect();
				break;
			case paintSameRect:
				break;
			case eraseSameRect:
				break;
			case invertSameRect:
				break;
			case fillSameRect:
				break;
			case frameRRect:
				break;
			case paintRRect:
				break;
			case eraseRRect:
				break;
			case invertRRect:
				port.invertRRect(new Rect(pictData));
				break;
			case fillRRect:
				break;
			case frameSameRRect:
				break;
			case paintSameRRect:
				break;
			case eraseSameRRect:
				break;
			case invertSameRRect:
				break;
			case fillSameRRect:
				break;
			case frameOval:
				break;
			case paintOval:
				break;
			case eraseOval:
				break;
			case invertOval:
				break;
			case fillOval:
				port.fillOval(new Rect(pictData));
				break;
			case frameSameOval:
				break;
			case paintSameOval:
				break;
			case eraseSameOval:
				break;
			case invertSameOval:
				break;
			case fillSameOval:
				port.fillSameOval();
				break;
			case frameArc:
				break;
			case paintArc:
				Rect arcRect = new Rect(pictData);
				short startAngle = pictData.getShort();
				short arcAngle = pictData.getShort();
				port.paintArc(arcRect, startAngle, arcAngle);
				break;
			case eraseArc:
				break;
			case invertArc:
				break;
			case fillArc:
				break;
			case frameSameArc:
				break;
			case paintSameArc:
				break;
			case eraseSameArc:
				break;
			case invertSameArc:
				break;
			case fillSameArc:
				break;
			case framePoly:
				break;
			case paintPoly:
				port.paintPoly(Polygon.fromByteBuffer(pictData));
				break;
			case erasePoly:
				break;
			case invertPoly:
				break;
			case fillPoly:
				break;
			case frameSamePoly:
				break;
			case paintSamePoly:
				break;
			case eraseSamePoly:
				break;
			case invertSamePoly:
				break;
			case fillSamePoly:
				break;
			case frameRgn:
				break;
			case paintRgn:
				break;
			case eraseRgn:
				break;
			case invertRgn:
				break;
			case fillRgn:
				break;
			case frameSameRgn:
				break;
			case paintSameRgn:
				break;
			case eraseSameRgn:
				break;
			case invertSameRgn:
				break;
			case fillSameRgn:
				break;
			case BitsRect:
				break;
			case BitsRgn:
				break;
			case PackBitsRect:
				break;
			case PackBitsRgn:
				break;
			case ShortComment:
				short kind = pictData.getShort();
				System.out.printf("short comment: %d\n", kind);
				break;
			case LongComment:
				LongComment c = LongComment.fromByteBuffer(pictData);
				System.out.println(c);
				break;
			case EndOfPicture:
				System.out.println("found end-of-picture");
				endOfPictureFound = true;
				break;

			case reservedForApple_00:
				break;
			case reservedForApple_01:
				break;

			default:
				break;
			}
			pos = pictData.position() - startPos;
		}

		if (!endOfPictureFound) {
			System.out.println("Reached end of PICT data, but end-of-picture opcode was not found yet!");
		}

	}

	public static void main(String[] args) {

//		new Picture(Picture.DEMO_1);

		String filename = "src/test/resources/abscab.pict"; // QuickDraw v1.9.5 PICT
//		String filename = "src/test/resources/test17.pict"; // QuickDraw v1.7 PICT
//		String filename = "src/test/resources/test.pict"; // same as statically defined DEMO_1
		try (RandomAccessFile memoryFile = new RandomAccessFile(filename, "r")) {
			MappedByteBuffer buf = memoryFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, memoryFile.length());

			// skip header
			buf.position(512); // definitely needed for PICT

			Picture p = new Picture(buf);
			p.port.display();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public short picSize;
	public Rect picFrame;

	public GrafPort port;

}

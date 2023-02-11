package de.labathome.jQuickDraw;

import java.nio.ByteBuffer;

public class Polygon {

	public Polygon(short polySize, Rect polyBBox, Point[] polyPoints) {
		this.polySize = polySize;
		this.polyBBox = polyBBox;
		this.polyPoints = polyPoints;
	}

	public static Polygon fromByteBuffer(ByteBuffer buf) {
		short polySize = buf.getShort();
		Rect polyBBox = new Rect(buf);
		int numPoints = (polySize - (Short.BYTES + Rect.BYTES)) / Point.BYTES;
		Point[] polyPoints = new Point[numPoints];
		for (int i = 0; i < numPoints; ++i) {
			polyPoints [i] = new Point(buf);
		}
		return new Polygon(polySize, polyBBox, polyPoints);
	}

	@Override
	public String toString() {
		String polyString = String.format("Polygon{polySize=%d, polyBBox=%s, polyPoints=[", polySize, polyBBox);
		for (int i = 0; i < polyPoints.length; ++i) {
			if (i < polyPoints.length - 1) {
				polyString += polyPoints[i].toString() + ", ";
			} else {
				// last point
				polyString += polyPoints[i].toString() + "]}";
			}
		}
		return polyString;
	}

	public short polySize;
	public Rect polyBBox;
	public Point[] polyPoints;
}

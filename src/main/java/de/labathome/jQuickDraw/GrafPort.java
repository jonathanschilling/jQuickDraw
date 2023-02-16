package de.labathome.jQuickDraw;

public abstract class GrafPort {

	public GrafPort(Rect portFrame) {
		this.portRect = portFrame;
	}

	public void setClipRegion(Region clipRgn) {
		System.out.printf("set clip region: %s\n", clipRgn);
		this.clipRgn = clipRgn;
	}

	public void setFillPattern(Pattern fillPat) {
		System.out.printf("set fill pattern: %s\n", fillPat);
		this.fillPat = fillPat;
	}

	public void setBackgroundPattern(Pattern bkPat) {
		System.out.printf("set background pattern: %s\n", bkPat);
		this.bkPat = bkPat;
	}

	public void setPenPattern(Pattern pnPat) {
		System.out.printf("set pen pattern: %s\n", pnPat);
		this.pnPat = pnPat;
	}

	public void fillRect(Rect rectToFill) {
		System.out.printf("fill rect: %s\n", rectToFill);
		currentRect = rectToFill;
	}

	public void fillSameOval() {
		System.out.printf("fill same oval: %s\n", currentRect);
	}

	public void paintPoly(Polygon p) {
		System.out.printf("paint polygon: %s\n", p);
	}

	public void paintArc(Rect arcRect, short startAngle, short arcAngle) {
		System.out.printf("paint arc: rect=%s, startAngle=%d, arcAngle=%d\n", arcRect, startAngle, arcAngle);
	}

	public void line(Point pnLoc, Point newPt) {
		System.out.printf("line from %s to %s\n", pnLoc, newPt);
		this.pnLoc = pnLoc;
	}

	public void frameSameRect() {
		System.out.printf("frame same rect: %s\n", currentRect);
	}

	public void setTextFont(short txFont) {
		System.out.printf("set text font: %d\n", txFont);
		this.txFont = txFont;
	}

	public void invertRRect(Rect r) {
		System.out.printf("invert RRect: %s\n", r);
	}

	public void setFontByName(FontName fontName) {
		System.out.printf("set font by name: %s\n", fontName);
		this.namedFont = fontName;
	}

	public void setTextSize(short txSize) {
		System.out.printf("set text size: %d\n", txSize);
		this.txSize = txSize;
	}

	public void setGlyphState(GlyphState glyphState) {
		System.out.printf("set glyph state: %s\n", glyphState);
		this.glyphState = glyphState;
	}

	public void fillOval(Rect ovalRect) {
		System.out.printf("fill oval: %s\n", ovalRect);
	}

	public void setPenSize(Point pnSize) {
		System.out.printf("set pen size: %s\n", pnSize);
		this.pnSize = pnSize;
	}

	public void eraseRect(Rect toErase) {
		System.out.printf("erase rect: %s\n", toErase);
	}

	public void longText(LongText txt) {
		System.out.printf("long text: %s\n", txt);
	}

	public void setTextFace(short txFace) {
		System.out.printf("text face: %d\n", txFace);
		this.txFace = txFace;
	}

	public void dhText(DHText txt) {
		System.out.printf("dh text: %s\n", txt);
	}

	public void dhdvText(DHDVText txt) {
		System.out.printf("dhdv text: %s\n", txt);
	}

	public void shortLine(ShortLine sl) {
		System.out.printf("ShortLine: %s\n", sl);
		pnLoc = sl.pnLoc;
	}

	public void shortLineFrom(byte dh, byte dv) {
		System.out.printf("ShortLineFrom: dh=%d, dv=%d\n", dh, dv);
	}

	public void lineFrom(Point newPt) {
		System.out.printf("LineFrom: %s\n", newPt);
	}

	public Rect portRect;
	public Region clipRgn;
	public Pattern fillPat;
	public Pattern bkPat;

	public Point pnSize;
	public Pattern pnPat;
	public Point pnLoc;

	public Rect currentRect;

	public short txFont;
	public short txSize;
	public short txFace;


	public FontName namedFont;
	public GlyphState glyphState;

	public abstract void display();
}

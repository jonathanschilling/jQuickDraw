package de.labathome.jQuickDraw;

import java.util.Arrays;

import aliceinnets.python.jyplot.JyPlot;

public class JyPlotDevice extends GrafPort {

	private JyPlot plt;
	
	public JyPlotDevice(Rect portFrame) {
		super(portFrame);
		
		plt = new JyPlot();
		
		drawRect(portFrame, "r-", "portFrame");
	}
	
	@Override
	public void setClipRegion(Region clipRegin) {
		super.setClipRegion(clipRegin);
		
		drawRect(clipRgn.rgnBBox, "b--", "clipRgn.rgnBBox");
	}
	
	@Override
	public void fillRect(Rect r) {
		super.fillRect(r);
		
		int w = r.right() - r.left();
		int h = r.bottom() - r.top();
		
		boolean[][] bmp = new boolean[h][w];
		
		for (int y = 0; y < h; ++y) {
			byte patLine = fillPat.p[y % fillPat.p.length];
			for (int x = 0; x < w; ++x) {
				int pX = x % Byte.SIZE;
				if ((patLine & (1 << pX)) != 0) {
					bmp[y][x] = false;
				} else {
					bmp[y][x] = true;
				}
			}
		}
		
		short[] xPos = new short[w + 1];
		for (int x = 0; x < w; ++x) {
			xPos[x] = (short) (r.left() + x);
		}
		xPos[w] = r.right();
		
		short[] yPos = new short[h + 1];
		for (int y = 0; y < h; ++y) {
			yPos[y] = (short) (r.top() + y);
		}
		yPos[h] = r.bottom();
		
		plt.pcolormesh(xPos, yPos, bmp, "cmap='gray'");
	}
	
	@Override
	public void fillSameOval() {
		super.fillSameOval();
		
		Rect r = currentRect;
		
		int w = r.right() - r.left();
		int h = r.bottom() - r.top();
		
		double w2 = w / 2.0;
		double h2 = h / 2.0;
		
		float[][] bmp = new float[h][w];
		
		for (int y = 0; y < h; ++y) {
			
			// transparent by default
			Arrays.fill(bmp[y], Float.NaN);
			
			byte patLine = fillPat.p[y % fillPat.p.length];
			for (int x = 0; x < w; ++x) {
				
				// figure out if inside oval
				double x2_w2 = (x-w2)*(x-w2) / (w2 * w2);
				double y2_h2 = (y-h2)*(y-h2) / (h2 * h2);
				
				if (x2_w2 + y2_h2 < 1) {
					int pX = x % Byte.SIZE;
					if ((patLine & (1 << pX)) != 0) {
						bmp[y][x] = 0;
					} else {
						bmp[y][x] = 1;
					}
				}
			}
		}
		
		short[] xPos = new short[w + 1];
		for (int x = 0; x < w; ++x) {
			xPos[x] = (short) (r.left() + x);
		}
		xPos[w] = r.right();
		
		short[] yPos = new short[h + 1];
		for (int y = 0; y < h; ++y) {
			yPos[y] = (short) (r.top() + y);
		}
		yPos[h] = r.bottom();
		
		plt.pcolormesh(xPos, yPos, bmp, "cmap='gray'");
	}
	
	@Override
	public void paintPoly(Polygon p) {
		super.paintPoly(p);
		
		int numPoints = p.polyPoints.length;
		
		short[][] pp = new short[2][numPoints];
		for (int i = 0; i < numPoints; ++i) {
			pp[0][i] = p.polyPoints[i].h;
			pp[1][i] = (short) (p.polyBBox.top() - p.polyPoints[i].v + p.polyBBox.bottom());
		}
		
		plt.fill(pp[0], pp[1], "fc='k'");
	}
	
	private void drawRect(Rect r, String format, String label) {
		short[][] corners = {
				{r.left(), r.right(), r.right(),  r.left(),   r.left()},
				{r.top(),  r.top(),   r.bottom(), r.bottom(), r.top()}
		};
		
		plt.plot(corners[0], corners[1], format, "label='"+label+"'");
	}
	
	public void display() {
		plt.axis("equal");
//		plt.legend();
		
		plt.show();
		plt.exec();
	}
}


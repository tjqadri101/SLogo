package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TurtleImage {

	private final double DEFAULT_HEADING = 90;
	private final double CENTER_X = 320;
	private final double CENTER_Y = 240;
	private final double SIZE_X = 320;
	private final double SIZE_Y = 240;
	private final Dimension SIZE = new Dimension(640, 480);

	private BufferedImage myTurtleImage;
	private File myTurtleFile;

	public TurtleImage() {
		myTurtleFile = TurtleFileChooser.initFileChooser();
	}
	
	/**
	 * Benson to All: I created a getter method for the image.
	 * Good code or nah?
	 */

	public BufferedImage getTurtleImage() {
		return myTurtleImage;
	}

	/*
	 * Creates and scales image from File Chooser Scales Image to Smaller Size
	 */

	public void setImage() throws IOException {
		
		BufferedImage in = ImageIO.read(myTurtleFile);
		myTurtleImage = new BufferedImage(in.getWidth(), in.getHeight(),
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = myTurtleImage.createGraphics();

		scale(myTurtleImage, 100, 100);
		paintWrapper(g);

		// g.drawImage(in, 0, 0, null);
		// g.dispose();
	}

	/*
	 * Wrapper function for paint(). Draws image on screen.
	 */

	public void paintWrapper(Graphics2D pen) {
		paint(pen, SIZE.getWidth() / 2, SIZE.getHeight() / 2, SIZE_X, SIZE_Y,
				DEFAULT_HEADING);
	}

	/*
	 * Rotates image on screen. Step 1: Save state of image. Step 2: Move
	 * graphics are to center of shape. Step 3: Rotate. Step 4: Draw rotated
	 * image. Step 5: Restore are from Step 2 to Step 1. (Why?)
	 */

	public void paint(Graphics2D pen, double xCenter, double yCenter,
			double xSize, double ySize, double angle) {

		AffineTransform old = new AffineTransform(pen.getTransform());
		pen.translate(xCenter, yCenter);
		pen.rotate(Math.toRadians(angle));
		pen.drawImage(myTurtleImage, (int) xCenter, (int) yCenter, (int) xSize,
				(int) ySize, null);
		pen.setTransform(old);
	}

	public BufferedImage scale(BufferedImage img, int targetWidth,
			int targetHeight) {

		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = img;
		BufferedImage scratchImage = null;
		Graphics2D g2 = null;

		int w = img.getWidth();
		int h = img.getHeight();

		int prevW = w;
		int prevH = h;

		do {
			if (w > targetWidth) {
				w /= 2;
				w = (w < targetWidth) ? targetWidth : w;
			}

			if (h > targetHeight) {
				h /= 2;
				h = (h < targetHeight) ? targetHeight : h;
			}

			if (scratchImage == null) {
				scratchImage = new BufferedImage(w, h, type);
				g2 = scratchImage.createGraphics();
			}

			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

			prevW = w;
			prevH = h;
			ret = scratchImage;
		} while (w != targetWidth || h != targetHeight);

		if (g2 != null) {
			g2.dispose();
		}

		if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
			scratchImage = new BufferedImage(targetWidth, targetHeight, type);
			g2 = scratchImage.createGraphics();
			g2.drawImage(ret, 0, 0, null);
			g2.dispose();
			ret = scratchImage;
		}

		return ret;

	}

}

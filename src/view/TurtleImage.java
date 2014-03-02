package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.ImageIcon;

public class TurtleImage {

	private Image myImage;
	private String myFileName;

	private final double DEFAULT_HEADING = 90;
	private final double CENTER_X = 320;
	private final double CENTER_Y = 240;
	private final double SIZE_X = 320;
	private final double SIZE_Y = 240;
	private final Dimension SIZE = new Dimension(640, 480);

	public TurtleImage(String fileName) {
		setImage(fileName);
	}

	public void setImage(String fileName) {
		myImage = new ImageIcon(getClass().getResource(fileName)).getImage();
		myFileName = fileName;
	}

	/*
	 * Wrapper function for paint(). Draws image on screen.
	 */

	public void paintCenter(Graphics2D pen) {
		paint(pen, SIZE.getWidth() / 2, SIZE.getHeight() / 2, SIZE_X, SIZE_Y, DEFAULT_HEADING);
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
		pen.drawImage(myImage, (int) xCenter, (int) yCenter, (int) xSize,
				(int) ySize, null);
		pen.setTransform(old);
	}

}

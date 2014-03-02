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

	private final double DEFAULT_HEADING = 0;
	private final double X_CENTER = 320;
	private final double Y_CENTER = 240;

	private BufferedImage myTurtleImage;
	private File myTurtleFile;

	public TurtleImage() {
		myTurtleFile = TurtleFileChooser.initFileChooser();
	}

	public BufferedImage setImage() throws IOException {

		myTurtleImage = ImageIO.read(myTurtleFile);
		return myTurtleImage;
	}

	/*
	 * Wrapper function for paint(). Draws image on screen.
	 */

	public void paintCenter(Graphics2D pen, BufferedImage turtle) {
		paint(pen, X_CENTER, Y_CENTER, 0, 0, DEFAULT_HEADING, turtle);
	}

	/*
	 * Rotates image on screen. 
	 * Step 1: Save state of image via AffineTransform.
	 * Step 2: Move relative to current location via translate and dX & dY. 
	 * Step 3: Rotate about center of object. 
	 * Step 4: Draw.
	 * Step 5: Restore from Step 2 to Step 1. 
	 */

	public void paint(Graphics2D pen, double xCenter, double yCenter,
			double deltaX, double deltaY, double angle, BufferedImage turtle) {

		AffineTransform old = new AffineTransform(pen.getTransform());
		
		pen.translate(deltaX, deltaY);
		pen.rotate(Math.toRadians(-angle), xCenter, yCenter);

		
		pen.drawImage(turtle, (int) xCenter, (int) yCenter, null);
		pen.setTransform(old);
	}
}

package graphics;

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

	private int myWidth;
	private int myHeight;
	private int myTopLeftX;
	private int myTopLeftY;

	private BufferedImage myTurtleImage;
	private File myTurtleFile;

	public TurtleImage() {
		myTurtleFile = TurtleFileChooser.initFileChooser();
	}

	public BufferedImage setImage() throws IOException {

		myTurtleImage = ImageIO.read(myTurtleFile);
		myWidth = myTurtleImage.getWidth();
		myHeight = myTurtleImage.getHeight();
		return myTurtleImage;
	}


	/*
	 * Rotates image on screen. Step 1: Save state of image via AffineTransform.
	 * Step 2: Move relative to current location via translate and dX & dY. Step
	 * 3: Rotate about center of object. Step 4: Draw. Step 5: Restore from Step
	 * 2 to Step 1.
	 */

	public void paint(Graphics2D pen, double xCenter, double yCenter,
			double deltaX, double deltaY, double angle, BufferedImage turtle) {
		

		initializeTopLeftCorner(xCenter, yCenter);
		
		AffineTransform old = new AffineTransform(pen.getTransform());	
		pen.translate(deltaX, deltaY);
		pen.rotate(Math.toRadians(-angle), xCenter, yCenter);
		//image drawn at top left corner
		//taking rotation into account
		pen.drawImage(turtle, myTopLeftX, myTopLeftY, null);
		pen.setTransform(old);
	}

	private void initializeTopLeftCorner(double xCenter, double yCenter) {
		// TODO Auto-generated method stub
		myTopLeftX = ((int) xCenter) - myWidth/2;
		myTopLeftY = ((int) yCenter) - myHeight/2;
	}
}

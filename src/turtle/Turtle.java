package turtle;

public class Turtle {

	private double myX;
	private double myY;
	
    /**
     * 0 degrees: Right.
     * 90 degrees: Up.
     * 180 degrees: Left.
     * 270 degrees: Down.
     */

	private double myAngle = 90;

	public Turtle() {
		// TODO: set default initial turtle position
	}

	public Turtle(double initialX, double initialY, double initialAngle) {
		myX = initialX;
		myY = initialY;
		myAngle = initialAngle;
	}

	public void updatePosition(double changeInAngle, double changeInDistance) {
		myAngle += changeInAngle;
		if (myAngle >= 360) {
			myAngle -= 360;
		}

		myX += changeInDistance * Math.cos(myAngle * (Math.PI / 180));
		myY += changeInDistance * Math.sin(myAngle * (Math.PI / 180));
	}

	public void setPosition(double xCoord, double yCoord) {
		myX = xCoord;
		myY = yCoord;
	}

	public void setHeading(double angle) {
		myAngle = angle;
	}

	public double getXPos() {
		return myX;
	}

	public double getYPos() {
		return myY;
	}
	
	public double getAngle(){
		return myAngle;
	}

}

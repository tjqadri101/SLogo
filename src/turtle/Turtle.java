package turtle;


public class Turtle {

	private double myX;
	private double myY;
	private double myPen;
	private final double PEN_DOWN = 1;
	private final double PEN_UP = 0;
	private double myAngle = 90;
	
    /**
     * 0 degrees: Right.
     * 90 degrees: Up.
     * 180 degrees: Left.
     * 270 degrees: Down.
     */

	public Turtle() {
		// TODO: set default initial turtle position
	}

	public Turtle(double initialX, double initialY, double initialAngle) {
		myX = initialX;
		myY = initialY;
		myAngle = initialAngle;
		myPen = PEN_UP;
	}

	public void updatePosition(double changeInAngle, double changeInDistance) {
		myAngle += changeInAngle;
		if (myAngle >= 360) {
			myAngle -= 360;
		}

		myX += changeInDistance * Math.cos(myAngle * (Math.PI / 180));
		myY += changeInDistance * Math.sin(myAngle * (Math.PI / 180));
	}
	
	
	
	/*
	 * Sets
	 */

	public void setPosition(double xCoord, double yCoord) {
		myX = xCoord;
		myY = yCoord;
	}

	public void setHeading(double angle) {
		myAngle = angle;
	}
	
	public void setPenUp(){
		myPen = PEN_UP;
	}
	
	public void setPenDown(){
		myPen = PEN_DOWN;
	}
	
	/*
	 * Gets
	 */

	public double getXPos() {
		return myX;
	}

	public double getYPos() {
		return myY;
	}
	
	public double getAngle(){
		return myAngle;
	}
	
	public double getPen(){
		return myPen;
	}


}

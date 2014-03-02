package turtle;

public class Turtle {
    
//    public Turtle() {
        //TODO: set default initial turtle position
//    }
    //TODO ONE BACKEND FOR ONE GAME
    // keep track of what's active: parse once and apply the changes to active turtles
    // TODO expand the backend: model; 

   

	private final double PEN_DOWN = 1;
	private final double PEN_UP = 0;
	private final double VISIBLE = 1;
	private final double INVISIBLE = 0;
	private final double DEFAULT_HEADING = 0;

	private double myX;
	private double myY;
	private double myPen;
	private double clearToggle;
	private double myVisibility;
	private double myAngle = DEFAULT_HEADING;

	/**
	 * 0 degrees: Up. 90 degrees: left. 180 degrees: down. 270 degrees: right.
	 */

	public Turtle() {
		// TODO: set default initial turtle position
	}

	public Turtle(double initialX, double initialY, double initialAngle) {
		myX = initialX;
		myY = initialY;
		myAngle = DEFAULT_HEADING;
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

	/**
	 * Benson to Tara: Couldn't think of anything better than to create a toggle
	 * for the clear.
	 */

	public void clearScreen() {
		clearToggle = 1;
	}

	public void resetClear() {
		clearToggle = 1;
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

	public void setPenUp() {
		myPen = PEN_UP;
	}

	public void setPenDown() {
		myPen = PEN_DOWN;
	}

	public void setInvisible() {
		myVisibility = INVISIBLE;
	}

	public void setVisible() {
		myVisibility = VISIBLE;
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

	public double getAngle() {
		return myAngle;
	}

	public double getPen() {
		return myPen;
	}

	public double getVisibility() {
		return myVisibility;
	}
}

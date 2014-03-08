package turtle;

public interface ITurtle {

	/**
	 * The purpose of this interface is to provide the front end with some
	 * properties of the turtle class that is instantiated in the back end.
	 * 
	 * This provides a level of abstraction and protection from the front end to
	 * the back end.
	 */

	/*
	 * Coordinate Parameters for TurtleImage from Turtle in Model
	 */

	double getPrevX();

	double getPrevY();

	double getDeltaX();

	double getDeltaY();

	double getAngle();

	double getPenToggle();
	
	double getCurX();
	
	double getCurY();

	String getPenColor();

	
}

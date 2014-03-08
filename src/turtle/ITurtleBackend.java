package turtle;

public interface ITurtleBackend {

	/**
	 * The purpose of this interface is to provide the front end with some
	 * properties of the turtle class that is instantiated in the back end.
	 * 
	 * This provides a level of abstraction and protection from the front end to
	 * the back end.
	 */
	
	/*
	 * Coordinate Parameters to set in TurtleViewPanel
	 */
	
	void setPrevX();

	void setPrevY();

	void setDeltaX();

	void setDeltaY();

	void setAngle();

	void setPenToggle();
	
	void setCurX();
	
	void setCurY();

	void setPenColor();
	
}

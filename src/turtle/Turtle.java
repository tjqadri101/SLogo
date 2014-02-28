package turtle;

public class Turtle {
    
    private double myX;
    private double myY;
    private double angle = 90;
    
    public Turtle() {
        //TODO: set default initial turtle position
    }
    
    public Turtle(double initialX, double initialY) {
        myX = initialX;
        myY = initialY;
    }
    
    public void updatePosition(double changeInX, double changeInY) {
        myX += changeInX;
        myY += changeInY;
    }
    
    public double getXPos() {
        return myX;
    }
    
    public double getYPos() {
        return myY;
    }
    
    /**
     * Angle stuff added by Benson (Feel free to change).
     * Let's define the neutral position at 90 degrees?
     * So the default position should be the turtle facing in the positive y - axis (90 degrees).
     * 
     * 0 degrees: Right.
     * 90 degrees: Up.
     * 180 degrees: Left.
     * 270 degrees: Down.
     * 
     * Turning left = CCW = positive change in angle.
     * Turning right = CW = negative change in angle.
     */
    
    public void updateAngle(double changeInAngle){
    	angle += changeInAngle;
    }
    
    public double getAngle(){
    	return angle;
    }
    
    
}

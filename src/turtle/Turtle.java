package turtle;

public class Turtle {
    
    private double myX;
    private double myY;
    private double myAngle = 90;
    
    public Turtle() {
        //TODO: set default initial turtle position
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
        
        myX += changeInDistance * Math.cos(myAngle*(Math.PI/180));
        myY += changeInDistance * Math.sin(myAngle*(Math.PI/180));
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
     * 
     * Need to mod360?
     */
    
    public void updateAngle(double changeInAngle){
    	myAngle += changeInAngle;
    }
    
    public double getAngle(){
    	return myAngle;
    }
    
    
}

package turtle;

public class Turtle {
    
    private double myX;
    private double myY;
    
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
    
    
}

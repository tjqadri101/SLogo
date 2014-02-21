package nodes;

import turtle.Turtle;

public class ForwardNode extends ActionNode {

    private Turtle myTurtle;
    private double myValue;
    
    public ForwardNode (Turtle turtle, String token, double value) {
        super(turtle, token, value);
        myValue = value;
        myTurtle = turtle;
    }
    
    public void action() {
        myTurtle.updatePosition(0, myValue);
    }

}

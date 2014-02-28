package nodes;

import turtle.Turtle;

public class VariableNode extends AbstractNode{ //TODO a function can be a variable node

    private double myValue;
    private Turtle myTurtle; 
    
    public VariableNode (Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }
    
    public VariableNode (Turtle turtle, double value) {
        this(turtle);
        myValue = value;
    }

    @Override
    public void action () {
        // do nothing
    }

    @Override
    public double evaluate () {
        return myValue;
    }

    @Override
    public boolean allowsTwoChildren () {
        return false;
    }

    @Override
    public boolean allowsMoreThanTwoChildren () {
        return false;
    }

}

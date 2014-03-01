package nodes;

import turtle.Turtle;

public class NumberNode extends AbstractNode{

    private double myValue;
    private Turtle myTurtle; 
    
    public NumberNode (Turtle turtle, double value) {
        super(turtle);
        myValue = value;
        myTurtle = turtle;
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
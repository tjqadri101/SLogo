package nodes;

import turtle.Turtle;

public class NumberNode extends AbstractNode{

    private double myValue;
    
    public NumberNode (Turtle turtle, double value) {
        super(turtle);
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
    public boolean allowsThreeChildren () {
        return false;
    }

}

package nodes;

import turtle.Turtle;

public class VariableNode extends AbstractNode{

    private double myValue;
    
    public VariableNode (Turtle turtle) {
        super(turtle);
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

}

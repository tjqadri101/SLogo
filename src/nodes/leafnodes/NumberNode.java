package nodes.leafnodes;

import turtle.Turtle;

public class NumberNode extends LeafNode{

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

}

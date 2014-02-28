package nodes.controlnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class IfElseNode extends AbstractNode {

    private Turtle myTurtle; 
    
    public IfElseNode (Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    @Override
    public void action () {
        // TODO Auto-generated method stub

    }

    @Override
    public double evaluate () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean allowsTwoChildren () {
        return true;
    }

    @Override
    public boolean allowsMoreThanTwoChildren () {
        return false;
    }

}

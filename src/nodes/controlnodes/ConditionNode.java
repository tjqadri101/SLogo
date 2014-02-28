package nodes.controlnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class ConditionNode extends AbstractNode {

    private Turtle myTurtle; 
    
    public ConditionNode (Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    @Override
    public double evaluate () {
        return this.getLeftNode().evaluate();
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

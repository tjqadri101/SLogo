package nodes.commandnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class ForwardNode extends AbstractNode {

    private Turtle myTurtle;
    
    public ForwardNode (Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    public void setTurtle (Turtle turtle) {
        myTurtle = turtle;
    }

    
    public void action() {
        double distance = this.getLeftNode().evaluate();
        myTurtle.updatePosition(0, distance);
    }




    @Override
    public double evaluate () {
        return 0;
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

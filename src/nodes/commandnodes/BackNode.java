package nodes.commandnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class BackNode extends AbstractNode {
    private Turtle myTurtle;

    public BackNode(Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    public void setTurtle(Turtle turtle) {
        myTurtle = turtle;
    }

    @Override
    public double evaluate() {
        double distance = this.getLeftNode().evaluate();
        double angle = myTurtle.getAngle();
        myTurtle.updatePosition(-distance*Math.cos(angle*(Math.PI/180)), -distance*Math.sin(angle*(Math.PI/180)));
        return distance;
    }

    @Override
    public boolean allowsTwoChildren() {
        return false;
    }

    @Override
    public boolean allowsMoreThanTwoChildren() {
        return false;
    }
}

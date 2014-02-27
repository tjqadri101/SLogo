package nodes.controlnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class IfElseNode extends AbstractNode {

    public IfElseNode (Turtle turtle) {
        super(turtle);
        // TODO Auto-generated constructor stub
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
    public boolean allowsTwo () {
        return true;
    }

    @Override
    public boolean allowsThree () {
        return false;
    }

}

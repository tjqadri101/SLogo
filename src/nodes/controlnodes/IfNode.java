package nodes.controlnodes;

import turtle.Turtle;
import nodes.AbstractNode;

public class IfNode extends AbstractNode {

    public IfNode (Turtle turtle) {
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

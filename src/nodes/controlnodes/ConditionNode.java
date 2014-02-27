package nodes.controlnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class ConditionNode extends AbstractNode {

    public ConditionNode (Turtle turtle) {
        super(turtle);
    }

    @Override
    public void action () {
        // do nothing
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

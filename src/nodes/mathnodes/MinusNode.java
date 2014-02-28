package nodes.mathnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class MinusNode extends AbstractNode {
    private Turtle myTurtle;

    public MinusNode(Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    @Override
    public double evaluate () {

        AbstractNode child = this.getLeftNode();
        return child.evaluate() * -1;
    }

    /**
     * Both are false because this node only has one Child
     */
    @Override
    public boolean allowsTwoChildren() {
        return false;
    }

    @Override
    public boolean allowsMoreThanTwoChildren() {
        return false;
    }

}

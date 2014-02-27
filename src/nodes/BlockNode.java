package nodes;

import turtle.Turtle;

public class BlockNode extends AbstractNode {

    public BlockNode (Turtle turtle) {
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
    public boolean allowsTwoChildren () {
        return true;
    }

    @Override
    public boolean allowsMoreThanTwoChildren () {
        return true;
    }

}

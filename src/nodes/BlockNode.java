package nodes;

import turtle.Turtle;

public class BlockNode extends AbstractNode {

    private Turtle myTurtle; 
    
    public BlockNode (Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
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

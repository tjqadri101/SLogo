package nodes;

import java.util.List;
import turtle.Turtle;

public class BlockNode extends AbstractNode {

    private List<Turtle> myTurtles; 
    
    public BlockNode (List<Turtle> turtles) {
        super(turtles);
        myTurtles = turtles;
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

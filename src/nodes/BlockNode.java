package nodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import turtle.Turtle;

public class BlockNode extends AbstractNode {

    private List<Turtle> myTurtles; 
    
    public BlockNode (List<Turtle> turtles) {
        super(turtles);
        myTurtles = turtles;
    }

    @Override
    public double evaluate () throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
        return (this.getLeftNode().evaluate() + this.getRightNode().evaluate());
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

package nodes.controlnodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import nodes.AbstractNode;
import turtle.Turtle;

public class ConditionNode extends AbstractNode {

    private Turtle myTurtle; 
    
    public ConditionNode (Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    @Override
    public double evaluate () throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
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

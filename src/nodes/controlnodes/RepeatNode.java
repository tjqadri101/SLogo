package nodes.controlnodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import turtle.Turtle;
import nodes.AbstractNode;

public class RepeatNode extends AbstractNode {

    private Turtle myTurtle; 
    
    public RepeatNode (Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    private double traverseSubtree() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
        // check left (condition node)
        AbstractNode conditionNode = this.getLeftNode();
        double count = conditionNode.evaluate();
        while (count > 0) {
            this.getRightNode().evaluate();
            count --;
        }
        return 0;
    }

    @Override
    public double evaluate () throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
        return traverseSubtree();
    }

    @Override
    public boolean allowsTwoChildren () {
        return true;
    }

    @Override
    public boolean allowsMoreThanTwoChildren () {
        return false;
    }

}

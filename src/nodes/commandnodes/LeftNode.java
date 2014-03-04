package nodes.commandnodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import nodes.AbstractNode;
import turtle.Turtle;

public class LeftNode extends AbstractNode {

    private Turtle myTurtle;

    public LeftNode(Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    public void setTurtle(Turtle turtle) {
        myTurtle = turtle;
    }

    @Override
    public double evaluate() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
        double deltaAngle = this.getLeftNode().evaluate();
        myTurtle.updatePosition(deltaAngle, 0);
        return deltaAngle;
    }

    @Override
    public boolean allowsTwoChildren() {
        return false;
    }

    @Override
    public boolean allowsMoreThanTwoChildren() {
        return false;
    }

}

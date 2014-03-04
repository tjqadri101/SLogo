package nodes.mathnodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import nodes.AbstractNode;
import turtle.Turtle;

public class LogNode extends AbstractNode {
    private Turtle myTurtle;

    public LogNode(Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    @Override
    public double evaluate () throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {

        AbstractNode child = this.getLeftNode();
        double expr = child.evaluate();
        double result = Math.log(expr);

        return result;

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

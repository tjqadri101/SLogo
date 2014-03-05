package nodes.controlnodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import turtle.Turtle;
import nodes.AbstractNode;

public class DoTimesNode extends AbstractNode {

    public DoTimesNode (List<Turtle> turtles) {
        super(turtles);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double evaluate () throws ClassNotFoundException, NoSuchMethodException,
                             SecurityException, InstantiationException, IllegalAccessException,
                             IllegalArgumentException, InvocationTargetException,
                             NoSuchFieldException, IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean allowsTwoChildren () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean allowsMoreThanTwoChildren () {
        // TODO Auto-generated method stub
        return false;
    }

}

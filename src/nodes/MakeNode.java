package nodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import turtle.Turtle;

public class MakeNode extends AbstractNode {

    public MakeNode (Turtle turtle) {
        super(turtle);
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

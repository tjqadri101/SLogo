package nodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import turtle.Turtle;

public class FunctionNode extends AbstractNode{

    private String myName;
    
    private List<Turtle> myTurtles;
    
    public FunctionNode(List<Turtle> turtles) {
        super(turtles);
        myTurtles = turtles;
    }


    public void setName (String string) {
        myName = string;
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
    public boolean allowsTwoChildren () { // allows only one child
        return false;
    }

    @Override
    public boolean allowsMoreThanTwoChildren () {
        return false;
    }
    
    
    
    

}

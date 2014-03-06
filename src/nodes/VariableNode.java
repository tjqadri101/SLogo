package nodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import model.Parser;
import turtle.Turtle;

public class VariableNode extends AbstractNode{ 

    private double myCurrentValue;
    private List<Turtle> myTurtles;

    private double myStartingValue;
    private double myEndingValue;
    private double myIncrement;

    private String myVariableName;
    
    private boolean myIsAlreadyDeclaredBoolean;

    public VariableNode (List<Turtle> turtles, String variableName) {
        super(turtles);
        myTurtles = turtles;
        myVariableName = variableName;
    }


    public VariableNode (List<Turtle> turtles, String variableName, double value) {
        this(turtles, variableName);
        myCurrentValue = value;
    }

    public VariableNode (List<Turtle> turtles, String variableName, double startingValue, double endingValue, double increment) {
        this(turtles, variableName);
        myStartingValue = startingValue;
        myEndingValue = endingValue;
        myIncrement = increment;

    }

    public double getEndingValue() {
        return myEndingValue;
    }

    public double getStartingValue() {
        return myStartingValue;
    }

    public double getIncrement() {
        return myIncrement;
    }
    
    @Override
    public void setCurrentValue(double value) {
        myCurrentValue = value;
    }

    public void setIncrement(double increment) {
        myIncrement = increment;
    }

    @Override
    public double evaluate () throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {

            return myCurrentValue;
        
    }


    public String getName () {
        return myVariableName;
    }
    
    @Override
    public boolean isAlreadyDeclared() {
        return myIsAlreadyDeclaredBoolean;
        
    }
    
    @Override
    public void setIsAlreadyDeclaredBoolean(boolean boo) {
        myIsAlreadyDeclaredBoolean = boo;
    }


    @Override
    public boolean allowsTwoChildren () { // can allow only one child
        return false;
    }


    @Override
    public boolean allowsMoreThanTwoChildren () {
        return false;
    }

}

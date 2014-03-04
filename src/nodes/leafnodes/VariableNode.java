package nodes.leafnodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import nodes.AbstractNode;
import model.Function;
import model.Parser;
import turtle.Turtle;

public class VariableNode extends LeafNode{ //TODO a function can be a variable node

    private double myCurrentValue;
    private Turtle myTurtle; 

    private double myStartingValue;
    private double myEndingValue;
    private double myIncrement;

    private String myVariableName;

    /**
     * A function can be a varible node too
     */
    private Function myFunction = null;

    public VariableNode (Turtle turtle, String variableName) {
        super(turtle);
        myTurtle = turtle;
        myVariableName = variableName;
    }


    public VariableNode (Turtle turtle, String variableName, double value) {
        this(turtle, variableName);
        myCurrentValue = value;
    }

    public VariableNode (Turtle turtle, String variableName, double startingValue, double endingValue, double increment) {
        this(turtle, variableName);
        myStartingValue = startingValue;
        myEndingValue = endingValue;
        myIncrement = increment;

    }

    public VariableNode(Turtle turtle, String variableName, Function function) {
        this(turtle, variableName);
        myFunction = function;
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

    public void setCurrentValue(double value) {
        myCurrentValue = value;
    }

    public void setIncrement(double increment) {
        myIncrement = increment;
    }

    @Override
    public double evaluate () throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
        if (myFunction == null) {
            return myCurrentValue;
        } else {
            Turtle turtle = this.myTurtle;
            List<Turtle> activeTurtles = this.getActiveTurtles(); 
            Parser parser = new Parser(turtle, activeTurtles);
            AbstractNode root = parser.createTree(myFunction, turtle);
            return parser.traverseTree(turtle, root);
        }
    }


}

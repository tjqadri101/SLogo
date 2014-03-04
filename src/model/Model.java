package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class Model {
    
    private List<Turtle> myAllTurtles = new ArrayList<Turtle>();
    private List<Turtle> myActiveTurtles = new ArrayList<Turtle>();
    private List<Turtle> myInactiveTurtles = new ArrayList<Turtle>();
    private Map<Parser, Turtle> myParserTurtleMap = new HashMap<Parser,Turtle>();
    
    public Model(List<Turtle> turtles) {
        myAllTurtles = turtles;
        initialize();
    }
    
    /**
     * Call this method to process commands, update turtle position, and change workspace display
     */
    public void updateWorkspace() {
        //TODO
    }
    
    /**
     * Create parser objects for each turtle, put them in myParserTurtleMap
     */
    public void initialize() {
        for (Turtle thisTurtle : myAllTurtles) {
            Parser parser = new Parser(thisTurtle, myAllTurtles);
            myParserTurtleMap.put(parser, thisTurtle);
        }
    }
    
    
    public void setActiveTurtles(List<Turtle> activeTurtles) {
        myActiveTurtles = activeTurtles;
    }
    
    public void addActiveTurtles(List<Turtle> activeTurtles) {
        myActiveTurtles.addAll(activeTurtles);
    }
    
    public List<Turtle> getActiveTurtles() {
        return myActiveTurtles;
    }
    
    public void setInactiveTurtles(List<Turtle> inactiveTurtles) {
        myInactiveTurtles = inactiveTurtles;
    }
    
    public void addInactiveTurtles(List<Turtle> inactiveTurtles) {
        myInactiveTurtles.addAll(inactiveTurtles);
    }
    
    
}

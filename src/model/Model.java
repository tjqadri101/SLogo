package model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    
    public Model() {
        
    }
    
    /**
     * Call this method to process commands, update turtle position, and change workspace display
     * @return 
     * @throws IOException 
     * @throws NoSuchFieldException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws ClassNotFoundException 
     */
    public double processCommands(String string, String language, List<Turtle> turtles) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
        myAllTurtles = turtles;
        myActiveTurtles = turtles;
        
        Parser parser = new Parser(turtles, string, language);
        return parser.doParse();
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

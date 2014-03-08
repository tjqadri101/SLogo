package model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import turtle.AbstractModel;
import turtle.Turtle;

public class Model{
    
    private List<Turtle> myAllTurtles = new ArrayList<Turtle>();
    private List<Turtle> myActiveTurtles = new ArrayList<Turtle>();
    private List<Turtle> myInactiveTurtles = new ArrayList<Turtle>();
    private Map<String, Parser> myWorkspaceParserMap = new HashMap<String,Parser>();
//    private Map<String, List<Turtle>> myWorkspaceTurtleMap = new HashMap<String, List<Turtle>>();
//    private Map<String, String> myWorkspaceCommandMap = new HashMap<String, String>();
    
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
    public Map<String, Double> processCommands(Map<String, List<Turtle>> turtleMap, Map<String, String> commandMap, 
                                  Map<String, String> languageMap) throws ClassNotFoundException, NoSuchMethodException, 
                                  SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, 
                                  InvocationTargetException, NoSuchFieldException, IOException {
        Map<String, Double> returnedValueMap = new HashMap<String, Double>();
        for (String thisWorkspace : turtleMap.keySet()) {
            List<Turtle> turtles = turtleMap.get(thisWorkspace);
            String commands = commandMap.get(thisWorkspace);
            String languageChoice = languageMap.get(thisWorkspace);
            Parser parser = new Parser(turtles, commands, languageChoice);
            returnedValueMap.put(thisWorkspace, parser.doParse());
        }
        
        return returnedValueMap;
    }
    
    
}

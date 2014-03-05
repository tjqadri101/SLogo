package nodes;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import nodes.leafnodes.NumberNode;
import model.CommandFinder;
import model.CommandReader;
import turtle.Turtle;

public class NodeFactory {

    private List<Turtle> myTurtles;
    private String myLanguage;

    private final String[] PACKAGES = { "nodes.booleannodes.",
                                        "nodes.commandnodes.", "nodes.controlnodes.", "nodes.mathnodes",
                                        "nodes.booleannodes", "nodes." };


    public NodeFactory(List<Turtle> turtles, String language) {
        myTurtles = turtles;
        myLanguage = language;
    }


    public AbstractNode createNode(String word) throws ClassNotFoundException,
    NoSuchMethodException, SecurityException, InstantiationException,
    IllegalAccessException, IllegalArgumentException,
    InvocationTargetException, IOException, NoSuchFieldException {

        AbstractNode genericNode = null;

        Map<String, String> commandsMap = CommandReader
                .readCommands(CommandFinder.aliasLookup(myLanguage));

        //TODO replace with reflection code; this is just hardcoding VariableNode
        if (word.charAt(0)==':') {
            genericNode = new VariableNode(myTurtles, word.substring(1));
        } else {

            if (!isNumeric(word)) {

                String command = commandsMap.get(word.toUpperCase());
                Class<?> c = Class.forName(findClass(command));

                Constructor<?> constructor = c.getConstructor(List.class);
                genericNode = (AbstractNode) constructor.newInstance(myTurtles);

            }

            else if (isNumeric(word)) {
                return new NumberNode(myTurtles, Double.parseDouble(word));
            }
        }

        return genericNode;

    }


    private String findClass(String command) {
        for (String s : PACKAGES) {
            if (classExists(s + command + "Node")) {
                String classLocation = s + command + "Node";
                return classLocation;
            }
        }
        return null;
        /**
         * Need to throw exception up to View
         */
    }

    private boolean classExists(String className) {
        try {
            Class.forName(className);

            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

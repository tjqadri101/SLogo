package nodes.leafnodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import nodes.AbstractNode;
import turtle.Turtle;

public class FunctionNode extends AbstractNode{

    private List<VariableNode> myVariableNodes = new ArrayList<VariableNode>();
    private String myContent;
    private String myName;
    
    private Turtle myTurtle;
    
    private boolean myAlreadyDeclaredBoolean;
    
    public FunctionNode(Turtle turtle, String content) {
        super(turtle);
        myTurtle = turtle;
        myContent = content;
    }
    
    public FunctionNode (Turtle turtle, String name,String content) {
        this(turtle, content);
        myName = name;
    }


    public String getContent () {
        return myContent;
    }


    public void setContent (String string) {
        myContent = string;
    }
    
    /**
     * if a function node is the root of a tree, it can have one child (a block node);
     * if a function node is not the root of a tree, it cannot have any child
     */
    public boolean isAlreadyDeclared() {
        return myAlreadyDeclaredBoolean;
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

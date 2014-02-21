package nodes;

import java.util.ArrayList;
import java.util.List;
import turtle.Turtle;

public abstract class AbstractNode {
    
    private Turtle myTurtle;
    
    private double myValue;
    private String myToken;
    private AbstractNode myParent;
    private List<AbstractNode> myChildren = new ArrayList<AbstractNode>();
    private AbstractNode myLeftNode;
    private AbstractNode myRightNode;
    private AbstractNode mySibling;
    
    private AbstractNode myStartingNode;
    private AbstractNode myEndingNode;
    
    public AbstractNode(Turtle turtle, String token) {
        myToken = token;
        myTurtle = turtle;
    }
    
    public AbstractNode(Turtle turtle, String token, double value) {
        this(turtle, token);
        myValue = value;
    }
    
    public AbstractNode(Turtle turtle, String token, double value, AbstractNode startingNode, AbstractNode endingNode) {
        this(turtle, token, value);
        myStartingNode = startingNode;
        myEndingNode = endingNode;
    }
    
    public AbstractNode getLeftNode() {
        return myLeftNode;
    }
    
    public AbstractNode getRightNode() {
        return myRightNode;
    }
    
    public void setLeftNode(AbstractNode node) {
        myChildren.add(node);
        myLeftNode = node;
    }
    
    public void setRightNode(AbstractNode node) {
        myChildren.add(node);
        myRightNode = node;
    }
    
    public void setStartingNode(AbstractNode node) {
        myStartingNode = node;
    }
    
    public AbstractNode getStartingNode(AbstractNode node) {
        return myStartingNode;
    }
    
    public void setEndingNode(AbstractNode node) {
        myEndingNode = node;
    }
    
    public AbstractNode getEndingNode(AbstractNode node) {
        return myEndingNode;
    }
    
    public boolean hasChild() {
        return (myChildren.size()>0);
    }
    
    public abstract void action();
    
}

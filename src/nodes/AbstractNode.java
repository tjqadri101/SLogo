package nodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import turtle.Turtle;

public abstract class AbstractNode {
    
    private List<Turtle> myTurtles;
    
    private double myValue;
    private AbstractNode myParent;
    private List<AbstractNode> myChildren = new ArrayList<AbstractNode>();
    private AbstractNode myLeftNode;
    private AbstractNode myRightNode;
    
    public AbstractNode(List<Turtle> turtles) {
        myTurtles = turtles;
    }

    public void setTurtles(List<Turtle> activeTurtles) {
        myTurtles = activeTurtles;
    }
    
    public List<Turtle> getTurtles() {
        return myTurtles;
    }
    
//    public AbstractNode getSubtree() {
//        AbstractNode newNode = new BlockNode(myTurtles);
//        AbstractNode thisNode = this;
//        //TODO
//        
//        
//        return null;
//    }
//    
//    private AbstractNode recurseToCopy(AbstractNode newRoot, AbstractNode oldNode, AbstractNode newNode) {
//        if (oldNode==null) {
//            return newRoot;
//        }
//        
//        
//        return null;
//    }
    
    
    public String toString() {
        return this.getClass().getName();
    }
 
    public AbstractNode getLeftNode() {
        return myLeftNode;
    }
    
    public AbstractNode getRightNode() {
        return myRightNode;
    }
    
    public AbstractNode getParent() {
        return myParent;
    }
    
    public void setParent(AbstractNode node) {
        myParent = node;
    }
    
    public void setLeftNode(AbstractNode node) {
        myChildren.add(node);
        node.setParent(this);
        myLeftNode = node;
    }
    
    public void setRightNode(AbstractNode node) {
        myChildren.add(node);
        node.setParent(this);
        myRightNode = node;
    }
    
    public void addChild(AbstractNode node) {
        myChildren.add(node);
        node.setParent(this);
    }
    
    public boolean hasChild() {
        return (myChildren.size()>0);
    }

    /**
     * perform calculation changes the position of the turtle for all sub nodes and return the final value
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
    public abstract double evaluate() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException;


    public List<AbstractNode> getChildren () {
        return myChildren;
    }

    /**
     * Check if a node allows two child nodes
     * @return
     */
    public abstract boolean allowsTwoChildren ();
    
    /**
     * check if a node allows more than two child nodes (such as BlockNode)
     * @return
     */
    public abstract boolean allowsMoreThanTwoChildren() ;


    /**
     * ONLY IMPLEMENTED IN FUNCTIONODE
     * @return
     */
    public boolean isAlreadyDeclared () {
        return false;
    }

    /**
     * Override in VaraibleNode
     * @param evaluate
     */
    public void setCurrentValue (double evaluate) {
        // TODO Auto-generated method stub
        
    }

    public void setIsAlreadyDeclaredBoolean (boolean boo) {
        // TODO Auto-generated method stub
        
    }

    /**
     * for variable nodes and function nodes
     * @return
     */
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }
    
}

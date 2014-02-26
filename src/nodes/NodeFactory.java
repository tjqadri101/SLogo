package nodes;

import turtle.Turtle;

public class NodeFactory implements Token{
    
    private Turtle myTurtle; 
    
    public NodeFactory(Turtle turtle) {
        myTurtle = turtle;
    }
    
    public AbstractNode createNode() {
        
        
        return null;
    }
    
    public Token createToken(String text) {
        
        return null;
    }

    public AbstractNode createNode (String word) {
        // TODO Auto-generated method stub
        return null;
    }
}

package nodes;

import nodes.commandnodes.ForwardNode;
import turtle.Turtle;

public class NodeFactory implements Token{

    private Turtle myTurtle; 

    public NodeFactory(Turtle turtle) {
        myTurtle = turtle;
    }

    public Token createToken(String text) {

        return null;
    }

    public AbstractNode createNode (String word) {
        // TODO THE FOLLOWING CODE IS ONLY FOR TESTING PURPOSES; add real code later
        if (word.equals("fd") || word.equals("forward")) {
            return new ForwardNode(myTurtle);
        }
        if (isNumeric(word)) {
            return new NumberNode(myTurtle, Double.parseDouble(word));
        }

            return null;
    }

    private boolean isNumeric(String str)  {  
        try {  
            double d = Double.parseDouble(str);  
        }  
        catch(NumberFormatException nfe) {  
            return false;  
        }  
        return true;  
    }
}

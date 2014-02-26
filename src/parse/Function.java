package parse;

import turtle.Turtle;
import nodes.AbstractNode;
import nodes.Token;

public class Function implements Token {
    
    private String myContent;
    
    public Function(String string) {
        myContent = string;
    }
    
    public String getContent() {
        return myContent;
    }

    public AbstractNode doParse() {
        //calls createFunction in Parser
        //TODO
        
        return null;
    }

    
}

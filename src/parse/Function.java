package parse;

import turtle.Turtle;
import nodes.AbstractNode;
import nodes.Token;

public class Function implements Token {
    
    private String myFunctionName;
    private String myContent;
    
    public Function(String name, String content) {
        myContent = content;
        myFunctionName = name;
    }
    
    public String getContent() {
        return myContent;
    }
    
    public void setContent(String string) {
        myContent = string;
    }
    
    public String getFunctionName() {
        return myFunctionName;
    }
}

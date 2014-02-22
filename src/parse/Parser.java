package parse;

import java.util.Arrays;
import turtle.Turtle;
import nodes.AbstractNode;
import nodes.Token;

public class Parser implements Token {
    
    private Turtle myTurtle;
    private String myText;
    private boolean myValidBoolean = true;
    
    public Parser(Turtle turtle, String text) {
        myTurtle = turtle;
        myText = text;
    }
    
    public boolean isValid() {
        return myValidBoolean;
    }
    
    public AbstractNode createTree() {
        // break text into a list of strings according to spaces
        
        return null;
    }
    
    public void readTextAndAddNodes(String[] textArray, AbstractNode node) { // use recursion
        if (textArray.length == 0) {
            return;
        }
        String thisString = textArray[0];
        String maybeNumber = textArray[1];
        
        AbstractNode childNode = null;
        // TODO create token
        //
        //create child node
        
        node.setLeftNode(childNode);
        
        if (isNumeric(maybeNumber)) {
            //TODO
        } else {
            String[] nextArray = Arrays.copyOfRange(textArray, 1, textArray.length);
            readTextAndAddNodes(nextArray, childNode);
        }
        
        
        
        
    }
    
    public String[] breakText(String text) {
        return text.split(" ");
    }
    
    private static boolean isNumeric(String str)  {  
      try {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe) {  
        return false;  
      }  
      return true;  
    }
    
}

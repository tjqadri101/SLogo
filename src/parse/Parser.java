package parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import turtle.Turtle;
import nodes.AbstractNode;
import nodes.NumberNode;
import nodes.VariableNode;
import nodes.Token;

public class Parser implements Token {
    
    private Turtle myTurtle;
    private String myText;
    private boolean myValidBoolean = true;
    
    private List<VariableNode> myVariables = new ArrayList<VariableNode>();
    private List<FunctionNode> myFunctions = new ArrayList<FunctionNode>();
    
    public Parser(Turtle turtle, String text) {
        myTurtle = turtle;
        myText = text;
    }
    
    public boolean isValid() {
        return myValidBoolean;
    }
    
    public void createFunctionsAndVariables(String s) {
        String[] functionString = s.split("to");
        for (String thisFunction : functionString) {
            myFunctions.add(new FunctionNode(myTurtle, thisFunction));
        }
        
        String[] words = s.split(" ");
        for (int i=0;i<words.length;i++) {
            if (words[i].charAt(0) == ':') {
                //create a variable node
                VariableNode vn = new VariableNode(myTurtle, Double.parseDouble(words[i+1]));
            }
        }
        
    }
    
    
    public AbstractNode createTree(FunctionNode function) {
        
        
        return null;
    }
    
    public void traverseTree(AbstractNode root) {
        AbstractNode node = root;
        List<AbstractNode> visited = new ArrayList<AbstractNode>();
        
        if (node == null) {
            return;
        }
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        
        if (node.getChildren().size() > 2) {
            for (AbstractNode child : node.getChildren()) {
                traverseTree(child);
            }
        } else if (!visited.contains(node.getLeftNode())) {
            traverseTree(node.getLeftNode());
        } else if (!visited.contains(node.getRightNode())) {
            traverseTree(node.getRightNode());
        } else { //no children
            if (node instanceof NumberNode || node instanceof VariableNode) {
                return;
            } else {
                node.action();
                node.evaluate();
            }
        }
        
        
    }
    
}

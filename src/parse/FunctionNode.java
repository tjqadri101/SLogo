package parse;

import turtle.Turtle;
import nodes.AbstractNode;
import nodes.Token;

public class FunctionNode extends AbstractNode implements Token {
    
    private String myContent;
    
    public FunctionNode(Turtle turtle) {
        super(turtle);
    }
    
    public FunctionNode(Turtle turtle, String string) {
        super(turtle);
        myContent = string;
    }

    @Override
    public void action () {
        // call on child node (for function node it only has one child)
        this.getLeftNode().action();
    }

    @Override
    public double evaluate () {
        return this.getLeftNode().evaluate();
    }
    
    
    
}

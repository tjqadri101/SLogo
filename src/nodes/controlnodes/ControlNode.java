package nodes.controlnodes;

import nodes.AbstractNode;
import nodes.Expression;
import nodes.Token;
import turtle.Turtle;

public class ControlNode extends AbstractNode implements Token {

    public ControlNode (Turtle turtle, String token, Expression expression) {
        super(turtle, token);
    }
    
    public ControlNode (Turtle turtle, String token, Expression expression, 
                        AbstractNode startingNode, AbstractNode endingNode){
        super(turtle, token, expression, startingNode, endingNode);
        
    }

    @Override
    public void action () {
        //implemented in sub classes
    }

    @Override
    public double evaluate () {
        // do nothing
        return 1;
    }

}

package nodes.controlnodes;

import nodes.AbstractNode;
import nodes.Expression;
import nodes.Token;
import nodes.commandnodes.CommandNode;
import turtle.Turtle;

public class ForNode extends ControlNode implements Token {
    
    private AbstractNode myStartingNode;
    private AbstractNode myEndingNode;
    private Expression myExpression;
    
    public ForNode (Turtle turtle, String token, Expression expression) {
        super(turtle, token, expression);
        myExpression = expression;
    }

    public ForNode (Turtle turtle, String token, Expression expression, AbstractNode startingNode, AbstractNode endingNode) {
        super(turtle, token, expression, startingNode, endingNode);
        myStartingNode = startingNode;
        myEndingNode = endingNode;
        myExpression = expression;
    }
    
    @Override
    public void action () {
        AbstractNode node = this;
        double value = myExpression.calculateExpression();
        while (value > 0 && this.hasChild()) {
            node = this.getLeftNode();
            node.action();
            value --;
            if (node.equals(myEndingNode)) {
                break;
            }
            
            
            
        }
    }

}

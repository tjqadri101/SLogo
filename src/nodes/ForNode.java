package nodes;

import turtle.Turtle;

public class ForNode extends AbstractNode implements Token {
    private double myValue;
    private AbstractNode myStartingNode;
    private AbstractNode myEndingNode;
    
    public ForNode (Turtle turtle, String token, double value) {
        super(turtle, token, value);
        myValue = value;
    }

    @Override
    public void action () {
        AbstractNode node = this;
        while (myValue > 0 && this.hasChild()) {
            node = this.getLeftNode();
            node.action();
            myValue --;
            if (node.equals(myEndingNode)) {
                break;
            }
            
            
            
        }
    }

}

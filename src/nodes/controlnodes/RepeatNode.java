package nodes.controlnodes;

import turtle.Turtle;
import nodes.AbstractNode;

public class RepeatNode extends AbstractNode {

    private Turtle myTurtle; 
    
    public RepeatNode (Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }

    private double traverseSubtree() {
        // check left (condition node)
        AbstractNode conditionNode = this.getLeftNode();
        double count = conditionNode.evaluate();
        while (count > 0) {
            this.getRightNode().evaluate();
            this.getRightNode().action();
            count --;
        }
        return 0;
    }
    
    @Override
    public void action () {
        traverseSubtree();
    }

    @Override
    public double evaluate () {
        return traverseSubtree();
    }

    @Override
    public boolean allowsTwoChildren () {
        return true;
    }

    @Override
    public boolean allowsMoreThanTwoChildren () {
        return false;
    }

}

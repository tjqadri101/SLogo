package nodes.booleannodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class NotEqualNode extends AbstractNode {
	
	 private Turtle myTurtle; 
	    
	    public NotEqualNode (Turtle turtle) {
	        super(turtle);
	        myTurtle = turtle;
	    }

	    @Override
	    public void action () {
	        // do nothing
	    }


	    public double evaluate () {
	        AbstractNode leftNode = this.getLeftNode();
	        AbstractNode rightNode = this.getRightNode();
	        if (leftNode.evaluate() != rightNode.evaluate()) {
	            return 1;
	        }
	        return 0;
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

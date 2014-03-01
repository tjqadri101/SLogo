package nodes.mathnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class ProductNode extends AbstractNode {
	 private Turtle myTurtle; 
	    
	    public ProductNode (Turtle turtle) {
	        super(turtle);
	        myTurtle = turtle;
	    }

	    @Override

	    public double evaluate () {
	        AbstractNode leftNode = this.getLeftNode();
	        AbstractNode rightNode = this.getRightNode();
	        
	        return leftNode.evaluate() * rightNode.evaluate();

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

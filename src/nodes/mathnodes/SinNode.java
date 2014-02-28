package nodes.mathnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class SinNode extends AbstractNode {
	
	private Turtle myTurtle;

	public SinNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public void action() {
		// do nothing
	}

	@Override
	    public double evaluate () {
	    	
	    	AbstractNode child = this.getLeftNode();
	    	double degrees = child.evaluate();
	    	double result = Math.sin(degrees * Math.PI/180);
	    	
	    	return result;

	    }

	/**
	 * Both are false because this node only has one Child
	 */
	@Override
	public boolean allowsTwoChildren() {
		return false;
	}

	@Override
	public boolean allowsMoreThanTwoChildren() {
		return false;
	}


}

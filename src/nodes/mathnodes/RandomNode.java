package nodes.mathnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class RandomNode extends AbstractNode {

	private Turtle myTurtle;

	public RandomNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		
    	AbstractNode child = this.getLeftNode();
    	double max = child.evaluate();
		return Math.random() * max;

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

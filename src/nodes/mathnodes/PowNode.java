package nodes.mathnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class PowNode extends AbstractNode {

	private Turtle myTurtle;

	public PowNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		AbstractNode leftNode = this.getLeftNode();
		AbstractNode rightNode = this.getRightNode();
		
		double base = leftNode.evaluate();
		double exp = rightNode.evaluate();
		
		return Math.pow(base, exp);

	}

	@Override
	public boolean allowsTwoChildren() {
		return true;
	}

	@Override
	public boolean allowsMoreThanTwoChildren() {
		return false;
	}

}

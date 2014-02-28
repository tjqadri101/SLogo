package nodes.booleannodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class GreaterNode extends CompareNode {

	private Turtle myTurtle;

	public GreaterNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	/**
	 * Return 1 if left node is greater than right node; return 0 if left node is less than right node
	 */
	public double evaluate() {
		AbstractNode leftNode = this.getLeftNode();
		AbstractNode rightNode = this.getRightNode();
		if (leftNode.evaluate() > rightNode.evaluate()) {
			return 1;
		}
		return 0;
	}

}

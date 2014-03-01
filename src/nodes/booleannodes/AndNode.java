package nodes.booleannodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class AndNode extends CompareNode {

	private Turtle myTurtle;

	public AndNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		AbstractNode leftNode = this.getLeftNode();
		AbstractNode rightNode = this.getRightNode();
		if (leftNode.evaluate() != 0 && rightNode.evaluate() != 0) {
			return 1;
		}
		return 0;
	}
}

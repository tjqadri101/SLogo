package nodes.booleannodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class EqualNode extends CompareNode {

	private Turtle myTurtle;

	public EqualNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		AbstractNode leftNode = this.getLeftNode();
		AbstractNode rightNode = this.getRightNode();
		if (leftNode.evaluate() == rightNode.evaluate()) {
			return 1;
		}
		return 0;
	}

}

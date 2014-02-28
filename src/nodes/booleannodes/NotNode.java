package nodes.booleannodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class NotNode extends AbstractNode {

	private Turtle myTurtle;

	public NotNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public void action() {
		// do nothing
	}

	@Override
	public double evaluate() {

		AbstractNode child = this.getLeftNode();
		if (child.evaluate() == 0) {
			return 1;
		} else {
			return 0;
		}
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

package nodes.displaynodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class SetPenColorNode extends AbstractNode {

	private Turtle myTurtle;

	public SetPenColorNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		return 0;
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

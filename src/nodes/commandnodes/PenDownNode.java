package nodes.commandnodes;

import turtle.Turtle;
import nodes.leafnodes.LeafNode;

public class PenDownNode extends LeafNode {

	private Turtle myTurtle;

	public PenDownNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		myTurtle.setPenDown();
		return 1;
	}

}

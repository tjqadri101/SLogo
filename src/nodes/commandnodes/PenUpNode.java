package nodes.commandnodes;

import nodes.LeafNode;
import turtle.Turtle;

public class PenUpNode extends LeafNode {

	private Turtle myTurtle;

	public PenUpNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		myTurtle.setPenUp();
		return 1;
	}


}

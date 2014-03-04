package nodes.commandnodes;

import nodes.leafnodes.LeafNode;
import turtle.Turtle;

public class HideTurtleNode extends LeafNode {

	private Turtle myTurtle;

	public HideTurtleNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		myTurtle.setInvisible();
		return 0;
	}

}

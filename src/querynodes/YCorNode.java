package querynodes;

import nodes.leafnodes.LeafNode;
import turtle.Turtle;

public class YCorNode extends LeafNode {

	private Turtle myTurtle;

	public YCorNode(Turtle turtle) {
		super(turtle);

		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		return myTurtle.getYPos();
	}

}

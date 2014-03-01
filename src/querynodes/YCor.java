package querynodes;

import nodes.LeafNode;
import turtle.Turtle;

public class YCor extends LeafNode {

	private Turtle myTurtle;

	public YCor(Turtle turtle) {
		super(turtle);

		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		return myTurtle.getXPos();
	}

}

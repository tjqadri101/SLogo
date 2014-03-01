package nodes.commandnodes;

import nodes.LeafNode;
import turtle.Turtle;

public class ShowTurtleNode extends LeafNode {

	private Turtle myTurtle;

	public ShowTurtleNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		myTurtle.setVisible();
		return 1;
	}

}

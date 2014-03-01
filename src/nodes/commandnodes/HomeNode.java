package nodes.commandnodes;

import nodes.LeafNode;
import turtle.Turtle;

public class HomeNode extends LeafNode {

	private Turtle myTurtle;

	public HomeNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	public void setTurtle(Turtle turtle) {
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {

		double xOld = myTurtle.getXPos();
		double yOld = myTurtle.getYPos();

		myTurtle.setPosition(0, 0);

		return Math.sqrt(Math.pow((0 - xOld), 2) + Math.pow((0 - yOld), 2));
	}

}

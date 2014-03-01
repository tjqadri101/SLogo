package nodes.commandnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class SetXYNode extends AbstractNode {

	private Turtle myTurtle;

	public SetXYNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	public void setTurtle(Turtle turtle) {
		myTurtle = turtle;
	}

	/**
	 * Benson: action() isn't complete. Should we use getter methods from the
	 * turtle class?
	 */
	// TODO
	@Override
	public double evaluate() {

		double xNew = this.getLeftNode().evaluate();
		double yNew = this.getRightNode().evaluate();

		double xOld = myTurtle.getXPos();
		double yOld = myTurtle.getYPos();

		myTurtle.setPosition(xNew, yNew);

		return Math.sqrt(Math.pow((xNew - xOld), 2)
				+ Math.pow((yNew - yOld), 2));
	}

	@Override
	public boolean allowsTwoChildren() {
		return true;
	}

	@Override
	public boolean allowsMoreThanTwoChildren() {
		return false;
	}

}

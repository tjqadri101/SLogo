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
	//TODO
	public void action() {
		double x = this.getLeftNode().evaluate();
		double y = this.getRightNode().evaluate();

	}

	@Override
	public double evaluate() {
		return 0;
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

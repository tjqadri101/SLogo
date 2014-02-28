package nodes.commandnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class LeftNode extends AbstractNode {
	
	private Turtle myTurtle;

	public LeftNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	public void setTurtle(Turtle turtle) {
		myTurtle = turtle;
	}

	//TODO: test
	public void action() {
		double deltaAngle = this.getLeftNode().evaluate();
		myTurtle.updateAngle(deltaAngle);
	}

	@Override
	public double evaluate() {
		return 0;
	}

	@Override
	public boolean allowsTwoChildren() {
		return false;
	}

	@Override
	public boolean allowsMoreThanTwoChildren() {
		return false;
	}

}

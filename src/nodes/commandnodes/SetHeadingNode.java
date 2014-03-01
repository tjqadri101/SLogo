package nodes.commandnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class SetHeadingNode extends AbstractNode {
	
	private Turtle myTurtle;

	public SetHeadingNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	public void setTurtle(Turtle turtle) {
		myTurtle = turtle;
	}

	// TODO
	@Override
	public double evaluate() {
		AbstractNode child = this.getLeftNode();
		
		double degreesNew = child.evaluate();
		double degreesOld = myTurtle.getAngle();
		
		myTurtle.setHeading(degreesNew);
		
		return Math.abs(degreesNew - degreesOld);
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

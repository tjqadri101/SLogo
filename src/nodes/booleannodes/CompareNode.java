package nodes.booleannodes;

import turtle.Turtle;
import nodes.AbstractNode;

public class CompareNode extends AbstractNode {

	private Turtle myTurtle;
	
	public CompareNode(Turtle turtle) {
		super(turtle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		// do nothing
	}
	
	@Override
	public boolean allowsTwoChildren() {
		return true;
	}

	@Override
	public boolean allowsMoreThanTwoChildren() {
		return false;
	}

	@Override
	public double evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}

}

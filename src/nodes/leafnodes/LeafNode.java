package nodes.leafnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class LeafNode extends AbstractNode {

	public LeafNode(Turtle turtle) {
		super(turtle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean allowsTwoChildren() {
		return false;
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

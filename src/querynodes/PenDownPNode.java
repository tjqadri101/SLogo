package querynodes;

import nodes.leafnodes.LeafNode;
import turtle.Turtle;

public class PenDownPNode extends LeafNode {

	private Turtle myTurtle;
	private final double PEN_DOWN = 1;
	private final double PEN_UP = 0;

	public PenDownPNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		
		if (myTurtle.getPen() == PEN_DOWN) {
			return PEN_DOWN;
		}
		else{
			return PEN_UP;
		}
	}

}

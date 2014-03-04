package nodes.displaynodes;

import nodes.leafnodes.LeafNode;
import turtle.Turtle;

public class PenColorNode extends LeafNode {
	
	private Turtle myTurtle;

	public PenColorNode(Turtle turtle) {
		super(turtle);
		
        myTurtle = turtle;
	}
	
	@Override
	public double evaluate() {
		return myTurtle.getPenColorIndex();
	}

}

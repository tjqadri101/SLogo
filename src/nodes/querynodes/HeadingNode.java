package nodes.querynodes;

import nodes.leafnodes.LeafNode;
import turtle.Turtle;

public class HeadingNode extends LeafNode {
	
	private Turtle myTurtle;

	public HeadingNode(Turtle turtle) {
		super(turtle);
		
        myTurtle = turtle;
	}
	
	@Override
	public double evaluate() {
		return myTurtle.getAngle();
	}

}

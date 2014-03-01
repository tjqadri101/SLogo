package querynodes;

import turtle.Turtle;
import nodes.LeafNode;

public class XCor extends LeafNode {
	
	private Turtle myTurtle;

	public XCor(Turtle turtle) {
		super(turtle);
		
        myTurtle = turtle;
	}
	
	@Override
	public double evaluate() {
		return myTurtle.getXPos();
	}

}

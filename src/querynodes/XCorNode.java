package querynodes;

import turtle.Turtle;
import nodes.leafnodes.LeafNode;

public class XCorNode extends LeafNode {
	
	private Turtle myTurtle;

	public XCorNode(Turtle turtle) {
		super(turtle);
		
        myTurtle = turtle;
	}
	
	@Override
	public double evaluate() {
		return myTurtle.getXPos();
	}

}

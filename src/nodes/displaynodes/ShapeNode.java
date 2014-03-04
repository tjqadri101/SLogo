package nodes.displaynodes;

import nodes.LeafNode;
import turtle.Turtle;

public class ShapeNode extends LeafNode {

	private Turtle myTurtle;

	public ShapeNode(Turtle turtle) {
		super(turtle);
		
        myTurtle = turtle;
	}
	
	@Override
	public double evaluate() {
		return myTurtle.getMyShapeIndex();
	}

}

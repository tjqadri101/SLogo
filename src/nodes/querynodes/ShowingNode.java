package nodes.querynodes;

import nodes.LeafNode;
import turtle.Turtle;

public class ShowingNode extends LeafNode {
	
	private Turtle myTurtle;
	private final double VISIBLE = 1;
	private final double INVISIBLE = 0;

	public ShowingNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {
		
		if (myTurtle.getVisibility() == VISIBLE) {
			return VISIBLE;
		}
		else{
			return INVISIBLE;
		}
	}



}

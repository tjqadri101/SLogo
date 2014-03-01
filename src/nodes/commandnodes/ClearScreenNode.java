package nodes.commandnodes;

import turtle.Turtle;
import nodes.LeafNode;

public class ClearScreenNode extends LeafNode {

	private Turtle myTurtle;

	public ClearScreenNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() {

		double xOld = myTurtle.getXPos();
		double yOld = myTurtle.getYPos();
		
		/**
		 * Benson to Tara: Not sure screen clearing will work.
		 */

		myTurtle.clearScreen();
		myTurtle.setPosition(0, 0);
		myTurtle.resetClear();
		return Math.sqrt(Math.pow((0 - xOld), 2) + Math.pow((0 - yOld), 2));

	}

}

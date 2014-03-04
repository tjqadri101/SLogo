package nodes.displaynodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import nodes.AbstractNode;
import turtle.Turtle;

public class SetShapeNode extends AbstractNode {

	private Turtle myTurtle;

	public SetShapeNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
		
		AbstractNode child = this.getLeftNode();
		double shapeIndex = child.evaluate();
		
		myTurtle.setTurtleShape(shapeIndex);
		return shapeIndex;
	}

	/**
	 * Both are false because this node only has one Child
	 */
	@Override
	public boolean allowsTwoChildren() {
		return false;
	}

	@Override
	public boolean allowsMoreThanTwoChildren() {
		return false;
	}

}

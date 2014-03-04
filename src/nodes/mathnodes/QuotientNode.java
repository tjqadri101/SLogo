package nodes.mathnodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import nodes.AbstractNode;
import turtle.Turtle;

public class QuotientNode extends AbstractNode {
	private Turtle myTurtle;

	public QuotientNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public double evaluate() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
		AbstractNode leftNode = this.getLeftNode();
		AbstractNode rightNode = this.getRightNode();

		return leftNode.evaluate() / rightNode.evaluate();

	}

	@Override
	public boolean allowsTwoChildren() {
		return true;
	}

	@Override
	public boolean allowsMoreThanTwoChildren() {
		return false;
	}

}

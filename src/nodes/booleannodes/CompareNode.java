package nodes.booleannodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import turtle.Turtle;
import nodes.AbstractNode;

public class CompareNode extends AbstractNode {

	private Turtle myTurtle;
	
	public CompareNode(Turtle turtle) {
		super(turtle);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean allowsTwoChildren() {
		return true;
	}

	@Override
	public boolean allowsMoreThanTwoChildren() {
		return false;
	}

	@Override
	public double evaluate() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
	        //evaluated in sub classes
		return 0;
	}

}

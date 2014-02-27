package nodes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import nodes.booleannodes.GreaterNode;
import nodes.commandnodes.ForwardNode;
import nodes.controlnodes.IfNode;
import nodes.controlnodes.RepeatNode;
import turtle.Turtle;

public class NodeFactory implements Token {

	private Turtle myTurtle;

	public NodeFactory(Turtle turtle) {
		myTurtle = turtle;
	}

	public Token createToken(String text) {

		return null;
	}

	public AbstractNode createNode(String word) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		// TODO THE FOLLOWING CODE IS ONLY FOR TESTING PURPOSES; add real code
		// later
	    if (word.equals("fd") || word.equals("forward")) {
	        return new ForwardNode(myTurtle);
	    }
	    if (isNumeric(word)) {
	        return new NumberNode(myTurtle, Double.parseDouble(word));
	    }
	    if (word.equals("repeat")) {
	        return new RepeatNode(myTurtle);
	    }
	    if (word.equals("if")) {
	        return new IfNode(myTurtle);
	    }
	    if (word.equals("greaterp")) {
	        return new GreaterNode(myTurtle);
	    }
	    return null;
		
//		AbstractNode genericNode = null;;
//
//		if (!isNumeric(word)) {
//			Class<?> c = Class.forName(word + "Node");
//			Constructor<?> constructor = c.getConstructor(Turtle.class);
//			genericNode = (AbstractNode) constructor
//					.newInstance(myTurtle);
//		}
//		
//		return genericNode;

	}

	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}

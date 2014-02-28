package nodes.mathnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class LogNode extends AbstractNode {
	private Turtle myTurtle;

	public LogNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	@Override
	public void action() {
		// do nothing
	}

	@Override
	    /**
	     * Benson to Tara: I'm temporarily this.getChildren to reference the single child.
	     * I'm not sure if you wanted to create a this.getChild method, so I just stuck with this
	     */
	    public double evaluate () {
	    	
	    	AbstractNode child = this.getChildren().get(0);
	    	double expr = child.evaluate();
	    	double result = Math.log(expr);
	    	
	    	return result;

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

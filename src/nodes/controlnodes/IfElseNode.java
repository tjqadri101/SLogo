package nodes.controlnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class IfElseNode extends AbstractNode {

	private Turtle myTurtle;

	public IfElseNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	private double traverseSubtree() {
		// go to left block: if satisfies condition, go to its block; if not, go
		// to right block
		AbstractNode leftBlock = this.getLeftNode();
		AbstractNode rightBlock = this.getRightNode();

		AbstractNode conditionNode = this.getLeftNode();
		double count = conditionNode.evaluate();
		if (count == 1) { // condition is true
			this.getLeftNode().getRightNode().evaluate();
			this.getLeftNode().getRightNode().action();
		} else {
			this.getRightNode().evaluate();
			this.getRightNode().action();
		}

		return 0;
	}

	@Override
	public void action() {
		traverseSubtree();
	}

	@Override
	public double evaluate() {
		return traverseSubtree();
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

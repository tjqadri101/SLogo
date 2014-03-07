package nodes.controlnodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import turtle.Turtle;
import nodes.AbstractNode;
import nodes.VariableNode;

public class DoTimesNode extends AbstractNode {

    public DoTimesNode (List<Turtle> turtles) {
        super(turtles);
    }

    @Override
    public double evaluate () throws ClassNotFoundException, NoSuchMethodException,
                             SecurityException, InstantiationException, IllegalAccessException,
                             IllegalArgumentException, InvocationTargetException,
                             NoSuchFieldException, IOException {
        return traverseSubtree();
    }
    
    private double traverseSubtree() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
        // check left (condition node)
        AbstractNode conditionNode = this.getLeftNode();
        double maxVal = conditionNode.evaluate();
        for (int i=0;i<maxVal;i++) {
            conditionNode.getLeftNode().setCurrentValue(i);
            traverseAndReplace(this.getRightNode(), conditionNode);
            return this.getRightNode().evaluate();
        }
        return 0;
    }
    
    private void traverseAndReplace(AbstractNode node, AbstractNode conditionNode) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException{
        if (node==null) return;
        if (node instanceof VariableNode) {
            node.setCurrentValue(conditionNode.evaluate());
        }
        traverseAndReplace(node.getLeftNode(), conditionNode);
        traverseAndReplace(node.getRightNode(),conditionNode);
    }

    @Override
    public boolean allowsTwoChildren () {
        return true;
    }

    @Override
    public boolean allowsMoreThanTwoChildren () {
        return false;
    }

}

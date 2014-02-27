package parse;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import turtle.Turtle;
import nodes.AbstractNode;
import nodes.BlockNode;
import nodes.NodeFactory;
import nodes.NumberNode;
import nodes.VariableNode;
import nodes.Token;
import nodes.controlnodes.ConditionNode;
import nodes.controlnodes.IfElseNode;
import nodes.controlnodes.IfNode;
import nodes.controlnodes.RepeatNode;

public class Parser implements Token {

    private Turtle myTurtle;
    private boolean myValidBoolean = true;

    private List<VariableNode> myVariables = new ArrayList<VariableNode>();
    private List<Function> myFunctions = new ArrayList<Function>();

    public Parser(Turtle turtle) {
        myTurtle = turtle;
    }

    public boolean isValid() {
        return myValidBoolean;
    }

    public void createFunctionsAndVariables(String s) {
        String[] words = s.split(" ");
        // if does not have the word "to" (only has one function)
        boolean hasTo = false;
        for (String word : words){
            if (word.equals("to")) {
                hasTo = true;
            }
        }
        if (hasTo) {
            String[] functionString = s.split("to");
            for (String thisFunction : functionString) {
                myFunctions.add(new Function(thisFunction));
            }
        }

        for (int i=0;i<words.length;i++) {
            if (words[i].charAt(0) == ':') {
                //create a variable node
                VariableNode vn = new VariableNode(myTurtle, Double.parseDouble(words[i+1]));
            }
        }

    }


    public AbstractNode createTree(Function function) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        NodeFactory nodeFactory = new NodeFactory(myTurtle);
        AbstractNode root = new BlockNode(myTurtle);

        String[] words = function.getContent().split(" ");
        Queue<String> queue = new LinkedList<String>();
        for (String word : words) {
            queue.add(word);
        }
        String currentWord = queue.poll();
        AbstractNode currentNode = nodeFactory.createNode(currentWord);
        root.setLeftNode(currentNode);

        while (!queue.isEmpty()) {
            if (currentNode instanceof NumberNode ||
                    currentNode instanceof VariableNode) {
                // return to parent
                currentNode = currentNode.getParent().getParent();
                // go to the block node
                if (! (currentNode instanceof BlockNode)) {
                    currentNode = currentNode.getRightNode();
                }
            }

            if (currentNode instanceof IfElseNode) {
                // create two block nodes
                AbstractNode bnLeft = new BlockNode(myTurtle);
                AbstractNode bnRight = new BlockNode(myTurtle);
                currentNode.setLeftNode(bnLeft);
                currentNode.setRightNode(bnRight);
                // create a condition node and a block node for each of the blocks
                AbstractNode conditionLeft = new ConditionNode(myTurtle);
                AbstractNode conditionRight = new ConditionNode(myTurtle);
                bnLeft.setLeftNode(conditionLeft);
                bnLeft.setRightNode(new BlockNode(myTurtle));
                bnRight.setLeftNode(conditionRight);
                bnRight.setRightNode(new BlockNode(myTurtle));

                // create condition for condition left; condition right is the opposite of condition left
                currentNode = bnLeft.getLeftNode();
            }
            if (currentNode instanceof IfNode || currentNode instanceof RepeatNode) {
                // create 1 block node
                AbstractNode bn = new BlockNode(myTurtle);
                // create 1 condition node
                AbstractNode conditionNode = new ConditionNode(myTurtle);
                currentNode.setLeftNode(conditionNode);
                currentNode.setRightNode(bn);
                currentNode = conditionNode;
            }

            String nextWord = queue.poll();
            if (nextWord.equals("[")) {
                // if the parent node is a repeat node or an if node, or the parent of the parent node is an if else node
                if (currentNode.getParent() instanceof RepeatNode || currentNode.getParent() instanceof IfNode) {
                    currentNode = currentNode.getParent().getRightNode(); // go to block
                } else if (currentNode.getParent().getParent() instanceof IfElseNode) {
                    //TODO
                }
                nextWord = queue.poll();
            }
            if (nextWord.equals("]")) {
                currentNode = currentNode.getParent();
                nextWord = queue.poll();
            }
            if (nextWord == null) {
                return root;
            }

            AbstractNode nextNode = nodeFactory.createNode(nextWord);


            if (currentNode.getLeftNode()== null) {
                currentNode.setLeftNode(nextNode);
            } else if (currentNode.getRightNode() == null && currentNode.allowsTwo()) {
                currentNode.setRightNode(nextNode);
            } else { //is a block node; had more than 2 children
                currentNode.addChild(nextNode);
            }

            currentNode = nextNode;
            currentWord = nextWord;

        }
        return root;
    }

    public void traverseTree(AbstractNode node, List<AbstractNode> visited) {
        
        System.out.println("**Parser traverseTree: visited = " + visited + ", current node is " + node);
        
        if (node == null) {
            return;
        }
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);

        if (node.getChildren().size() > 2) {
            for (AbstractNode child : node.getChildren()) {
                traverseTree(child, visited);
            }
        } else if (!visited.contains(node.getLeftNode())) {
            traverseTree(node.getLeftNode(), visited);
        } else if (!visited.contains(node.getRightNode())) {
            traverseTree(node.getRightNode(), visited);
        } else { //no children

            node.action();
            node.evaluate();
        }


    }

}

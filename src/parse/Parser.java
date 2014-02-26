package parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import turtle.Turtle;
import nodes.AbstractNode;
import nodes.BlockNode;
import nodes.ConditionNode;
import nodes.IfElseNode;
import nodes.LeftBracketNode;
import nodes.NodeFactory;
import nodes.NumberNode;
import nodes.RightBracketNode;
import nodes.VariableNode;
import nodes.Token;
import nodes.controlnodes.IfNode;
import nodes.controlnodes.RepeatNode;

public class Parser implements Token {
    
    private Turtle myTurtle;
    private String myText;
    private boolean myValidBoolean = true;
    
    private List<VariableNode> myVariables = new ArrayList<VariableNode>();
    private List<Function> myFunctions = new ArrayList<Function>();
    
    public Parser(Turtle turtle, String text) {
        myTurtle = turtle;
        myText = text;
    }
    
    public boolean isValid() {
        return myValidBoolean;
    }
    
    public void createFunctionsAndVariables(String s) {
        String[] functionString = s.split("to");
        for (String thisFunction : functionString) {
            myFunctions.add(new Function(thisFunction));
        }
        
        String[] words = s.split(" ");
        for (int i=0;i<words.length;i++) {
            if (words[i].charAt(0) == ':') {
                //create a variable node
                VariableNode vn = new VariableNode(myTurtle, Double.parseDouble(words[i+1]));
            }
        }
        
    }
    
    
    public AbstractNode createTree(Function function) {
        NodeFactory nodeFactory = new NodeFactory(myTurtle);
        
        String[] words = function.getContent().split(" ");
        Queue<String> queue = new LinkedList<String>();
        for (String word : words) {
            queue.add(word);
        }
        String currentWord = queue.poll();
        AbstractNode root = nodeFactory.createNode(currentWord);
        AbstractNode currentNode = root;
        while (queue.size() > 0) {
            if (currentNode instanceof NumberNode ||
                    currentNode instanceof VariableNode) {
                // return to parent
                currentNode = currentNode.getParent();
            }
            if (currentNode.getChildren().size() >= 2 || !(currentNode instanceof BlockNode
                    || currentNode instanceof RightBracketNode)) {
                // only block nodes are allowed to have more than 2 children
                currentNode = currentNode.getParent();
            }
            
            if (currentNode instanceof LeftBracketNode) {
                //TODO
                // if the parent node is a repeat node or an if node, or the parent of the parent node is an if else node
                if (currentNode.getParent() instanceof RepeatNode || currentNode.getParent() instanceof IfNode) {
                    currentNode = currentNode.getParent().getRightNode(); // go to block
                } else if (currentNode.getParent().getParent() instanceof IfElseNode) {
                    //TODO
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
            AbstractNode nextNode = nodeFactory.createNode(currentWord);
            
            if (currentNode.getLeftNode()!= null) {
                currentNode.setLeftNode(nextNode);
            } else if (currentNode.getRightNode() != null) {
                currentNode.setRightNode(nextNode);
            } else { //is a block node; had more than 2 children
                currentNode.addChild(nextNode);
            }
            
            currentNode = nextNode;
            
            
        }
        
        
        
        return root;
    }
    
    public void traverseTree(AbstractNode root) {
        AbstractNode node = root;
        List<AbstractNode> visited = new ArrayList<AbstractNode>();
        
        if (node == null) {
            return;
        }
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        
        if (node.getChildren().size() > 2) {
            for (AbstractNode child : node.getChildren()) {
                traverseTree(child);
            }
        } else if (!visited.contains(node.getLeftNode())) {
            traverseTree(node.getLeftNode());
        } else if (!visited.contains(node.getRightNode())) {
            traverseTree(node.getRightNode());
        } else { //no children
            if (node instanceof NumberNode || node instanceof VariableNode) {
                return;
            } else {
                node.action();
                node.evaluate();
            }
        }
        
        
    }
    
}

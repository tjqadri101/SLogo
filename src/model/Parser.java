package model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import turtle.ITurtle;
import turtle.Turtle;
import nodes.AbstractNode;
import nodes.BlockNode;
import nodes.MakeNode;
import nodes.NodeFactory;
import nodes.controlnodes.ConditionNode;
import nodes.controlnodes.DoTimesNode;
import nodes.controlnodes.ForNode;
import nodes.controlnodes.IfElseNode;
import nodes.controlnodes.IfNode;
import nodes.controlnodes.RepeatNode;
import nodes.controlnodes.ToNode;
import nodes.leafnodes.FunctionNode;
import nodes.leafnodes.LeafNode;
import nodes.leafnodes.NumberNode;
import nodes.leafnodes.VariableNode;

public class Parser {
    private List<Turtle> myTurtles;
    private List<Turtle> myInactiveTurtles;
    private boolean myValidBoolean = true;
    private String myCommands;
    private String myLanguage;
    private List<VariableNode> myVariableNodes = new ArrayList<VariableNode>();
    private List<FunctionNode> myFunctionNodes = new ArrayList<FunctionNode>();

    public Parser (List<Turtle> turtles, String commands, String language) {
        myTurtles = turtles;
        myCommands = commands;
        myLanguage = language;
                
    }
    
    public void inactivateTurtle(Turtle turtle) {
        for (int i=0;i<myTurtles.size();i++) {
            if (myTurtles.get(i).equals(turtle)) {
                myTurtles.remove(i);
                myInactiveTurtles.add(turtle);
            }
        }
    }
    
    public void activeTurtle(Turtle turtle) {
        for (int i=0;i<myInactiveTurtles.size();i++) {
            if (myInactiveTurtles.get(i).equals(turtle)) {
                myInactiveTurtles.remove(i);
                myTurtles.add(turtle);
            }
        }
    }
    
    public List<FunctionNode> getFunctionNodes () {
        return myFunctionNodes;
    }

    public List<VariableNode> getVariables() {
        return myVariableNodes; // TODO put in model: myFunc, myVar, myTurtle, myPencolor, ...
    }

    public boolean isValid() {
        return myValidBoolean;
    }
    /** 
     * call this method in Model; creates abstract syntax tree and traverse the tree
     * @return value to display in the view
     */
    public double doParse() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
        AbstractNode node = createTree();
        return traverseTree(node);
    }

    public AbstractNode createTree() throws ClassNotFoundException, 
    NoSuchMethodException, SecurityException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, NoSuchFieldException {
        NodeFactory nodeFactory = new NodeFactory(myTurtles, myLanguage);
        AbstractNode root = new BlockNode(myTurtles);
        String[] words = myCommands.split(" ");
        Queue<String> queue = new LinkedList<String>();
        Stack<String> bracketStack = new Stack<String>(); 
        for (String word : words) {
            queue.add(word);
        }
        String currentWord = queue.poll();
        AbstractNode currentNode = nodeFactory.createNode(currentWord);
        root.setLeftNode(currentNode);

        while (!queue.isEmpty()) {
            
            if (currentNode instanceof LeafNode) {
                // return to parent
                currentNode = currentNode.getParent();
                if (!currentNode.allowsTwoChildren() || 
                        (currentNode.allowsTwoChildren() && currentNode.getChildren().size()==2)) {
                    currentNode = currentNode.getParent();
                }
            }

            if (currentNode instanceof IfElseNode) {
                // create two block nodes
                AbstractNode bnLeft = new BlockNode(myTurtles);
                AbstractNode bnRight = new BlockNode(myTurtles);
                currentNode.setLeftNode(bnLeft);
                currentNode.setRightNode(bnRight);
                // create a condition node for left block
                AbstractNode conditionLeft = new ConditionNode(myTurtles);
                bnLeft.setLeftNode(conditionLeft);
                bnLeft.setRightNode(new BlockNode(myTurtles));

                // create condition for condition left; condition right is the opposite of condition left
                currentNode = bnLeft.getLeftNode();
            }
            if ((currentNode instanceof IfNode || currentNode instanceof RepeatNode) && currentNode.getLeftNode() == null) {
                // create 1 block node
                AbstractNode bn = new BlockNode(myTurtles);
                // create 1 condition node
                AbstractNode conditionNode = new ConditionNode(myTurtles);
                currentNode.setLeftNode(conditionNode);
                currentNode.setRightNode(bn);
                currentNode = conditionNode;
            }

            String nextWord = queue.poll();
            if (nextWord == null) {
                return root;
            }
            for (int i=0;i<2;i++) {
                if (nextWord.equals("[")) { //TODO counter for brackets
                    // if the parent node is a repeat node or an if node, or the parent of the parent node is an if else node
                    if (currentNode.getParent() instanceof RepeatNode || currentNode.getParent() instanceof IfNode ||
                            currentNode.getParent().getParent() instanceof IfElseNode) {
                        currentNode = currentNode.getParent().getRightNode(); // go to block
                    } else if (currentNode instanceof RepeatNode || currentNode instanceof IfNode) {
                        currentNode = currentNode.getRightNode(); // go to block
                    }
                    nextWord = queue.poll();
                }

                if (nextWord.equals("]")) {    
                    if (currentNode == null) {
                        return root;
                    }
                    currentNode = currentNode.getParent();
                    if (currentNode.getParent() instanceof IfElseNode) {
                        currentNode = currentNode.getParent();
                    }
                    nextWord = queue.poll();
                    if (nextWord == null) {
                        return root;
                    }
                }
            }
            AbstractNode nextNode = nodeFactory.createNode(nextWord);

            if (currentNode.getLeftNode()== null) {
                currentNode.setLeftNode(nextNode);
            } else if (currentNode.getRightNode() == null && currentNode.allowsTwoChildren()) {
                currentNode.setRightNode(nextNode);
            } else if (currentNode.getRightNode() != null && currentNode.allowsMoreThanTwoChildren()){
                currentNode.addChild(nextNode);
            }

            currentNode = nextNode;
            currentWord = nextWord;

        
            
            
//            if (currentNode == null) {
//                return root;
//            }
//            if (currentWord.equals("[")) {
//                bracketStack.push(currentWord);
//                if (currentNode instanceof RepeatNode || currentNode instanceof IfNode 
//                            || currentNode instanceof DoTimesNode || currentNode instanceof ForNode) {
//                    AbstractNode leftNode = new ConditionNode(myTurtles);
//                    AbstractNode rightNode = new BlockNode(myTurtles);
//                    currentNode.setLeftNode(leftNode);
//                    currentNode.setRightNode(rightNode);
//                    currentNode = currentNode.getLeftNode();
//                }
//                if (currentNode instanceof FunctionNode) {
//                    AbstractNode blockNode = new BlockNode(myTurtles);
//                    currentNode.setLeftNode(blockNode);
//                    currentNode = currentNode.getLeftNode();
//                }
//                if (currentNode instanceof IfElseNode) {
//                    // create two block nodes
//                    AbstractNode bnLeft = new BlockNode(myTurtles);
//                    AbstractNode bnRight = new BlockNode(myTurtles);
//                    currentNode.setLeftNode(bnLeft);
//                    currentNode.setRightNode(bnRight);
//                    // create a condition node for left block
//                    AbstractNode conditionLeft = new ConditionNode(myTurtles);
//                    bnLeft.setLeftNode(conditionLeft);
//                    bnLeft.setRightNode(new BlockNode(myTurtles));
//
//                    currentNode = bnLeft.getLeftNode();
//                }
//                if (currentNode instanceof ToNode) {
//                    // create two block nodes
//                    AbstractNode bnLeft = new BlockNode(myTurtles);
//                    AbstractNode bnRight = new BlockNode(myTurtles);
//                    currentNode.setLeftNode(bnLeft);
//                    currentNode.setRightNode(bnRight);
//                    currentNode = bnLeft.getLeftNode();
//                }
//                currentWord = queue.poll();
//            }
//            if (currentWord.equals("]") && !bracketStack.isEmpty()) {
//                bracketStack.push(currentWord);
//                // go up until it finds an available right node
//                while (!isAvailable(currentNode)) {
//                    // go to its block node if there is one
//                    currentNode = currentNode.getParent();
//                }
//                currentNode = currentNode.getRightNode();
//                currentWord = queue.poll();
//            }
//            if (currentWord == null && bracketStack.isEmpty()) {
//                return root;
//            }
//            if (currentWord == null && !bracketStack.isEmpty()) {
//                //ERROR: bracket mismatch
//                return null;
//            }
//            // poll next word and make next node
//            String nextWord = queue.poll();
//            AbstractNode nextNode = nodeFactory.createNode(nextWord);
//            if (currentNode.getLeftNode()== null) {
//                currentNode.setLeftNode(nextNode);
//            } else if (currentNode.getRightNode() == null && currentNode.allowsTwoChildren()) {
//                currentNode.setRightNode(nextNode);
//            } else if (currentNode.getRightNode() != null && currentNode.allowsMoreThanTwoChildren()){
//                currentNode.addChild(nextNode);
//            }
//            currentNode = nextNode;
//            currentWord = nextWord;
        }
        return root;
    }

    private boolean isAvailable (AbstractNode currentNode) {
        return currentNode.getRightNode() instanceof BlockNode && currentNode.getRightNode().getLeftNode()==null; //TODO: TEST
    }

    public double traverseTree(AbstractNode root) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {  

        if (root!=null) {
            for (AbstractNode childNode : root.getChildren()) {
                traverseTree(childNode);
            }
            return root.evaluate();
        }
        return 1; //TODO
    }
}
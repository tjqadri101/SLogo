package model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import turtle.Turtle;
import nodes.AbstractNode;
import nodes.BlockNode;
import nodes.FunctionNode;
import nodes.MakeNode;
import nodes.NodeFactory;
import nodes.VariableNode;
import nodes.controlnodes.ConditionNode;
import nodes.controlnodes.DoTimesNode;
import nodes.controlnodes.ForNode;
import nodes.controlnodes.IfElseNode;
import nodes.controlnodes.IfNode;
import nodes.controlnodes.RepeatNode;
import nodes.leafnodes.LeafNode;

public class Parser {
    private List<Turtle> myTurtles;
    private List<Turtle> myInactiveTurtles;
    private boolean myValidBoolean = true;
    private String myCommands;
    private String myLanguage;
    private List<AbstractNode> myVariableNodes = new ArrayList<AbstractNode>();
    private List<AbstractNode> myFunctionNodes = new ArrayList<AbstractNode>();
    
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
            boolean skipLeafCheck = false;
            if (currentNode instanceof VariableNode) {
                if (findPreviouslyCreatedNodes(currentNode)!=null) {
                currentNode = findPreviouslyCreatedNodes(currentNode);
                } else {
                    skipLeafCheck=true;
                }
            } else if (currentNode instanceof FunctionNode) {
                if (findPreviouslyCreatedNodes(currentNode)!=null) {
                    currentNode = findPreviouslyCreatedNodes(currentNode);
                    } else {
                        skipLeafCheck=true;
                    }
            }
            if (!skipLeafCheck) {
                if (currentNode instanceof LeafNode || currentNode instanceof VariableNode || currentNode instanceof FunctionNode) {
                    // return to parent
                    currentNode = currentNode.getParent();
                    if (currentNode instanceof VariableNode && (currentNode.getChildren().size() == 3 
                            || (currentNode.getChildren().size()==1 && !nodeFactory.isNumeric(queue.peek())))) {
                        if (currentNode instanceof VariableNode) { 
                            currentNode.setCurrentValue(currentNode.getLeftNode().evaluate());
                            myVariableNodes.add(currentNode);
                        }
                        if (currentNode instanceof FunctionNode) myFunctionNodes.add(currentNode);
                        currentNode = currentNode.getParent();
                        if (currentNode instanceof MakeNode || currentNode instanceof ConditionNode) currentNode = currentNode.getParent();
                    } else if (!(currentNode instanceof VariableNode) && (!currentNode.allowsTwoChildren() || 
                            (currentNode.allowsTwoChildren() && currentNode.getChildren().size()==2))) {
                        currentNode = currentNode.getParent();
                    }
                }
            }
            if (currentNode instanceof IfElseNode) {
                AbstractNode bnLeft = new BlockNode(myTurtles);// create two block nodes
                AbstractNode bnRight = new BlockNode(myTurtles);
                currentNode.setLeftNode(bnLeft);
                currentNode.setRightNode(bnRight);
                AbstractNode conditionLeft = new ConditionNode(myTurtles); // create a condition node for left block
                bnLeft.setLeftNode(conditionLeft);
                bnLeft.setRightNode(new BlockNode(myTurtles));
                currentNode = bnLeft.getLeftNode();
            }
            if ((currentNode instanceof IfNode || currentNode instanceof RepeatNode 
                    || currentNode instanceof DoTimesNode) && currentNode.getLeftNode() == null) {
                AbstractNode bn = new BlockNode(myTurtles);// create 1 block node
                AbstractNode conditionNode = new ConditionNode(myTurtles);// create 1 condition node
                currentNode.setLeftNode(conditionNode);
                currentNode.setRightNode(bn);
                currentNode = conditionNode;
            }
            if (currentNode instanceof ForNode && currentNode.getLeftNode() == null) {
                AbstractNode bnLeft = new BlockNode(myTurtles);// create 1 block node
                AbstractNode bnRight = new BlockNode(myTurtles);
                currentNode.setLeftNode(bnLeft);
                currentNode.setRightNode(bnRight);
                currentNode = bnLeft;
            }
            if (currentNode instanceof FunctionNode) {
                String functionName = queue.poll();
                ((FunctionNode) currentNode).setName(functionName);
                boolean hasTwoChildren=false;
                String word="";
                while(queue.poll().equals("[")){
                    word = queue.peek(); //TODO TEST: before: poll()
                }
                if (word.charAt(0)==':') {
                    hasTwoChildren=true;
                }
                if (hasTwoChildren) {
                    AbstractNode bnLeft = new BlockNode(myTurtles);// create two block nodes
                    AbstractNode bnRight = new BlockNode(myTurtles);
                    currentNode.setLeftNode(bnLeft);
                    currentNode.setRightNode(bnRight);
                    currentNode = bnLeft;
                } else {// if it's a normal declare of function without variables --> only one child
                    AbstractNode bn = new BlockNode(myTurtles);
                    currentNode.setLeftNode(bn);
                    currentNode = bn;
                }
            }
            String nextWord = queue.poll();
            if (nextWord == null) {
                return root;
            }
            for (int i=0;i<2;i++) {
                if (nextWord.equals("[")) { 
                    // if the parent node is a repeat node or an if node, or the parent of the parent node is an if else node
                    if (currentNode instanceof RepeatNode || currentNode instanceof IfNode 
                            || currentNode instanceof DoTimesNode || currentNode instanceof ForNode) {
                        currentNode = currentNode.getRightNode(); // go to block
                    } else if (currentNode.getParent() instanceof RepeatNode || currentNode.getParent() instanceof IfNode) {
                        currentNode = currentNode.getParent().getRightNode(); // go to block
                    } else if (currentNode.getParent()!=null) {
                        if (currentNode.getParent().getParent() instanceof IfElseNode) {
                            currentNode = currentNode.getParent().getRightNode(); // go to block
                        }
                    }
                    nextWord = queue.poll();
                }
                if (nextWord.equals("]")) {    
                    if (currentNode == null) {
                        return root;
                    }
                    currentNode = currentNode.getParent();
                    if (currentNode == null) {
                        return root;
                    }
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
            } else if (currentNode.getRightNode() == null && (currentNode.allowsTwoChildren() || currentNode.allowsMoreThanTwoChildren())) {
                currentNode.setRightNode(nextNode);
            } else if (currentNode.getRightNode() != null && currentNode.allowsMoreThanTwoChildren()){
                currentNode.addChild(nextNode);
            }
            currentNode = nextNode;
            currentWord = nextWord;
        }
        return root;
    }

    private AbstractNode findPreviouslyCreatedNodes (AbstractNode currentNode) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
        for (AbstractNode thisNode : myVariableNodes) {
            if (thisNode.getName().equals(currentNode.getName())) {
                currentNode.setLeftNode(thisNode.getLeftNode()); 
                currentNode.setCurrentValue(thisNode.evaluate());
                return currentNode;
            }
        }
        return null;
    }

    public double traverseTree(AbstractNode root) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {  
        if (root!=null) {
            System.out.println("**Parser traverseTree: root "+root+"'s children are" + root.getChildren());
            for (AbstractNode childNode : root.getChildren()) {
                
                System.out.println("**Parser traverseTree: child node"+childNode+ " is being traversed");
                traverseTree(childNode);
                
            }
            System.out.println("**Parser traverseTree: evaluated "+ root+" = "+root.evaluate());
            return root.evaluate();
        }
        return 1; //TODO
    }
}
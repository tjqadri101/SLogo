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
import nodes.controlnodes.IfElseNode;
import nodes.controlnodes.IfNode;
import nodes.controlnodes.RepeatNode;
import nodes.leafnodes.FunctionNode;
import nodes.leafnodes.LeafNode;
import nodes.leafnodes.NumberNode;
import nodes.leafnodes.VariableNode;

public class Parser {

    /**
     * active turtles
     */
    private List<Turtle> myTurtles;
    
    /**
     * inactive turtles
     */
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
     * call this method in Model; 
     * creates abstract syntax tree and traverse the tree
     * @return value to display in the view
     */
    public double doParse() {
        
        
        
        
        
        
        
        
        
        
        
        return -1;
    }
    

//    public void createFunctionsAndVariables(String s) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
//        String[] words = s.split(" ");
//        // if does not have the word "to" (only has one function)
//        boolean hasTo = false;
//        for (String word : words){
//            if (word.equals("to")) {
//                hasTo = true;
//            }
//        }
//        if (hasTo) {
//            String[] functionString = s.split("to");
//
//            for (String thisFunction : functionString) {
//                if (!thisFunction.equals("")) {
//
//                    String[] wordsInFunction = thisFunction.split(" ");
//                    int functionNameIndex = 0;
//                    int functionContentStartIndex = 0;
//                    while (wordsInFunction[functionNameIndex].equals("") ||
//                            wordsInFunction[functionNameIndex].equals(" ")) {
//                        functionNameIndex ++;
//                        functionContentStartIndex += wordsInFunction[functionNameIndex].length() + 1;
//                    }
//                    String functionName = wordsInFunction[functionNameIndex];
//                    String content = thisFunction.substring(functionContentStartIndex);
//                    
//                    FunctionNode root = createTree(s, myTurtle); 
//                    root.setName(functionName);
//                    root.setContent(content);
//                    myFunctionNodes.add(root);
//                }
//            }
//            
//        } else { //hasTo = false
//            FunctionNode root = createTree(s, myTurtle);
//            root.setName("NoName");
//            root.setContent(s);
//            myFunctionNodes.add(root);
//        }
//
//        for (int i=0;i<words.length;i++) {
//            if (words[i].equals("make")) {
//                VariableNode vn = new VariableNode(myTurtle, words[i+1].substring(1), Double.parseDouble(words[i+2]));
//            }
//            if (words[i].equals("set")) {
//                // find variable node
//                for (VariableNode thisNode : myVariableNodes) {
//                    if (thisNode.getName().equals(words[i+1])) {
////                        thisNode.setValue() //TODO
//                    }
//                }
//            }
//        }
//
//    }

    public AbstractNode createTree() throws ClassNotFoundException, 
    NoSuchMethodException, SecurityException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, NoSuchFieldException {
        
        NodeFactory nodeFactory = new NodeFactory(myTurtles, myLanguage);
        
        FunctionNode root = new FunctionNode(myTurtles, myCommands);
        AbstractNode blockNode = new BlockNode(myTurtles);
        root.setLeftNode(blockNode);
        
//        processFunction(function); TODO brackets

        String[] words = myCommands.split(" ");
        Queue<String> queue = new LinkedList<String>();
        
        Stack<String> bracketStack = new Stack<String>(); //TODO
        
        for (String word : words) {
            queue.add(word);
        }
        String currentWord = queue.poll();
        
        AbstractNode currentNode = nodeFactory.createNode(currentWord);
        blockNode.setLeftNode(currentNode);

        while (!queue.isEmpty()) {
            
            if (currentNode instanceof MakeNode) {
                //TODO
                
            }
            

            if (currentNode instanceof LeafNode || 
                                    (currentNode instanceof FunctionNode && !currentNode.isAlreadyDeclared())) {
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

        }
        return root;

    }

//    private void processFunction (FunctionNode function) {
//        // getting rid of initial spaces and outside brackets
//        int beginIndex = 0;
//        while (function.getContent().charAt(beginIndex)==' ' || function.getContent().charAt(beginIndex)=='[') {
//            beginIndex ++;
//        }
//        int endIndex = function.getContent().length() - 1;
//        while(function.getContent().charAt(endIndex) == ' ' ||  function.getContent().charAt(endIndex)=='[') {
//            endIndex --;
//        }
//        function.setContent(function.getContent().substring(beginIndex, endIndex + 1));
//    }

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
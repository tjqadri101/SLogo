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
     * One parser for one turtle
     */
    private Turtle myTurtle;
    private boolean myValidBoolean = true;
    
    private boolean myActiveBoolean = true;
    
    private String myLanguage;

    private List<VariableNode> myVariableNodes = new ArrayList<VariableNode>();
    private List<FunctionNode> myFunctionNodes = new ArrayList<FunctionNode>();
    

    public Parser(Turtle turtle) {
        myTurtle = turtle;
        myLanguage = turtle.getLangauge();
        
//        ITurtle iTurtle = new Turtle();
        ITurtle newITurtle = (ITurtle) myTurtle;
    }
    
    public Parser(Turtle turtle, boolean onOffBoolean) {
        this(turtle);
        myActiveBoolean = onOffBoolean;
    }
    

    public void setOnOffBoolean (boolean onOrOff) {
        myActiveBoolean = onOrOff;
    }
    
    public boolean getOnOffBoolean() {
        return myActiveBoolean;
    }
    
    public void toggleOnOffBoolean() {
        myActiveBoolean = !myActiveBoolean;
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

    public void createFunctionsAndVariables(String s) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
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
                if (!thisFunction.equals("")) {

                    String[] wordsInFunction = thisFunction.split(" ");
                    int functionNameIndex = 0;
                    int functionContentStartIndex = 0;
                    while (wordsInFunction[functionNameIndex].equals("") ||
                            wordsInFunction[functionNameIndex].equals(" ")) {
                        functionNameIndex ++;
                        functionContentStartIndex += wordsInFunction[functionNameIndex].length() + 1;
                    }
                    String functionName = wordsInFunction[functionNameIndex];
                    String content = thisFunction.substring(functionContentStartIndex);
                    
                    FunctionNode root = createTree(s, myTurtle); 
                    root.setName(functionName);
                    root.setContent(content);
                    myFunctionNodes.add(root);
                }
            }
            
        } else { //hasTo = false
            FunctionNode root = createTree(s, myTurtle);
            root.setName("NoName");
            root.setContent(s);
            myFunctionNodes.add(root);
        }

        for (int i=0;i<words.length;i++) {
            if (words[i].equals("make")) {
                VariableNode vn = new VariableNode(myTurtle, words[i+1].substring(1), Double.parseDouble(words[i+2]));
            }
            if (words[i].equals("set")) {
                // find variable node
                for (VariableNode thisNode : myVariableNodes) {
                    if (thisNode.getName().equals(words[i+1])) {
//                        thisNode.setValue() //TODO
                    }
                }
            }
        }

    }

    public FunctionNode createTree(String content, Turtle turtle) throws ClassNotFoundException, 
    NoSuchMethodException, SecurityException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, NoSuchFieldException {
        myTurtle = turtle;
        NodeFactory nodeFactory = new NodeFactory(myTurtle, myLanguage);
        
        FunctionNode root = new FunctionNode(turtle, content);
        AbstractNode blockNode = new BlockNode(myTurtle);
        root.setLeftNode(blockNode);
        
//        processFunction(function); TODO brackets

        String[] words = content.split(" ");
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
                AbstractNode bnLeft = new BlockNode(myTurtle);
                AbstractNode bnRight = new BlockNode(myTurtle);
                currentNode.setLeftNode(bnLeft);
                currentNode.setRightNode(bnRight);
                // create a condition node for left block
                AbstractNode conditionLeft = new ConditionNode(myTurtle);
                bnLeft.setLeftNode(conditionLeft);
                bnLeft.setRightNode(new BlockNode(myTurtle));

                // create condition for condition left; condition right is the opposite of condition left
                currentNode = bnLeft.getLeftNode();
            }
            if ((currentNode instanceof IfNode || currentNode instanceof RepeatNode) && currentNode.getLeftNode() == null) {
                // create 1 block node
                AbstractNode bn = new BlockNode(myTurtle);
                // create 1 condition node
                AbstractNode conditionNode = new ConditionNode(myTurtle);
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

    public double traverseTree(Turtle turtle, AbstractNode root) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {  
        myTurtle = turtle;
        if (root!=null) {
            for (AbstractNode childNode : root.getChildren()) {
                traverseTree(myTurtle, childNode);
            }
            return root.evaluate();
        }
        return 1; //TODO
    }
}
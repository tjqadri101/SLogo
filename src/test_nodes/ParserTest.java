package test_nodes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import parse.Function;
import parse.Parser;
import turtle.Turtle;
import nodes.AbstractNode;
import nodes.BlockNode;

public class ParserTest {

    private static Turtle myTurtle = new Turtle(0, 0);
    private static Parser myParser = new Parser(myTurtle);
    
    public AbstractNode parseAndCreateTree(String string) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        AbstractNode node = new BlockNode(myTurtle);
        
        return myParser.createTree(new Function(string));
        
    }
    
    public void traverseTree(AbstractNode node) {
        myParser.traverseTree(node, new ArrayList<AbstractNode>());
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ParserTest test = new ParserTest();
        // test repeat
//        String string = "repeat 2 [ fd 50 fd 100 ]"; 
        
        // test if
        String string = "if greaterp 3 2 [ fd 50 fd 100 fd 80 ]";
        
        
//        String string = "fd 50 fd 100";

        AbstractNode node = test.parseAndCreateTree(string);
        System.out.println("ParserTest parseAndCreateTree: root is " + node);
        
        while (node != null) {
            
            AbstractNode nextNode = new BlockNode(myTurtle);
            if (node.getLeftNode()!=null) {
                nextNode = node.getLeftNode();
                System.out.println("ParserTest parseAndCreateTree: node" + node+"'s left node is " + nextNode);
            }
            if (node.getRightNode()!=null) {
                nextNode = node.getRightNode();
                System.out.println("ParserTest parseAndCreateTree: node" + node+"'s right node is " + nextNode);
            }
            node = nextNode;
        }
        
//        test.traverseTree(node);
//        System.out.println("ParserTest traverseTree: turtle's final position is " + myTurtle.getXPos() 
//                                                                                   + ", " + myTurtle.getYPos());
        
    }
}

package test_nodes;

import parse.Function;
import parse.Parser;
import turtle.Turtle;
import nodes.AbstractNode;
import nodes.BlockNode;

public class ParserTest {

    private static Turtle myTurtle;
    
    public AbstractNode parseAndCreateTree(String string) {
        myTurtle = new Turtle(0, 0);
        AbstractNode node = new BlockNode(myTurtle);
        Parser parser = new Parser(myTurtle);
        
        return parser.createTree(new Function(string));
        
    }
    
    
    public static void main(String[] args) {
        ParserTest test = new ParserTest();
        String string = "repeat 2 [ fd 50 fd 100 ]";
        
//        String string = "fd 50 fd 100";

        AbstractNode node = test.parseAndCreateTree(string);
        System.out.println("ParserTest: root is " + node);
        
        while (node != null) {
            
            AbstractNode nextNode = new BlockNode(myTurtle);
            if (node.getLeftNode()!=null) {
                nextNode = node.getLeftNode();
                System.out.println("ParserTest: node" + node+"'s left node is " + nextNode);
            }
            if (node.getRightNode()!=null) {
                nextNode = node.getRightNode();
                System.out.println("ParserTest: node" + node+"'s right node is " + nextNode);
            }
            node = nextNode;
        }
        
        return;
        
    }
}

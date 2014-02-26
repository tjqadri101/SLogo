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
        String string = "repeat 2 [ fd 50 ft 100 ]";
        AbstractNode node = test.parseAndCreateTree(string);
        
        while (node != null) {
            System.out.println(node);
            AbstractNode nextNode = new BlockNode(myTurtle);
            if (node.getLeftNode()==null && node.getRightNode()==null) {
                return;
            }
            if (node.getLeftNode()!=null) {
                nextNode = node.getLeftNode();
                System.out.println(nextNode);
            }
            if (node.getRightNode()!=null) {
                nextNode = node.getRightNode();
                System.out.println(nextNode);
            }
            node = nextNode;
        }
        
    }
}

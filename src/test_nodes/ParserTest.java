package test_nodes;

import parse.Function;
import parse.Parser;
import turtle.Turtle;
import nodes.AbstractNode;
import nodes.BlockNode;

public class ParserTest {

    public AbstractNode parseAndCreateTree(String string) {
        Turtle turtle = new Turtle(0, 0);
        AbstractNode node = new BlockNode(turtle);
        Parser parser = new Parser(turtle);
        
        return parser.createTree(new Function(string));
        
    }
    
    
    public static void main(String[] args) {
        ParserTest test = new ParserTest();
        String string = "fd 50";
        AbstractNode node = test.parseAndCreateTree(string);
//        System.out.println(node);
    }
}

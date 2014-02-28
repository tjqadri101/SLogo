package test_nodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import parse.Function;
import parse.Parser;
import nodes.AbstractNode;
import nodes.controlnodes.ConditionNode;
import nodes.controlnodes.RepeatNode;
import turtle.Turtle;

public class TestBackend {
    
    @org.junit.Test
    public void testForCreateTree_Repeat() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        String string = "repeat 2 [ fd 50 fd 100 fd 80 ]";
        Turtle turtle = new Turtle(0, 0);
        Parser parser = new Parser(turtle);
        parser.createFunctionsAndVariables(string);
        for (Function function : parser.getFunctions()) {
            AbstractNode node = parser.createTree(function, turtle);
            assert node instanceof RepeatNode;
            assert node.getLeftNode() instanceof ConditionNode;
            
        }
        
        
      }
    
    
}

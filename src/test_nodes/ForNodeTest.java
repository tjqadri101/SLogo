//package test_nodes;
//
//import nodes.AbstractNode;
//import nodes.Token;
//import nodes.commandnodes.ForwardNode;
//import nodes.controlnodes.ForNode;
//import turtle.Turtle;
//
//public class ForNodeTest implements Token{
//    
//    public void moveTurtle() {
//        Turtle turtle = new Turtle(0, 0);
//        System.out.println("created turtle");
//        AbstractNode forNode = new ForNode(turtle, FOR_LOOP, 3);
//        AbstractNode fdNode = new ForwardNode(turtle, FORWARD, 50);
//        forNode.setLeftNode(fdNode);
//        forNode.setEndingNode(fdNode);
//        
//        forNode.action();
//        System.out.println("updated turtle position: x=" + turtle.getXPos() + ", y=" + turtle.getYPos());
//    }
//    
//    public static void main (String[] args) {
//        ForNodeTest test = new ForNodeTest();
//        test.moveTurtle();
//        
//    }
//}

package nodes.commandnodes;

import turtle.Turtle;

public class ForwardNode extends CommandNode {

    private Turtle myTurtle;
    private double myValue;
    
    public ForwardNode (Turtle turtle) {
        super(turtle);
    }

    

    
    public void action() {
        myTurtle.updatePosition(0, myValue);
    }

}

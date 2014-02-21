package nodes;

import turtle.Turtle;

public class ActionNode extends AbstractNode implements Token {

    public ActionNode (Turtle turtle, String token, double value) {
        super(turtle, token, value);
        
    }

    @Override
    public void action () {
        //implemented in sub classes
    }

}

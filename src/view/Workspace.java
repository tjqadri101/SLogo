package view;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parse.Parser;
import turtle.Moveable;
import turtle.Turtle;

//this class holds the logic for the main pieces of each window - each has separate parser (model) and text input 
public class Workspace extends JPanel{
	
	public static final Integer LENGTH = 800;
	public static final Integer HEIGHT = 600;
	public static final Dimension SIZE = new Dimension(LENGTH, HEIGHT);
	
	private Parser myParser;
	private Moveable myTurtle;
	private CommandCenter myCommandCenter;
	private ActionDisplay myActionDisplay;
	
	public Workspace(){
		Turtle tempTurtle = new Turtle();
		myParser = new Parser(tempTurtle, "");
		myTurtle = (Moveable) tempTurtle;
		
		this.add(makePanel());
		this.add(setAndMakeCommandCenter());
		this.add(setAndMakeActionDisplay());
		
	}
	
	private CommandCenter setAndMakeCommandCenter(){
		myCommandCenter = new CommandCenter();
		return myCommandCenter;
	}
	
	private ActionDisplay setAndMakeActionDisplay(){
		myActionDisplay = new ActionDisplay();
		return myActionDisplay;
	}
	
	private JPanel makePanel(){
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(LENGTH, HEIGHT/60));
		return panel;
	}
	
	
	
}

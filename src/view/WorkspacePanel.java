package view;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parse.Parser;
import turtle.Moveable;
import turtle.Turtle;

//this class holds the logic for the main pieces of each window - each has separate parser (model) and text input 
public class WorkspacePanel extends JPanel{
	
	public static final Integer LENGTH = 800;
	public static final Integer HEIGHT = 600;
	public static final Dimension SIZE = new Dimension(LENGTH, HEIGHT);
	
	private Parser myParser;
	private Moveable myTurtle;
	private CommandPanel myCommandPanel;
	private ActionDisplayPanel myActionDisplayPanel;
	
	public WorkspacePanel(){
		Turtle tempTurtle = new Turtle();
		myParser = new Parser(tempTurtle, "");
		myTurtle = (Moveable) tempTurtle;
		
		this.add(makePanel());
		this.add(setAndMakeCommandCenter());
		this.add(setAndMakeActionDisplay());
		
	}
	
	private CommandPanel setAndMakeCommandCenter(){
		myCommandPanel = new CommandPanel();
		return myCommandPanel;
	}
	
	private ActionDisplayPanel setAndMakeActionDisplay(){
		myActionDisplayPanel = new ActionDisplayPanel();
		return myActionDisplayPanel;
	}
	
	private JPanel makePanel(){
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(LENGTH, HEIGHT/60));
		return panel;
	}
	
	
	
}

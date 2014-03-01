package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parse.Parser;
import test_nodes.ParserTest;
import turtle.Moveable;
import turtle.Turtle;

//this class holds the logic for the main pieces of each window - each has separate parser (model) and text input 
public class WorkspacePanel extends JPanel{
	
	public static final Integer WIDTH = 800;
	public static final Integer HEIGHT = 600;
	
	private ParserTest myParser;
	private Turtle myTurtle;
	private CommandPanel myCommandPanel;
	private ActionDisplayPanel myActionDisplayPanel;
	
	public WorkspacePanel(){
		myTurtle = new Turtle(320d, 240d, 0);
		myParser = new ParserTest();

		this.setBackground(Color.black);
		this.add(setAndMakeActionDisplay(), BorderLayout.WEST);
		this.add(setAndMakeCommandCenter(), BorderLayout.EAST);
	}
	
	private CommandPanel setAndMakeCommandCenter(){
		myCommandPanel = new CommandPanel(WIDTH, HEIGHT, .33, myParser, myTurtle);
		return myCommandPanel;
	}
	
	private ActionDisplayPanel setAndMakeActionDisplay(){
		myActionDisplayPanel = new ActionDisplayPanel(WIDTH, HEIGHT, .67, myParser, myTurtle);
		return myActionDisplayPanel;
	}
	
}

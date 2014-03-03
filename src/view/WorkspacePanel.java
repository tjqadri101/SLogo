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
public class WorkspacePanel extends JPanel {

	public static final Integer WIDTH = 800;
	public static final Integer HEIGHT = 600;

	/**
	 * Interfacing from Frontend to Backend
	 */
	private ParserTest myParser = new ParserTest();
	private Turtle myTurtle = new Turtle(0, 0, 0);
	private CommandPanel myCommandPanel;
	private ActionDisplayPanel myActionDisplayPanel;
	private JFrame myTopFrame;
	
	public WorkspacePanel(JFrame f){
		myTurtle = new Turtle(320d, 240d, 0);
		myParser = new ParserTest();
		myTopFrame = f;


		this.setBackground(Color.black);
		this.add(setAndMakeActionDisplay(), BorderLayout.WEST);
		this.add(setAndMakeCommandCenter(), BorderLayout.EAST);
		setFocusable(false);
	}

	private CommandPanel setAndMakeCommandCenter() {
		myCommandPanel = new CommandPanel(WIDTH, HEIGHT, .33, myParser,
				myTurtle);
		return myCommandPanel;
	}
	
	private ActionDisplayPanel setAndMakeActionDisplay(){
		myActionDisplayPanel = new ActionDisplayPanel(WIDTH, HEIGHT, .67, myParser, myTopFrame,myTurtle);
		return myActionDisplayPanel;
	}

}

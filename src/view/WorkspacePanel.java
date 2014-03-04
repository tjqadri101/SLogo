package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import test_nodes.ParserTest;
import turtle.Turtle;

//this class holds the logic for the main pieces of each window - each has separate parser (model) and text input 
public class WorkspacePanel extends JPanel {

	public static final Integer WIDTH = 1000;
	public static final Integer HEIGHT = 800;

	/**
	 * Interfacing from Frontend to Backend
	 */
	private ParserTest myParser = new ParserTest();
	//private Turtle myTurtle = new Turtle(0, 0, 0);
	private ProgrammingPanel myProgrammingPanel;
	private ActionDisplayPanel myActionDisplayPanel;
	
	public WorkspacePanel(JFrame f){
		//myTurtle = new Turtle(320d, 240d, 0);
		myParser = new ParserTest();
		GridLayout gl = new GridLayout(1,2);
		this.setBorder(
	            BorderFactory.createTitledBorder("Workspace"));
		this.setLayout(gl);
		this.setPreferredSize(new Dimension(1100, 640));
		this.add(setAndMakeActionDisplay());
		this.add(setAndMakeCommandCenter());
		this.revalidate();
	}

	private ProgrammingPanel setAndMakeCommandCenter() {
		myProgrammingPanel = new ProgrammingPanel(myParser, null);
		return myProgrammingPanel;
	}
	
	private ActionDisplayPanel setAndMakeActionDisplay(){
		myActionDisplayPanel = new ActionDisplayPanel(null);
		return myActionDisplayPanel;
	}

}
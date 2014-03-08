package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.ModelController;
import turtle.ITurtle;
import turtle.Turtle;
import turtle_graphics.TurtleImage;


//this class holds the logic for the main pieces of each window - each has separate parser (model) and text input 
public class WorkspacePanel extends JPanel {

//	public static final Integer WIDTH = 1000;
//	public static final Integer HEIGHT = 800;
	
	private String curFile;
	private int numTurtles;
	//private WorkspacePreference defaultPreference = new WorkspacePreference(Color.GRAY,1,null);
	//private 

	/**
	 * Interfacing from Frontend to Backend via Controller
	 */
	
	private ModelController controller = null;

	//private ParserTest myParser = new ParserTest();

	//private Turtle myTurtle = new Turtle(0, 0, 0);
	private ProgrammingPanel myProgrammingPanel;
	private ActionDisplayPanel myActionDisplayPanel;
	
	public WorkspacePanel(){
		//myTurtle = new Turtle(320d, 240d, 0);
		//myParser = new ParserTest();
		this.setBackground(Color.GRAY);
		
		GridLayout gl = new GridLayout(1,2);
		//this.setWorkspacePreference(defaultPreference);
		this.setBorder(
	            BorderFactory.createTitledBorder("Workspace"));
		this.setLayout(gl);
		this.setPreferredSize(new Dimension(1360, 768));
		this.add(setAndMakeActionDisplay());
		this.add(setAndMakeCommandCenter());
		addPropertyListener();
		this.revalidate();
		
		/*
		 * Controller added below
		 */
		
		this.controller = (ModelController) controller;
	}

	private ProgrammingPanel setAndMakeCommandCenter() {

		myProgrammingPanel = new ProgrammingPanel();
		return myProgrammingPanel;
	}
	
	
	
	private ActionDisplayPanel setAndMakeActionDisplay(){

		myActionDisplayPanel = new ActionDisplayPanel();
		return myActionDisplayPanel;
	}
	private TurtleDisplayPanel getTurtleDisplayPanel(){
		return myActionDisplayPanel.getInstance();
	}
	
	private void addPropertyListener(){
		myProgrammingPanel.getTextArea().addPropertyChangeListener("command", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				// TODO Auto-generated method stub
				//myProgrammingPanel.getTextArea().append("Change happened");
				String commandToController = myProgrammingPanel.getCommand();
				List<TurtleImage> listToController = getTurtleDisplayPanel().getTurtleList();
			}
		});
	}
	
	public Color getWorkspaceColor(){
		return this.getBackground();
	}
	
	public String getCurrentFile(){
		return curFile;
	}
	
	public int getNumTurtles(){
		return numTurtles;
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBackgroundColor(Color c){
		this.setBackground(c);
	}
}

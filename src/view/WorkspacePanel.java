package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

//this class holds the logic for the main pieces of each window - each has separate parser (model) and text input 
public class WorkspacePanel extends JPanel {

//	public static final Integer WIDTH = 1000;
//	public static final Integer HEIGHT = 800;
	
	private String curFile;
	private int numTurtles;
	//private WorkspacePreference defaultPreference = new WorkspacePreference(Color.GRAY,1,null);
	//private 

	/**
	 * Interfacing from Frontend to Backend
	 */

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
		this.setPreferredSize(new Dimension(1024, 800));
		this.add(setAndMakeActionDisplay());
		this.add(setAndMakeCommandCenter());
		this.revalidate();
	}

	private ProgrammingPanel setAndMakeCommandCenter() {

		myProgrammingPanel = new ProgrammingPanel();
		return myProgrammingPanel;
	}
	
	private ActionDisplayPanel setAndMakeActionDisplay(){

		myActionDisplayPanel = new ActionDisplayPanel();
		return myActionDisplayPanel;
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

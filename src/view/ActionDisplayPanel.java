package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import test_nodes.ParserTest;

//choose JPanel because this is more of a container
public class ActionDisplayPanel extends JPanel{

	//Buttons for controlling the turtle and other miscelleneous actions
    private JButton moveTurtleLeft = new JButton("Left");
    private JButton moveTurtleRight = new JButton("Right");
    private JButton moveTurtleUp = new JButton("Up");
    private JButton moveTurtleDown = new JButton("Down");
    
    //Turtle display panel for displaying turtle and its movements
    private TurtleDisplayPanel turtleDisplayPanel;
    
    
	ActionDisplayPanel(Integer width, Integer height, Double ratio, ParserTest myParser){
		this.setPreferredSize(new Dimension((int) (width*ratio), height));
		

	
		turtleDisplayPanel = new TurtleDisplayPanel();
		
		

		turtleDisplayPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		this.add(makeButtonPanel(),BorderLayout.EAST);
		this.add(turtleDisplayPanel,BorderLayout.SOUTH);
	}
	
	//Creates the panel for the buttons
	private JComponent makeButtonPanel() {
		
		JPanel forButtons = new JPanel();
		
		forButtons.add(moveTurtleLeft,BorderLayout.WEST);
		forButtons.add(moveTurtleRight,BorderLayout.EAST);
		forButtons.add(moveTurtleUp,BorderLayout.NORTH);
		forButtons.add(moveTurtleDown,BorderLayout.SOUTH);
		
		forButtons.setBackground(Color.blue);
		forButtons.setPreferredSize(new Dimension(500,50));
		//forButtons.setAlignmentX(LEFT_ALIGNMENT);
		
		return forButtons;
	}
}

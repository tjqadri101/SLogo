package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

//choose JPanel because this is more of a container
public class ActionDisplayPanel extends JPanel{

	//Buttons for controlling the turtle and other miscelleneous actions
    private JButton moveTurtleLeft = new JButton("Left");
    private JButton moveTurtleRight = new JButton("Right");
    private JButton moveTurtleUp = new JButton("Up");
    private JButton moveTurtleDown = new JButton("Down");
    
    
	ActionDisplayPanel(Integer width, Integer height, Double ratio){
		this.setPreferredSize(new Dimension((int) (width*ratio), height));
		
		this.add(makeButtonPanel(),BorderLayout.EAST);
	}
	
	
	//Creates the panel for the buttons
	private JComponent makeButtonPanel() {
		
		JPanel forButtons = new JPanel();
		
		forButtons.add(moveTurtleLeft);
		forButtons.add(moveTurtleRight);
		forButtons.add(moveTurtleUp);
		forButtons.add(moveTurtleDown);
		
		return forButtons;
	}
}

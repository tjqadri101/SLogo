package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import test_nodes.ParserTest;
import turtle.Turtle;

//choose JPanel because this is more of a container
public class ActionDisplayPanel extends JPanel implements KeyListener{

	//Buttons for controlling the turtle and other miscelleneous actions
    private JButton moveTurtleLeft = new JButton("Left");
    private JButton moveTurtleRight = new JButton("Right");
    private JButton moveTurtleUp = new JButton("Up");
    private JButton moveTurtleDown = new JButton("Down");
    
    //Turtle display panel for displaying turtle and its movements
    private TurtleDisplayPanel turtleDisplayPanel;
    
    
	ActionDisplayPanel(Integer width, Integer height, Double ratio, ParserTest myParser,Turtle t){
		this.setPreferredSize(new Dimension((int) (width*ratio), height));
		
		turtleDisplayPanel = new TurtleDisplayPanel();
    	turtleDisplayPanel.setFocusable(true);
    	turtleDisplayPanel.requestFocusInWindow();
        this.addKeyListener(this);
		

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
		
		forButtons.setFocusable(false);
		
		return forButtons;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
    		turtleDisplayPanel.goLeft();
    	}
    	if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
    		turtleDisplayPanel.goRight();
    	}
    	if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
    		turtleDisplayPanel.goDown();
    	}
    	if(arg0.getKeyCode() == KeyEvent.VK_UP){
    		turtleDisplayPanel.goUp();
    	}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

//component that takes into account the users texts - chose JPanel because this is more of a container
public class CommandPanel extends JPanel{
	
	private static final Integer RATIO = 12;
	
	//text field class for displaying and storing user input
	private UserTextPanel userTextPanel;
    	
	public CommandPanel(Integer width, Integer height, Double ratio){
		
		userTextPanel = new UserTextPanel(12);
		//userTextPanel.addActionListener(this);
		
		//Set the preferred size of the command and panel and add an embedded
		//text field panel where the user inputs are displayed
		this.setBackground(Color.MAGENTA);
		this.setPreferredSize(new Dimension((int) (ratio*width), height));
		this.add(userTextPanel, BorderLayout.NORTH);
	}
}

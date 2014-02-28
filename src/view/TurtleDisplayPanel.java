package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

//This class is responsible for displaying the turtle along with showing its movements
//via updates from the user
public class TurtleDisplayPanel extends JPanel {
	
	public TurtleDisplayPanel(Integer width, Integer height, Double ratio){
		this.setPreferredSize(new Dimension((int) (ratio*width/2), height/2));
		this.setBackground(Color.white);
	}

}

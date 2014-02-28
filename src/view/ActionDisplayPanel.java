package view;

import java.awt.Dimension;

import javax.swing.JPanel;

//choose JPanel because this is more of a container
public class ActionDisplayPanel extends JPanel{

	
	ActionDisplayPanel(Integer width, Integer height, Double ratio){
		this.setPreferredSize(new Dimension((int) (width*ratio), height));
	}
}

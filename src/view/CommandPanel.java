package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

//component that takes into account the users texts - chose JPanel because this is more of a container
public class CommandPanel extends JPanel{
	
	private static final Integer RATIO = 12;
	private JTextField myTextField;
	
	CommandPanel(Integer width, Integer height, Double ratio){
		myTextField = makeTextField(width*ratio);
		
		this.setPreferredSize(new Dimension((int) (ratio*width), height));
		this.add(myTextField, BorderLayout.NORTH);
	}
	
	private JTextField makeTextField(Double size){
		JTextField textField = new JTextField((int) (size/RATIO));
		return textField;
	}
}

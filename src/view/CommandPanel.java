package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

//component that takes into account the users texts - chose JPanel because this is more of a container
public class CommandPanel extends JPanel{
	
	//Dimensions of our Panel?
	public static final Dimension SIZE = new Dimension(800, 600);
	
	private static final Integer RATIO = 12;
	private Graphics g;
	
	//User-inputed data
	private String inputData;
	
	//Buttons for controlling the turtle and other miscelleneous actions
    private JButton moveTurtleLeft = new JButton("Left");
    private JButton moveTurtleRight = new JButton("Right");
    private JButton moveTurtleUp = new JButton("Up");
    private JButton moveTurtleDown = new JButton("Down");
    
//    myModel = model;
//    // use resources for labels
//    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
//    // add components to frame
//    setLayout(new BorderLayout());
//    // must be first since other panels may refer to page
//    add(makePageDisplay(), BorderLayout.CENTER);
//    add(makeInputPanel(), BorderLayout.NORTH);
//    add(makeInformationPanel(), BorderLayout.SOUTH);
//    // control the navigation
//    enableButtons();
	
	public CommandPanel(Integer width, Integer height, Double ratio){
		
		//Set the border of the GUI
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.setPreferredSize(new Dimension((int) (ratio*width), height));
		this.add(makeTextField(width*ratio), BorderLayout.NORTH);
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

	private JTextField makeTextField(Double size){
		JTextField textField = new JTextField((int) (size/RATIO));
		return textField;
	}
	
	/**To be implemented later**/
    // organize user's options for controlling/giving input to turtle
//    private JComponent makeInputPanel ()
//    {
//        JPanel result = new JPanel(new BorderLayout());
//        result.add(makeNavigationPanel(), BorderLayout.NORTH);
//        result.add(makePreferencesPanel(), BorderLayout.SOUTH);
//        return result;
//    }
}

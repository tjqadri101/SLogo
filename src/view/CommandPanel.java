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
	private JTextField myTextField;
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
		myTextField = makeTextField(width*ratio);
		
		//Create new Graphics object
		//g = new Graphics();
		
		//Set the border of the GUI
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.setPreferredSize(new Dimension((int) (ratio*width), height));
		this.add(myTextField, BorderLayout.NORTH);
		this.add(makeInputPanel(),BorderLayout.EAST);
	}
	
	//Creates the panel for the buttons
	private JComponent makeInputPanel() {
		JPanel forButtons = new JPanel();
		forButtons.add(moveTurtleLeft);
		forButtons.add(moveTurtleRight);
		forButtons.add(moveTurtleUp);
		forButtons.add(moveTurtleDown);
		
		return null;
	}

	private JTextField makeTextField(Double size){
		JTextField textField = new JTextField((int) (size/RATIO));
		return textField;
	}
	
	private void paint(){
		//Paint the GUI onto the screen for user input
		//paintComponent(this);
		
	}
	
	//Enable the buttons for user control
    // only enable buttons when useful to user
    private void enableButtons()
    {
        moveTurtleLeft.setEnabled(true);
        moveTurtleRight.setEnabled(true);
        moveTurtleUp.setEnabled(true);
        moveTurtleDown.setEnabled(true);
        //moveTurtleDown.
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

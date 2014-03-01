package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UserTextPanel extends JTextField implements ActionListener{
	
	//user input data
	private String userInputData;
	
	public UserTextPanel(int numColumns){
		//Invokes a JTextField constructor
		super(numColumns);
			
		//Sets up this panel to monitor when the user types into the
		//text field
		addActionListener(this);
		
		//sets the text to be left justified
		setHorizontalAlignment(LEFT);
	}

//	//Updates the contents of userInputData whenever
//	//the user inputs a keystroke
//	public void textValueChanged(TextEvent e) {
//		System.out.println(userInputData);
//		userInputData = paramString();
//	}
	
	//return the userInputData
	public String getInputData(){
		return userInputData;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		userInputData = this.getText();
		System.out.println(userInputData);
	}

}

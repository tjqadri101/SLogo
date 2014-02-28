package view;

import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UserTextPanel extends JTextField implements TextListener{
	
	//user input data
	private String userInputData;
	
	public UserTextPanel(int numColumns){
		//Invokes a JTextField constructor
		super(numColumns);
		
		//sets the text to be left justified
		setHorizontalAlignment(LEFT);
	}

	//Updates the contents of userInputData whenever
	//the user inputs a keystroke
	public void textValueChanged(TextEvent e) {
		userInputData = paramString();
	}
	
	//return the userInputData
	public String getInputData(){
		return userInputData;
	}

}

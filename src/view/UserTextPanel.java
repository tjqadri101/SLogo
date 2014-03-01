package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UserTextPanel extends JScrollPane implements ActionListener{
	
	//user input data
	private String userInputData;
	private JTextArea userInput;
	//private JButton execute = new JButton("Execute!");
	
	public UserTextPanel(int xDim,int yDim){

		this.setPreferredSize(new Dimension(xDim, yDim));
		userInput = new JTextArea(12,12);
		
		this.setViewportView(userInput);
					
		//sets the text to be left justified
		this.setAlignmentX(LEFT_ALIGNMENT);
		//this.add(execute);
		//this.validate();
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
	
	//Clears the text area field.Called when a user pressed the "Execute!" button
	public void clearText(){
		userInput.setText(null);
	}
	
	public String getText(){
		return userInput.getText();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		userInputData = userInput.getText();
		System.out.println(userInputData);
	}

}

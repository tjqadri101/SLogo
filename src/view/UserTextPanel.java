package view;

import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JTextField;

public class UserTextPanel extends JTextField implements TextListener{
	
	public UserTextPanel(int numColumns){
		setHorizontalAlignment(LEFT);
	}

	@Override
	public void textValueChanged(TextEvent e) {

		
	}

}

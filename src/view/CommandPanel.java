package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

//component that takes into account the users texts - chose JPanel because this is more of a container
public class CommandPanel extends JPanel{
	
	private JTextField myTextField;
	
	CommandPanel(){
		myTextField = makeTextField();
		this.add(myTextField);
	}
	
	private JTextField makeTextField(){
		JTextField textField = new JTextField(20);
		return textField;
	}
}

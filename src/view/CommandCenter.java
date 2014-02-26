package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

//component that takes into account the users texts - chose JPanel because this is more of a container
public class CommandCenter extends JPanel{
	
	private JTextField myTextField;
	
	CommandCenter(){
		myTextField = makeTextField();
		this.add(myTextField);
	}
	
	private JTextField makeTextField(){
		JTextField textField = new JTextField(20);
		return textField;
	}
}

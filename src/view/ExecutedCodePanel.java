package view;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class ExecutedCodePanel extends JScrollPane{

	//The text area that will contain the user code that has been executed
	private JList executedCode;
	private DefaultListModel codeSnippets = new DefaultListModel();
	
	public ExecutedCodePanel(){
		this.setPreferredSize(new Dimension(250,250));
		executedCode = new JList(codeSnippets);
		executedCode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setViewportView(executedCode);
		//getContentPane().add(executedCode);
	}
	
	public void addToCodeList(String code){
		//System.out.println("fire");
		codeSnippets.addElement(code);
	}
}

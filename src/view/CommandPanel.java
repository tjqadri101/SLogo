package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nodes.AbstractNode;
import test_nodes.ParserTest;
import turtle.Turtle;

//component that takes into account the users texts - chose JPanel because this is more of a container
public class CommandPanel extends JPanel implements ActionListener {
	
	private static final Integer RATIO = 12;
	
	//text field class for displaying and storing user input
	private UserTextPanel userTextPanel;
	private JButton execute = new JButton("Execute!");
	private ExecutedCodePanel executedCodePanel = new ExecutedCodePanel();
	private ParserTest parser;
	private Turtle turtle;
	
//	private JPanel userInputAndButton = new JPanel();
//	private JPanel userOutput = new JPanel();
    	
	public CommandPanel(Integer width, Integer height, Double ratio, ParserTest myParser, Turtle t){
		
		parser = myParser;
		turtle = t;
		
		userTextPanel = new UserTextPanel(250,200);
		//userTextPanel.addActionListener(this);
		
		//Set the preferred size of the command and panel and add an embedded
		//text field panel where the user inputs are displayed
		this.setBackground(Color.MAGENTA);
		this.setPreferredSize(new Dimension((int) (ratio*width), height));
		
		//userInputAndButton.add
		this.add(userTextPanel, BorderLayout.NORTH);
		this.add(execute,BorderLayout.CENTER);
		this.add(executedCodePanel,BorderLayout.SOUTH);
		
		setFocusable(false);
		
		execute.addActionListener(this);
	}

	//Called when the user clicks the execute button. Will pass the text in the text area to
	//the backend for parsing.
	public void actionPerformed(ActionEvent arg0) {
		executedCodePanel.addToCodeList(userTextPanel.getText());
		String input = userTextPanel.getText();
		userTextPanel.clearText();
		AbstractNode node = null;
		try {
			System.out.println(userTextPanel.getText());
			node = parser.parseAndCreateTree(input, turtle);
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parser.traverseTree(turtle, node);
	}
}

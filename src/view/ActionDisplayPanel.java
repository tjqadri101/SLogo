package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import turtle.Turtle;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.net.URI;

import javax.swing.JLabel;

import test_nodes.ParserTest;

//choose JPanel because this is more of a container
public class ActionDisplayPanel extends JPanel{

	//Buttons for controlling the turtle and other miscelleneous actions
	private JButton moveTurtleLeft = new JButton("Left");
	private JButton moveTurtleRight = new JButton("Right");
	private JButton moveTurtleForward = new JButton("Forward");
	private JButton moveTurtleBack = new JButton("Back");
	
	private TurtleDisplayPanel turtleDisplayPanel;
	private ScrollableTextArea myScrollableTextArea = new ScrollableTextArea(5,30,null);

	public ActionDisplayPanel(Turtle t) {
		turtleDisplayPanel = new TurtleDisplayPanel(t);

		this.add(myScrollableTextArea);
		this.add(makeClear());
		this.add(makeButtonRotateR45());
		this.add(turtleDisplayPanel);
		this.add(makeTurtleMovementButtons());
		revalidate();
		repaint();
	}

	protected void showMessage (String message) {
		myScrollableTextArea.append(message + "\n");
		myScrollableTextArea.setCaretPosition(myScrollableTextArea.getTextLength());

	}
	
	protected void showState(){
		double matchedX = turtleDisplayPanel.getCurX() - 320d;
		double matchedY = -(turtleDisplayPanel.getCurY() - 240d);

		String messagePos = "The coordinates of turtle are (" + matchedX + "," + matchedY + ")";
		String messageAngle = "The turtle's heading is (" + turtleDisplayPanel.getAngle() + ")";
		showMessage(messagePos + "\n" + messageAngle);
	}
	
	protected JButton makeButtonRotateR45(){
		JButton right = new JButton("Right Rotate");
		right.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				turtleDisplayPanel.rotateTurtleRight();
				showState();
			}
		});
		return right;
	}
	protected JButton makeClear () {
		JButton result = new JButton(("ClearCommand"));
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				myScrollableTextArea.setText("");
				turtleDisplayPanel.resetTurtle();
				showState();
			}
		});
		return result;
	}
	
	protected JComponent makeTurtleMovementButtons(){
		JPanel buttons = new JPanel(new BorderLayout());
		
		moveTurtleLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleLeft();
				showState();
			}
		});
		
		moveTurtleRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleRight();
				showState();
			}
		});
		
		moveTurtleForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleForward();
				showState();
			}
		});
		
		moveTurtleBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleBack();
				showState();
			}
		});
		
		buttons.add(moveTurtleForward,BorderLayout.NORTH);
		buttons.add(moveTurtleBack,BorderLayout.SOUTH);
		buttons.add(moveTurtleLeft,BorderLayout.WEST);
		buttons.add(moveTurtleRight,BorderLayout.EAST);
		
		return buttons;
		
	}
}

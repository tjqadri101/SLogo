package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private Turtle myTurtle;
	private Graphics2D g2d;
	private BufferedImage displayTurtle;
	TurtleImage turtlePic = new TurtleImage();

	private double curX;
	private double curY;
	private double prevX;
	private double prevY;
	private double deltaX;
	private double deltaY;
	private double myAngle;
	private boolean center;

	private MouseListener myMouseListener;
	private KeyListener myKeyListener;
	private JTextArea myTextArea;

	/*ActionDisplayPanel(Integer width, Integer height, Double ratio, ParserTest myParser, Turtle t){
		this.setPreferredSize(new Dimension((int) (width*ratio), height));

		turtleDisplayPanel = new TurtleDisplayPanel(t);		
	}*/

	public ActionDisplayPanel(Turtle t) {
		this.setPreferredSize(new Dimension(640, 550));
		this.setBackground(Color.white);
		myTurtle = t;
		curX = 320; curY = 240;
		center = true;


		try {
			displayTurtle = turtlePic.setImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		makeKeyListener();
		makeMouseListener();
		this.add(makeInfoDisplay());
		this.add(makeClear());
		this.add(makeButtonRotateR45());

		
		this.add(makeHyperLink("Go to basic commands page",
				"http://www.cs.duke.edu/courses/cps108/current/assign/03_slogo/commands.php",
				0,0),BorderLayout.SOUTH);
		//this.add(turtleDisplayPanel,BorderLayout.EAST);

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g2d = (Graphics2D) g;
		if(center){
			turtlePic.paintCenter(g2d, displayTurtle);
			prevX = curX; prevY = curY; myAngle = 0;
		}	
		else{
			turtlePic.paint(g2d, prevX, prevY, deltaX, deltaY, myAngle, displayTurtle);
		}
		center = false;
	}

	protected void showMessage (String message) {
		myTextArea.append(message + "\n");
		myTextArea.setCaretPosition(myTextArea.getText().length());
	}
	protected void showState(){
		double matchedX = curX - 320d;
		double matchedY = -(curY - 240d);

		String messagePos = "The coordinates of turtle are (" + matchedX + "," + matchedY + ")";
		String messageAngle = "The turtle's heading is (" + myAngle + ")";
		showMessage(messagePos + "\n" + messageAngle);
	}
	protected void makeMouseListener(){
		myMouseListener = new MouseListener(){	
			@Override
			public void mousePressed(MouseEvent e) {
				return;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				return;
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				myAngle += -90d;
				repaint();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				return;
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				return;
			}
		};
	}
	protected void makeKeyListener(){
		myKeyListener = new KeyListener(){
			@Override
			public void keyPressed(KeyEvent key) {
				checkKeys(key);
				showState();
			}
			@Override
			public void keyReleased(KeyEvent key) {
				return;
			}
			@Override
			public void keyTyped(KeyEvent key) {
				return;
			}

		};
	}
	protected void checkKeys(KeyEvent key){
		if(key.getKeyCode() == KeyEvent.VK_LEFT){
			prevX = curX; prevY = curY;
			deltaX = -5; deltaY = 0;
			curX = curX+deltaX;  
			repaint();
		}
		if(key.getKeyCode() == KeyEvent.VK_RIGHT){
			prevX = curX; prevY = curY;
			deltaX = 5; deltaY = 0;
			curX = curX+deltaX; 
			repaint();
		}
		if(key.getKeyCode() == KeyEvent.VK_DOWN){
			prevX = curX; prevY = curY;
			deltaX = 0; deltaY = 5;
			curY = curY+deltaY; 
			repaint();
		}
		if(key.getKeyCode() == KeyEvent.VK_UP){
			prevX = curX; prevY = curY;
			deltaX = 0; deltaY = -5;
			curY = curY+deltaY; 
			repaint();
		}
	}	
	protected JButton makeButtonRotateR45(){
		JButton right = new JButton("Right Rotate");
		right.addKeyListener(myKeyListener);
		right.addMouseListener(myMouseListener);

		return right;
	}
	protected JButton makeClear () {
		JButton result = new JButton(("ClearCommand"));
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				myTextArea.setText("");
				center = true;
				curX = 320; curY = 240; myAngle = 0;
				repaint();
			}
		});
		return result;
	}

	protected JComponent makeInfoDisplay(){
		myTextArea = new JTextArea(5, 30);
		myTextArea.setEditable(false);
		JScrollPane toReturn = new JScrollPane(myTextArea);
		return toReturn;
	}

}

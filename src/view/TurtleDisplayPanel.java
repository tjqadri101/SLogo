//This class is responsible for displaying the turtle along with showing its movements
//via updates from the user

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.KeyStroke;

import turtle.Turtle;

public class TurtleDisplayPanel extends JPanel {

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

	public TurtleDisplayPanel(JFrame f, Turtle t) {
		this.setPreferredSize(new Dimension(640, 480));
		this.setBackground(Color.white);
		myTurtle = t;
    	/*myAngle =  t.getAngle();
    	curX =  t.getXPos();
    	curY =  t.getYPos();*/
		curX = 320; curY = 240;
		center = true;

		try {
			displayTurtle = turtlePic.setImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 makeKeyListener();
	     makeMouseListener();
	     f.getContentPane().add(this);
	     f.getContentPane().add(makeDisplay(), BorderLayout.WEST);
	     f.getContentPane().add(makePanel(), BorderLayout.EAST);
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
	    	
	    	
	    	String messagePos = "The coordinates of turtle are (" + matchedX
	    			+ "," + matchedY + ")";
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
	protected JComponent makeDisplay(){
    	myTextArea = new JTextArea(5, 30);
    	//myTextArea.addKeyListener(myKeyListener);
    	//calVertices();
    	myTextArea.setEditable(false);
    	return new JScrollPane(myTextArea);
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

    protected JComponent makePanel () {
    	JPanel panel = new JPanel();
        this.add(makeClear());
        this.add(makeButtonRotateR45());
        return panel;
    }
}

package view;

import graphics.TurtleImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import turtle.Turtle;

import javax.swing.JLabel;

import test_nodes.ParserTest;

//choose JPanel because this is more of a container
public class TurtleDisplayPanel extends JPanel{

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
	private int myPanelHeight;
	private int myPanelWidth;

	public TurtleDisplayPanel() {

		this.setBackground(Color.white);
		center = true; 
		myAngle = 0;
		
		try {
			displayTurtle = turtlePic.setImage();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g2d = (Graphics2D) g;
		if(center){
			setCenter();
			prevX = curX;
			prevY = curY;
			turtlePic.paint(g2d, prevX, prevY, deltaX, deltaY, myAngle, displayTurtle);
		}	
		else{
			turtlePic.paint(g2d, prevX, prevY, deltaX, deltaY, myAngle, displayTurtle);
		}
		center = false;
	}
	
	public double getCurX(){
		return curX;
	}
	
	public double getCurY(){
		return curY;
	}
	
	public double getAngle(){
		return myAngle;
	}
	
	public void moveTurtleLeft(){
		prevX = curX; prevY = curY;
		deltaX = -5; deltaY = 0;
		curX = curX+deltaX;  
		repaint();
	}
	
	public void moveTurtleRight(){
		prevX = curX; prevY = curY;
		deltaX = 5; deltaY = 0;
		curX = curX+deltaX; 
		repaint();
	}
	
	public void moveTurtleBack(){
		prevX = curX; prevY = curY;
		deltaX = 0; deltaY = 5;
		curY = curY+deltaY; 
		repaint();
	}
	
	public void moveTurtleForward(){
		prevX = curX; prevY = curY;
		deltaX = 0; deltaY = -5;
		curY = curY+deltaY; 
		repaint();
	}
	
	public void rotateTurtleRight(){
		myAngle -= 90;
		if(myAngle <= -360){
			myAngle = myAngle + 360d;
		}
		repaint();
	}
	private void setCenter(){
		curX = (double) (this.getWidth()/2); 
		curY = (double) (this.getHeight()/2); 
		myAngle = 0;
	}
	
	public String getLocationInfo(){
		double matchedX = curX - (double) this.getWidth()/2;
		double matchedY = -(curY - (double) this.getHeight()/2);

		String messagePos = "The coordinates of turtle are (" + matchedX + "," + matchedY + ")";
		return messagePos;
	}
	
	public void resetTurtle(){
		center = true;
		setCenter();
		repaint();
	}
}
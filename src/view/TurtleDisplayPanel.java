package view;

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


	public TurtleDisplayPanel(Turtle t) {
		//this.setPreferredSize(new Dimension(640, 550));
		this.setBackground(Color.white);
		myTurtle = t;
		//curX = 320; curY = 240;
		curX = 80; curY = 120;
		center = true;


		try {
			displayTurtle = turtlePic.setImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		myAngle += 90;
		repaint();
	}
	
	public void resetTurtle(){
		center = true;
		curX = 320; curY = 240; myAngle = 0;
		repaint();
	}
}
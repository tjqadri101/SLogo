package view;

import graphics.TurtleImage;
//import graphics.Line;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JPanel;

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
	private LinkedList<Line2D> lineList;
	private Color lineColor;
	private int myPen;

	public TurtleDisplayPanel() {

		this.setBackground(Color.white);
		center = true; 
		myAngle = 0;
		lineColor = Color.black;
		lineList = new LinkedList<Line2D>();
		
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
		checkLineAddition();
		for(Line2D line:lineList){
			g2d.setColor(lineColor);
			g2d.draw((Shape) line);
			
		}
		center = false;
	}
	
	private void checkLineAddition() {
		boolean changedPos = curX!=prevX | curY != prevY;
		if(myPen == 1 && changedPos){
			lineList.add(new Line2D.Double(prevX, prevY, curX, curY));
		}
		
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
	
	public void setPenToggle(){
		if(myPen == 0)
			myPen = 1;
		else
			myPen = 0;
	}
	
	public void setColor(Color c){
		lineColor = c;
	}
	
	public void resetTurtle(){
		center = true;
		setCenter();
		lineList.clear();
		repaint();
	}
}
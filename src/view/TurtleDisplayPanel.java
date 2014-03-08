package view;


import graphics.TurtleImage;




import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JPanel;

import turtle.Turtle;

import javax.swing.JLabel;

//choose JPanel because this is more of a container
public class TurtleDisplayPanel extends JPanel implements IView{

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
	private boolean initialize;
	private int myPanelHeight;
	private int myPanelWidth;
	private LinkedList<Line2D> lineList;
	private HashMap<Line2D, Color> lineColorMap;
	private Color penColor;
	private static final Color Default_Pen_Color = Color.black;
	private int myPen;

	public TurtleDisplayPanel() {

		this.setBackground(Color.white);
		initialize = true; 
		myAngle = 0;
		penColor = Default_Pen_Color;
		lineList = new LinkedList<Line2D>();
		lineColorMap = new HashMap<Line2D, Color>();

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
		if(initialize){
			setCenter();

			turtlePic.paint(g2d, prevX, prevY, deltaX, deltaY, myAngle, displayTurtle);
		}	
		else{
			turtlePic.paint(g2d, prevX, prevY, deltaX, deltaY, myAngle, displayTurtle);
			checkLineAddition();
			for(Line2D line:lineList){
				g2d.setColor(lineColorMap.get(line));
				g2d.draw((Shape) line);
			}
		}
		initialize = false;
	}

	private void checkLineAddition() {
		if(myPen == 1){
			Line2D newLine = new Line2D.Double(prevX, prevY, curX, curY);
			lineColorMap.put(newLine, penColor);
			lineList.add(newLine);
		}

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

	public double getCurX(){
		return curX;
	}

	public double getCurY(){
		return curY;
	}

	public double getAngle(){
		return myAngle;
	}
	public String getPositionInfo(){
		double matchedX;
		double matchedY;
		matchedX =  curX - (double) this.getWidth()/2;
		matchedY = -(curY - (double) this.getHeight()/2);

		String positionInfo = "The coordinates of turtle are (" + matchedX + "," + matchedY + ")";
		return positionInfo;
	}
	public Color getColor(){
		return penColor;
	}


	public void setPenToggle(){
		if(myPen == 0)
			myPen = 1;
		else
			myPen = 0;
	}

	public void setColor(Color c){
		penColor = c;
	}

	// set it to private as it is never called by external methods
	private void setCenter(){
		curX = (double) (this.getWidth()/2); 
		curY = (double) (this.getHeight()/2); 
		prevX = curX;
		prevY = curY;
		deltaX = 0; deltaY = 0;
		myAngle = 0;
		lineList.clear();
	}



	public void resetTurtle(){
		setCenter();
		repaint();
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}

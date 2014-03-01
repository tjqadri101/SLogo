//This class is responsible for displaying the turtle along with showing its movements
//via updates from the user

package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;


public class TurtleDisplayPanel extends JPanel {

    private Point curP1 = new Point (0,0);
    private Point curP2= new Point (0,0);;
    private Point curP3= new Point (0,0);;
    private Point prevP1;
    private Point prevP2;
    private Point prevP3;
    private int centerX = 320; private int centerY = 240;
    private int myAngle;
    private boolean drawing;
    private MouseListener myMouseListener;
    private KeyListener myKeyListener;
    private static final int DELTA = 10;
    private Graphics2D g2d;
    private JTextArea myTextArea;
    
    public TurtleDisplayPanel() {
    
    	myAngle = 0;
        this.setPreferredSize(new Dimension(640, 480));
        this.setBackground(Color.white);
      
        //this.addMouseListener(myMouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
    
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setColor(Color.green);
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(4,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        calVertices();
        int[] xPoints = new int[] {curP1.x, curP2.x, curP3.x};
        int[] yPoints = new int[] {curP1.y, curP2.y, curP3.y};
        g2d.fillPolygon(xPoints, yPoints, 3);
       // g2d.rotate(Math.toRadians(myAngle));
    }
    
    protected void calVertices(){
    	curP1.x = centerX; 
    	curP1.y = centerY - 5;
    	curP2.x = centerX-5; 
    	curP2.y = centerY + 5;
    	curP3.x = centerX+5; 
    	curP3.y = centerY + 5;
    	
    	Integer matchedX = centerX - 320;
    	Integer matchedY = -(centerY - 240);
    	
    	
    	String message = "The coordinates of turtle are (" + matchedX.toString()
    			+ "," + matchedY.toString() + ")";
    	showMessage(message);
    	
    }
    protected void showMessage (String message) {
        myTextArea.append(message + "\n");
        myTextArea.setCaretPosition(myTextArea.getText().length());
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
				// TODO Auto-generated method stub
				//
				myAngle = (int) Math.toRadians(45);
	        	repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				return;
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				return;
			}
    	};
    }
    
    protected void makeKeyListener(){
    	myKeyListener = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent key) {
				// TODO Auto-generated method stub	
				checkKeys(key);
				//calVertices();
			}

			@Override
			public void keyReleased(KeyEvent key) {
			// TODO Auto-generated method stub	
				return;
			}
			

			@Override
			public void keyTyped(KeyEvent key) {
				// TODO Auto-generated method stub
				return;
			}
    		
    	};
    }
    
    protected void checkKeys(KeyEvent key){
    	if(key.getKeyCode() == KeyEvent.VK_LEFT){
    		centerX = centerX-5;
    		/*curP1.translate(-DELTA, 0);
        	curP2.translate(-DELTA, 0);
        	curP3.translate(-DELTA, 0);*/
        	repaint();
    	}
    	if(key.getKeyCode() == KeyEvent.VK_RIGHT){
    		centerX = centerX + 5;
    		/*curP1.translate(DELTA, 0);
        	curP2.translate(DELTA, 0);
        	curP3.translate(DELTA, 0);*/
        	repaint();
    	}
    	if(key.getKeyCode() == KeyEvent.VK_DOWN){
    		centerY = centerY+5;
    		/*curP1.translate(0,DELTA);
        	curP2.translate(0,DELTA);
        	curP3.translate(0,DELTA);*/
        	repaint();
    	}
    	if(key.getKeyCode() == KeyEvent.VK_UP){
    		centerY = centerY - 5;
    		/*curP1.translate(0,-DELTA);
        	curP2.translate(0,-DELTA);
        	curP3.translate(0,-DELTA);*/
        	repaint();
    	}
    }
    protected JComponent makePanel () {
    		JPanel panel = new JPanel();
    		//panel.add(makeButtonRotateR45());
    		panel.add(makeClear());
    		panel.add(makeDisplay());
    		return panel;
        }
    protected JComponent makeDisplay(){
    	myTextArea = new JTextArea(5, 30);
    	myTextArea.addKeyListener(myKeyListener);
    	//calVertices();
    	myTextArea.setEditable(false);
    	return new JScrollPane(myTextArea);
    }

    protected JButton makeButtonRotateR45(){
    	JButton right = new JButton("Right Rotate");
    	right.addMouseListener(myMouseListener);
    	
    	return right;
    }

    protected JButton makeClear () {
        JButton result = new JButton(("ClearCommand"));
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                myTextArea.setText("");
                centerX = 320; centerY = 240;
                repaint();
            }
        });
        return result;
    }
    protected void display() {
        JFrame f = new JFrame("LinePanel");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        makeKeyListener();
        makeMouseListener();
        f.add(this);
        f.add(makePanel(), BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TurtleDisplayPanel().display();
            }
        });
    }
}


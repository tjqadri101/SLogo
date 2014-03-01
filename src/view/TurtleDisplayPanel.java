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

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;


public class TurtleDisplayPanel extends JPanel {

    private Point p1 = new Point(310, 250);
    private Point p2 = new Point(330, 250);
    private Point p3 = new Point(320, 230);
    private boolean drawing;
    private MouseListener myMouseListener;
    private KeyListener myKeyListener;
    private static final int DELTA = 10;
    
    public TurtleDisplayPanel() {
        this.setPreferredSize(new Dimension(640, 480));
        this.setBackground(Color.white);
        //this.addMouseListener(myMouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.green);
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(4,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        int[] xPoints = new int[] {p1.x, p2.x, p3.x};
        int[] yPoints = new int[] {p1.y, p2.y, p3.y};
        g.drawPolygon(xPoints, yPoints, 3);
    }

   /* protected void makeMouseListener(){
    	myMouseListener = new MouseListener(){
	        @Override
	        public void mousePressed(MouseEvent e) {
	            drawing = true;
	            p1 = e.getPoint();
	            p2 = p1;
	            repaint();
	        }
	
	        @Override
	        public void mouseReleased(MouseEvent e) {
	            drawing = false;
	            p2 = e.getPoint();
	            repaint();
	        }

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
    	};
    }*/
    
    protected void makeKeyListener(){
    	myKeyListener = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent key) {
				// TODO Auto-generated method stub	
				checkKeys(key);
			}

			@Override
			public void keyReleased(KeyEvent key) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void keyTyped(KeyEvent key) {
				// TODO Auto-generated method stub
				
			}
    		
    	};
    }
    
    protected void checkKeys(KeyEvent key){
    	if(key.getKeyCode() == KeyEvent.VK_LEFT){
    		TurtleDisplayPanel.this.p1.translate(-DELTA, 0);
        	TurtleDisplayPanel.this.p2.translate(-DELTA, 0);
        	TurtleDisplayPanel.this.p3.translate(-DELTA, 0);
        	TurtleDisplayPanel.this.repaint();
    	}
    	if(key.getKeyCode() == KeyEvent.VK_RIGHT){
    		TurtleDisplayPanel.this.p1.translate(DELTA, 0);
        	TurtleDisplayPanel.this.p2.translate(DELTA, 0);
        	TurtleDisplayPanel.this.p3.translate(DELTA, 0);
        	TurtleDisplayPanel.this.repaint();
    	}
    	if(key.getKeyCode() == KeyEvent.VK_DOWN){
    		TurtleDisplayPanel.this.p1.translate(0,DELTA);
        	TurtleDisplayPanel.this.p2.translate(0,DELTA);
        	TurtleDisplayPanel.this.p3.translate(0,DELTA);
        	TurtleDisplayPanel.this.repaint();
    	}
    	if(key.getKeyCode() == KeyEvent.VK_UP){
    		TurtleDisplayPanel.this.p1.translate(0,-DELTA);
        	TurtleDisplayPanel.this.p2.translate(0,-DELTA);
        	TurtleDisplayPanel.this.p3.translate(0,-DELTA);
        	TurtleDisplayPanel.this.repaint();
    	}
    }
    protected JComponent makePanel () {
    		JPanel panel = new JPanel();
    		panel.add(makeTextArea());
    		return panel;
        }
    protected JTextArea makeTextArea(){
    	JTextArea textArea = new JTextArea(1, 1);
    	textArea.addKeyListener(myKeyListener);
    	textArea.setEditable(false);
    	return textArea;
    }

/*
    protected JButton makeClear () {
        JButton result = new JButton(("ClearCommand"));
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                myTextArea.setText("");
            }
        });
        return result;
    }*/
    protected void display() {
        JFrame f = new JFrame("LinePanel");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        makeKeyListener();
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
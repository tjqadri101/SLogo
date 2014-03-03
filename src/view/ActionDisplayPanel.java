package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import turtle.Turtle;

import test_nodes.ParserTest;
import turtle.Turtle;

//choose JPanel because this is more of a container
public class ActionDisplayPanel extends JPanel implements KeyListener{

	//Buttons for controlling the turtle and other miscelleneous actions
    private JButton moveTurtleLeft = new JButton("Left");
    private JButton moveTurtleRight = new JButton("Right");
    private JButton moveTurtleUp = new JButton("Up");
    private JButton moveTurtleDown = new JButton("Down");
    
    //Turtle display panel for displaying turtle and its movements
    private TurtleDisplayPanel turtleDisplayPanel;
    

//	ActionDisplayPanel(Integer width, Integer height, Double ratio, ParserTest myParser,Turtle t){
//		this.setPreferredSize(new Dimension((int) (width*ratio), height));
//		
//		turtleDisplayPanel = new TurtleDisplayPanel();
//    	turtleDisplayPanel.setFocusable(true);
//    	turtleDisplayPanel.requestFocusInWindow();
//        this.addKeyListener(this);

	ActionDisplayPanel(Integer width, Integer height, Double ratio, ParserTest myParser,JFrame f, Turtle t){
		this.setPreferredSize(new Dimension((int) (width*ratio), height));
	
		turtleDisplayPanel = new TurtleDisplayPanel();

		
		//turtleDisplayPanel.setAlignmentX(LEFT_ALIGNMENT);

		
		this.add(makeHyperLink("Go to basic commands page",
				"http://www.cs.duke.edu/courses/cps108/current/assign/03_slogo/commands.php",
				0,0),BorderLayout.SOUTH);
		this.add(turtleDisplayPanel,BorderLayout.EAST);
	}
	
	  public static JLabel makeHyperLink(final String s, final String link, int x, int y)
	  {
	      final JLabel l = new JLabel(s);
	      l.addMouseListener(new MouseAdapter()
	      {

	          @Override
	          public void mouseExited(MouseEvent arg0)
	          {
	              l.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	              l.setText(s);
	          }

	          @Override
	          public void mouseEntered(MouseEvent arg0)
	          {
	              l.setCursor(new Cursor(Cursor.HAND_CURSOR));
	              l.setText(String.format("<HTML><FONT color = \"#000099\"><U>%s</U></FONT></HTML>", s));
	          }

	          @Override
	          public void mouseClicked(MouseEvent arg0)
	          {
	              try
	              {
	                  URI uri = new URI(link);
	                  if (Desktop.isDesktopSupported())
	                      Desktop.getDesktop().browse(uri);
	              } catch (Exception e)
	              {
	              }
	          }
	      });
	      
	      l.setBounds(x, y, s.length()*5, 20);
	      l.setToolTipText(String.format("go to %s", link));
	      return l;
	      
	  }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

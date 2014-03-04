package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import test_nodes.ParserTest;
import turtle.Turtle;

//this class holds the logic for the main pieces of each window - each has separate parser (model) and text input 
public class WorkspacePanel extends JPanel {

	public static final Integer WIDTH = 1000;
	public static final Integer HEIGHT = 600;

	/**
	 * Interfacing from Frontend to Backend
	 */
	private ParserTest myParser = new ParserTest();
	private Turtle myTurtle = new Turtle(0, 0, 0);
	private CommandPanel myCommandPanel;
	private ActionDisplayPanel myActionDisplayPanel;
	
	public WorkspacePanel(JFrame f){
		myTurtle = new Turtle(320d, 240d, 0);
		myParser = new ParserTest();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(makeHyperLink("Go to basic commands page",
				"http://www.cs.duke.edu/courses/cps108/current/assign/03_slogo/commands.php",
				0,0));

		this.add(setAndMakeActionDisplay());
		this.add(setAndMakeCommandCenter());
		
		setFocusable(false);
	}

	private CommandPanel setAndMakeCommandCenter() {
		myCommandPanel = new CommandPanel(WIDTH, HEIGHT, .33, myParser, myTurtle);
		return myCommandPanel;
	}
	
	private ActionDisplayPanel setAndMakeActionDisplay(){
		myActionDisplayPanel = new ActionDisplayPanel(myTurtle);
		return myActionDisplayPanel;
	}
	
	private static JLabel makeHyperLink(final String s, final String link, int x, int y)
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
				} catch (Exception e){}
			}
		});

		l.setBounds(x, y, s.length()*5, 20);
		l.setToolTipText(String.format("go to %s", link));
		return l;
	}

}

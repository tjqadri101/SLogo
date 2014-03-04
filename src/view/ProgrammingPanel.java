package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import nodes.AbstractNode;

import test_nodes.ParserTest;
import turtle.Turtle;

//component that takes into account the users texts - chose JPanel because this is more of a container
public class ProgrammingPanel extends JPanel implements ActionListener {
	
	private static final Integer RATIO = 12;
	
	//text field class for displaying and storing user input
	private ScrollableTextArea userTextArea;
	private ScrollableJList instanceVars,functions;
	private JButton execute;
	private ExecutedCodePanel executedCode;
	private ParserTest parser;
	private Turtle turtle;
	
//	private JPanel userInputAndButton = new JPanel();
//	private JPanel userOutput = new JPanel();
    	
	public ProgrammingPanel(ParserTest myParser, Turtle t){
		instanceVars = new ScrollableJList(null);
		functions = new ScrollableJList(null);
		parser = myParser;
		turtle = t;
		//this.setLayout(new BorderLayout());

		//userTextPanel.addActionListener(this);
		this.setLayout(new GridBagLayout());
		//GridBagConstraints gbc = new GridBagConstraints();
		//Set the preferred size of the command and panel and add an embedded
		//text field panel where the user inputs are displayed
		//this.setBackground(Color.MAGENTA);
		//this.setPreferredSize(new Dimension((int) (ratio*width), height));
		userTextArea = new ScrollableTextArea(null);
		addBorderedComponent(0,0,1,1,1,1,userTextArea,"Code here!");		
		execute = new JButton("Execute!");
		addBorderedComponent(0,1,1,0,1,1,execute,"Click to run!");
		executedCode = new ExecutedCodePanel();
		addBorderedComponent(0,2,1,1,1,1,executedCode,"Previous executions:");
		addBorderedComponent(0,3,1,0,1,1,makeHyperLink("Go to basic commands page",
				"http://www.cs.duke.edu/courses/cps108/current/assign/03_slogo/commands.php",
				0,0),"Command reference:");
		addBorderedComponent(1,0,1,1,1,4,instanceVars,"Instance variables:");
		addBorderedComponent(2,0,1,1,1,4,functions,"Your functions:");
		
		revalidate();
		
		setFocusable(false);
		
		execute.addActionListener(this);
	}
	
	public void addBorderedComponent(int gridX,int gridY,double weightX,
			double weightY,int gridWidth,int gridHeight,JComponent jComponent,
			String title){
			JPanel jp = new JPanel(new BorderLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx=gridX;
			gbc.gridy=gridY;
			gbc.weightx=weightX;
			gbc.weighty=weightY;
			gbc.gridwidth=gridWidth;
			gbc.gridheight=gridHeight;
			jp.setBorder(
		            BorderFactory.createTitledBorder(
		                    BorderFactory.createEtchedBorder(
		                            EtchedBorder.RAISED, Color.GRAY
		                            , Color.DARK_GRAY), title));
			jp.add(jComponent,BorderLayout.CENTER);
			this.add(jp,gbc);
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

	//Called when the user clicks the execute button. Will pass the text in the text area to
	//the backend for parsing.
	public void actionPerformed(ActionEvent arg0) {
		executedCode.addToCodeList(userTextArea.getText());
		String input = userTextArea.getText();
		userTextArea.setText("");
		AbstractNode node = null;
		try {
			System.out.println(userTextArea.getText());
			node = parser.parseAndCreateTree(input, turtle);
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parser.traverseTree(turtle, node);
	}
}

package view;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.border.EtchedBorder;

public class ActionDisplayPanel extends JPanel{

	private TurtleDisplayPanel turtleDisplayPanel;
	private ScrollableTextArea myScrollableTextArea = new ScrollableTextArea(null);	

	public ActionDisplayPanel() {
		turtleDisplayPanel = new TurtleDisplayPanel();
		myScrollableTextArea.setEditable(false);
		this.setLayout(new GridBagLayout());

		addBorderedComponent(0,0,1,1,4, 2,turtleDisplayPanel,"Turtle display:");
		addBorderedComponent(0,2,0,0,1,1,makePenColorChooser_Toggle(),"Modify Pen Options");
		addBorderedComponent(1,2,0,0,1,1,makeButtonRotateR45(),"Rotate turtle:");
		addBorderedComponent(2,2,0,0,1,1,makeTurtleMovementButtons(),"Press to move turtle!");
		addBorderedComponent(3,2,0.1,0,1,1,makeClear(),"Reset");
		addBorderedComponent(0,3,0,0.1,3,2,myScrollableTextArea,"Turtle state:");
		addBorderedComponent(3,3,0.1,0,1,2,makeTurtle(),"Create");
		
		revalidate();
		repaint();
	}

	private void showMessage (String message) {
		myScrollableTextArea.append(message + "\n");
		myScrollableTextArea.setCaretPosition(myScrollableTextArea.getTextLength());
	}

	private void showState(){
		String messagePos = turtleDisplayPanel.getAllPositionInfos();
		//String messageAngle = "The turtle's heading is (" + turtleDisplayPanel.getAngle() + ")";
		showMessage(messagePos);
	}

	private JComponent makeButtonRotateR45(){
		JButton right = new JButton("Right Rotate");
		right.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				turtleDisplayPanel.rotateTurtlesRight();
				showState();
			}
		});
		return right;
	}
	private JComponent makeClear () {
		JButton clear = new JButton(("ClearCommand"));
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				myScrollableTextArea.setText("");
				turtleDisplayPanel.resetTurtle();
				showState();
			}
		});
		return clear;
	}

	private JComponent makeTurtle(){
		JButton create = new JButton("CreateTurtle");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.createNewTurtleImage();

			}
		});
		return create;
	}
	private JComponent makePenColorChooser_Toggle(){
		JPanel colorButtons = new JPanel(new BorderLayout());
		JButton colorChooser = new JButton("Choose a Pen color");
		colorChooser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e) {
				Color newColor = JColorChooser.showDialog(
						ActionDisplayPanel.this,
						"Choose Pen Color",
						turtleDisplayPanel.getColor());
				if (newColor != null) {
					turtleDisplayPanel.setColor(newColor);
				}
			}
		});
		JButton togglePen = new JButton("Pen Toggle");
		togglePen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				turtleDisplayPanel.setPenToggle();
				showState();
			}
		});
		
		colorButtons.add(togglePen,BorderLayout.WEST);
		colorButtons.add(colorChooser,BorderLayout.EAST);
		return colorButtons;

	}

	private JComponent makeTurtleMovementButtons(){
		JPanel buttons = new JPanel(new BorderLayout());
		JButton moveTurtleLeft = new JButton("Left");
		JButton moveTurtleRight = new JButton("Right");
		JButton moveTurtleForward = new JButton("Forward");
		JButton moveTurtleBack = new JButton("Downwards");
		moveTurtleLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleLeft();
				showState();
			}
		});

		moveTurtleRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleRight();
				showState();
			}
		});

		moveTurtleForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleForward();
				showState();
			}
		});

		moveTurtleBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleDown();
				showState();
			}
		});


		buttons.add(moveTurtleForward,BorderLayout.NORTH);
		buttons.add(moveTurtleBack,BorderLayout.SOUTH);
		buttons.add(moveTurtleLeft,BorderLayout.WEST);
		buttons.add(moveTurtleRight,BorderLayout.EAST);

		return buttons;

	}

	/*Used to add titled and bordered components in a grid LayoutManager
	to this panel*/
	private void addBorderedComponent(int gridX,int gridY,double weightX,
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
								, Color.BLUE), title));
		jp.add(jComponent,BorderLayout.CENTER);
		this.add(jp,gbc);
	}
}

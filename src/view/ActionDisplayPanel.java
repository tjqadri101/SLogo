package view;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.reflect.Method;

import javax.swing.border.EtchedBorder;

import main.SLogo;

public class ActionDisplayPanel extends JPanel{

	private TurtleDisplayPanel turtleDisplayPanel;
	private ScrollableTextArea myScrollableTextArea = new ScrollableTextArea(null);	

	public ActionDisplayPanel() {
		turtleDisplayPanel = new TurtleDisplayPanel();
		myScrollableTextArea.setEditable(false);
		this.setLayout(new GridBagLayout());

		addBorderedComponent(0,0,1,1,4,2,turtleDisplayPanel,"Turtle display:");
		addBorderedComponent(0,3,0,0.1,3,2,myScrollableTextArea,"Turtle state:");
		
		addBorderedComponent(0,2,0,0,1,1,makePenColorChooser_Toggle(),"Modify Pen Options");
		addBorderedComponent(2,2,0,0,1,1,makeTurtleMovementButtons(),"Press to move turtle!");
		
		addBorderedComponent(3,2,0.1,0,1,1,makeButton("Clear", new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				myScrollableTextArea.setText("");
				turtleDisplayPanel.resetTurtle();
				showState();
			}
		}),"Reset");
		
		addBorderedComponent(3,3,0.1,0,1,2,makeButton("Create Turtle", new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				turtleDisplayPanel.createNewTurtleImage();	
			}
		}),"Create");
		
		addBorderedComponent(1,2,0,0,1,1,makeButton("Right Rotate", new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				turtleDisplayPanel.rotateTurtlesRight();
				showState();
			}
		}),"Rotate turtle90");
		
		revalidate();
		repaint();
	}

	private void showMessage (String message) {
		myScrollableTextArea.append(message + "\n");
		myScrollableTextArea.setCaretPosition(myScrollableTextArea.getTextLength());
	}

	private void showState(){
		String messagePos = turtleDisplayPanel.getAllPositionInfos();
		showMessage(messagePos);
	}

	private JPanel makePenColorChooser_Toggle(){
		
		JPanel colorButtons = new JPanel(new BorderLayout());
		colorButtons.add(makeButton("Choose a Pen Color",new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(
						ActionDisplayPanel.this,
						"Choose Pen Color",
						turtleDisplayPanel.getColor());
				if (newColor != null) {
					turtleDisplayPanel.setColor(newColor);
				}
			}
		}),BorderLayout.EAST);

		colorButtons.add(makeButton("Pen Toggle", new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				turtleDisplayPanel.setPenToggle();
				showState();	
			}
		}),BorderLayout.WEST);
		
		return colorButtons;
	}

	private JPanel makeTurtleMovementButtons(){
		JPanel buttons = new JPanel(new BorderLayout());

		buttons.add(makeMovementButton("Right", "moveTurtleRight"), BorderLayout.EAST);
		buttons.add(makeMovementButton("Forward", "moveTurtleForward"), BorderLayout.NORTH);
		buttons.add(makeMovementButton("Left", "moveTurtleLeft"), BorderLayout.WEST);
		buttons.add(makeMovementButton("Downward", "moveTurtleDown"), BorderLayout.SOUTH);

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

	private JButton makeMovementButton(String label, String method){
		JButton b = new JButton(label);
		
		try {		
			final Method onClickMethod = TurtleDisplayPanel.class.getDeclaredMethod(method);
			b.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						onClickMethod.setAccessible(true);
						onClickMethod.invoke(getInstance());
						onClickMethod.setAccessible(false);
					}
					
					catch (Exception e1) {} 
				}
			});
			
		} catch (Exception e1) {}
		return b;
	}
	
	private TurtleDisplayPanel getInstance(){
		return turtleDisplayPanel;
	}
	
	private JButton makeButton(String label, ActionListener l){
		JButton b = new JButton(label);
		b.addActionListener(l);
		return b;
	}

}

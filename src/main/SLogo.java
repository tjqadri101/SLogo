package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import view.ColorMenuComponent;
import view.WorkspacePanel;

public class SLogo extends JFrame {

	//List of workspaces on the View
	private List<WorkspacePanel> workspacePanels;
	private JTabbedPane workspaces;
	private int workspaceCount=1;
	private List<Preferences> preferences;
	private List<Preferences> savedPreferences;

	public SLogo(){
		super("SLogo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1024, 800));

		JMenuBar menuBar = buildMenuBar();
		setJMenuBar(menuBar);

		workspacePanels = new ArrayList<WorkspacePanel>();
		workspaces = new JTabbedPane();
		addNewWorkspace();
		add(workspaces);

		setDefaultPreference();

		validate();
		pack();
		repaint();

		setVisible(true);
	}

	private void setDefaultPreference() {
		//Preferences defaultPref = = Preferences.userRoot().node(GENERAL_LOOK_NODE);
	}

	private JMenuBar buildMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		fileMenu.add(makeMenuItem("New Workspace", "addNewWorkspace"));
		fileMenu.add(makeMenuItem("Open", null));
		fileMenu.add(makeMenuItem("Save Preferences", "savePreferences"));
		
		fileMenu.add(createColorsMenu());
		return menuBar;
	}
	
	private JComponent createColorsMenu(){

		JMenu colors = new JMenu("Choose background Color");
		for (final ColorMenuComponent color : ColorMenuComponent.values()){
			
			JMenuItem menuItemToAdd = makeColorMenuItem(color.name(), new ActionListener(){	
				@Override
				public void actionPerformed(ActionEvent e) {
					workspaces.getSelectedComponent().setBackground(color.getColor());	
				}
			});
			
			colors.add(menuItemToAdd);
		}
		return colors;
	}

	public void addNewWorkspace(){
		WorkspacePanel temp = new WorkspacePanel();
		workspaces.add("workspace "+workspaceCount,temp);
		workspaceCount++;
		workspacePanels.add(temp);
	}

	@SuppressWarnings("static-access")
	private void savePreferences(){
		JOptionPane askForPreferenceName = new JOptionPane();
		askForPreferenceName.showInputDialog("Enter preference name");
		//savedPreferences.add()
	}

	public static void main(String[] args) {
		SLogo mySLogo = new SLogo();
		//createAndShowMainWindow();
	}
	
	public JMenuItem makeMenuItem(String label, String method){
		JMenuItem result = new JMenuItem(label);
		try {		
			final Method add = SLogo.class.getDeclaredMethod(method);
			result.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						add.invoke(getInstance());
					} 	
					catch (Exception e1) {e1.printStackTrace();} 
				}
			});
	
		} catch (Exception e1) {e1.printStackTrace();}
				
		return result;
	}
	
	private SLogo getInstance(){
		return this;
	}
	
	public JMenuItem makeColorMenuItem(String label, ActionListener listener){
		JMenuItem result = new JMenuItem(label);
		result.addActionListener(listener);
		return result;
		
	}
	
}
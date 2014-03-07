package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		JMenuItem newMenu = new JMenuItem("New Workspace");
        JMenuItem openMenu = new JMenuItem("Open  (Ctrl+O)");
        JMenuItem saveMenu = new JMenuItem("Save  Preferences");
        saveMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	savePreferences();
            }
        });
        newMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewWorkspace();
            }
        });
        openMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        fileMenu.add(newMenu);
        fileMenu.add(openMenu);
        fileMenu.add(saveMenu);
        fileMenu.add(createColorsMenu());
		return menuBar;
	}
	
	private JComponent createColorsMenu(){
        
        JMenu colors = new JMenu("Choose background Color");
        JMenuItem black = new JMenuItem("black");
        JMenuItem green = new JMenuItem("green");
        JMenuItem yellow = new JMenuItem("yellow");
        JMenuItem blue = new JMenuItem("blue");
        JMenuItem red = new JMenuItem("red");
        
        black.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	workspaces.getSelectedComponent().setBackground(Color.BLACK);
            }
        });
        green.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	workspaces.getSelectedComponent().setBackground(Color.GREEN);
            }
        });
        yellow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	workspaces.getSelectedComponent().setBackground(Color.YELLOW);
            }
        });
        blue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	workspaces.getSelectedComponent().setBackground(Color.BLUE);
            }
        });
        red.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	workspaces.getSelectedComponent().setBackground(Color.RED);
            }
        });
        
        colors.add(black);
        colors.add(yellow);
        colors.add(blue);
        colors.add(green);
        colors.add(red);
        
        return colors;
	}
	
	private void addNewWorkspace(){
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
}
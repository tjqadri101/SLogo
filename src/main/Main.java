package main;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.WorkspacePanel;

public class Main {

	private static JTabbedPane workspaces;
	private static int workspaceCount;
	private static JFrame frame;
	
	private static void createAndShowMainWindow() {
		frame = new JFrame("SLOGO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1024, 800));
		
		JMenuBar menuBar = buildMenuBar();
		frame.setJMenuBar(menuBar);
		
		workspaceCount=1;
		workspaces = new JTabbedPane();
		Container initialWorkspace = new WorkspacePanel(frame);
		workspaces.add("workspace 1",initialWorkspace);
		workspaceCount++;
		frame.add(workspaces);

		frame.validate();
		frame.pack();
		frame.repaint();
	   
		frame.setVisible(true);
	}

	private static JMenuBar buildMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenu = new JMenuItem("New Workspace");
        JMenuItem openMenu = new JMenuItem("Open  (Ctrl+O)");
        JMenuItem saveMenu = new JMenuItem("Save  (Ctrl+S)");
        saveMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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
		return menuBar;
	}

	public static void main(String[] args) {
		createAndShowMainWindow();
    }
	
	public static void addNewWorkspace(){
		workspaces.add("workspace "+workspaceCount,new WorkspacePanel(frame));
		workspaceCount++;
	}
}

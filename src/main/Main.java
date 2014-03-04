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

import view.WorkspacePanel;

public class Main {

	private static void createAndShowMainWindow() {
		JMenuBar menuBar = buildMenuBar();
		JFrame frame = new JFrame("SLOGO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(menuBar);
		Container mainWindow = new WorkspacePanel(frame);
		frame.add(mainWindow);

		frame.validate();
		frame.pack();
		frame.repaint();
	   
		frame.setVisible(true);
	}

	private static JMenuBar buildMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenu = new JMenuItem("New  (Ctrl+N)");
        JMenuItem openMenu = new JMenuItem("Open  (Ctrl+O)");
        JMenuItem saveMenu = new JMenuItem("Save  (Ctrl+S)");
        saveMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        newMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        openMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

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
}

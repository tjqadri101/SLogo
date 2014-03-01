package main;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import view.WorkspacePanel;

public class Main {

	private static void createAndShowMainWindow() {
		
		JFrame frame = new JFrame("SLOGO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container mainWindow = new WorkspacePanel();
		frame.add(mainWindow);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		createAndShowMainWindow();
    }
}

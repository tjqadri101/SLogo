package view;

//SimpleFileChooser.java
//A simple file chooser to see what it takes to make one of these work.
//
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFrame {

	private String turtleString;

	public FileChooser(final String type) {

		super("SLogo: Choose Turtle Image");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JButton openButton;
		openButton = new JButton("Select Turtle");

		JButton openButtonFinish = new JButton("Done");
		final JLabel statusbar = new JLabel("Select Image File");

		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPEG", "JPG", "GIF", "BMP", "PNG");
				chooser.setFileFilter(filter);

				int option = chooser.showOpenDialog(FileChooser.this);
				
				if (option == JFileChooser.APPROVE_OPTION) {
					
					File sf = chooser.getSelectedFile();
					turtleString = sf.getName();
					statusbar.setText("You chose " + sf.getName());
				}

			}

		});

		openButtonFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// TODO Transfer image to view.
				
				
				close();
			}
		});

		c.add(openButton);
		c.add(openButtonFinish);
		c.add(statusbar);

	}

	public void close() {
		this.setVisible(false);
		this.dispose();
	}

}
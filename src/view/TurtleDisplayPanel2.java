//This class is responsible for displaying the turtle along with showing its movements
//via updates from the user

package view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TurtleDisplayPanel2 extends JPanel {


	private Graphics2D g2d;
	private BufferedImage displayTurtle;

	TurtleImage turtlePic = new TurtleImage();

	public TurtleDisplayPanel2() {
		this.setPreferredSize(new Dimension(640, 480));
		this.setBackground(Color.white);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g2d = (Graphics2D) g;

		/**
		 * Benson to Talal: Turtle display properties are implemented in
		 * TurtleImage, which essentially is the "backend" to this.
		 */

		turtlePic.paintCenter(g2d, displayTurtle);

	}

	protected void display() {

		/**
		 * Benson to Talal: Add turtle here
		 */

		try {
			displayTurtle = turtlePic.setImage();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JFrame f = new JFrame("LinePanel");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				new TurtleDisplayPanel2().display();
			}
		});
	}
}

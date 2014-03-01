package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class TurtleView extends JPanel {

    private MouseHandler mouseHandler = new MouseHandler();
    private Point p1 = new Point(310, 250);
    private Point p2 = new Point(330, 250);
    private Point p3 = new Point(320, 230);
    private boolean drawing;

    public TurtleView() {
        this.setPreferredSize(new Dimension(640, 480));
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.green);
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(8,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        //g.drawLine(p1.x, p1.y, p2.x, p2.y);
        int[] xPoints = new int[] {p1.x, p2.x, p3.x};
        int[] yPoints = new int[] {p1.y, p2.y, p3.y};
        g.drawPolygon(xPoints, yPoints, 3);
    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            drawing = true;
            p1 = e.getPoint();
            p2 = p1;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            drawing = false;
            p2 = e.getPoint();
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (drawing) {
                p2 = e.getPoint();
                repaint();
            }
        }
    }

    private class ControlPanel extends JPanel {

        private static final int DELTA = 10;

        public ControlPanel() {
            this.add(new MoveButton("\u2190", KeyEvent.VK_LEFT, -DELTA, 0));
            this.add(new MoveButton("\u2191", KeyEvent.VK_UP, 0, -DELTA));
            this.add(new MoveButton("\u2192", KeyEvent.VK_RIGHT, DELTA, 0));
            this.add(new MoveButton("\u2193", KeyEvent.VK_DOWN, 0, DELTA));
        }

        private class MoveButton extends JButton {

            KeyStroke k;
            int dx, dy;

            public MoveButton(String name, int code, final int dx, final int dy) {
                super(name);
                this.k = KeyStroke.getKeyStroke(code, 0);
                this.dx = dx;
                this.dy = dy;
                this.setAction(new AbstractAction(this.getText()) {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	TurtleView.this.p1.translate(dx, dy);
                    	TurtleView.this.p2.translate(dx, dy);
                    	TurtleView.this.repaint();
                    }
                });
                ControlPanel.this.getInputMap(
                    WHEN_IN_FOCUSED_WINDOW).put(k, k.toString());
                ControlPanel.this.getActionMap().put(k.toString(), new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MoveButton.this.doClick();
                    }
                });
            }
        }
    }

    private void display() {
        JFrame f = new JFrame("LinePanel");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.add(new ControlPanel(), BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TurtleView().display();
            }
        });
    }
}
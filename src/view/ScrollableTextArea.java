package view;

import java.awt.event.KeyListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//Sets up a JScrollPane surrounding a text area
public class ScrollableTextArea extends JScrollPane{
	private JTextArea myTextArea;
	private int myWidth,myHeight;
	private KeyListener myKeyListener;
	
	public ScrollableTextArea(int width,int height,KeyListener listener){
		myWidth = width;
		myHeight = height;
		myKeyListener = listener;
    	myTextArea = new JTextArea(myWidth, myHeight);
    	myTextArea.addKeyListener(myKeyListener);
		this.setViewportView(myTextArea);
	}
	
	public void setCaretPosition(int pos){
		myTextArea.setCaretPosition(pos);
	}
	
	public void append(String s){
		myTextArea.append(s);
	}
	
	public void setText(String s){
		myTextArea.setText(s);
	}
	
	public String getText(){
		return myTextArea.getText();
	}
	
	public int getTextLength(){
		return myTextArea.getText().length();
	}
	
	public void setEditable(){
		myTextArea.setEditable(false);
	}

}

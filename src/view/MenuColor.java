package view;

import java.awt.Color;

public enum MenuColor {
	Red(Color.RED),
	Black(Color.BLACK),
	Green(Color.GREEN),
	Yellow(Color.YELLOW);
	
	private final Color myColor;
	MenuColor(Color color){
		myColor = color;
	}
	
	public Color getColor(){
		return myColor;
	}
}

package controller;

import mvcutils.AbstractController;

public class TurtleController extends AbstractController{
	public static final String XPOSITION_PROPERTY = "X Position";
	public static final String YPOSITION_PROPERTY = "Y Position";
	public static final String ANGLE_PROPERTY = "Angle";
	
	public TurtleController(){
	}
	
	public void changeFirstName(String newValue){
		 setModelProperty(XPOSITION_PROPERTY, newValue);
	}

	public void changeSurname(String newValue){
	 setModelProperty(YPOSITION_PROPERTY, newValue);
	}

	public void changeAge(String newValue){
	 Integer value = Integer.parseInt(newValue);
	 setModelProperty(ANGLE_PROPERTY, value);
	}	
}

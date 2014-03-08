package controller;

import java.util.List;
import java.util.Map;

import model.Model;
import turtle.Turtle;

public class ModelController extends AbstractController {

	/*
	 * Setting up Communication between Controller and Model
	 */

	public static final String MODEL = "Model";
	
	private String myCommand;
	private String myLanguage;
	private List<Turtle> myTurtles;

	public ModelController() {
	}
	
	public void setCommand(String command){
		myCommand = command;
	}
	
	public void setLanguage(String language){
		myLanguage = language;
	}
	
	public void setTurtles(List<Turtle> turtles){
		myTurtles = turtles;
	}

	public void passToModel(List<Turtle> turtleMap,
			String commandMap, String languageMap) {

		setModelProperty(turtleMap, commandMap, languageMap);

	}

	public void addModel(Model m) {
		// TODO Auto-generated method stub
		
	}

}

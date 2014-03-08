package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Model;
import turtle.ITurtle;
import turtle.Turtle;
import turtle_graphics.TurtleImage;

public class ModelController extends AbstractController {

	/*
	 * Setting up Communication between Controller and Model
	 */

	
	private final String DEFAULT_LANGUAGE = "English";
	public static final String MODEL = "Model";


	public ModelController() {
	}
	
	public List<ITurtle> convertImagetoITurtle(List<TurtleImage> turtleImages){
		
		List<ITurtle> iTurtles = new ArrayList<ITurtle>();
		
		for(TurtleImage ti : turtleImages){
			iTurtles.add(ti);
		}		
		return iTurtles;
	}
	
	
	public void passToEnglishModel(List<TurtleImage> turtles, String command){

		passToModel(turtles, command, DEFAULT_LANGUAGE);
	}


	public void passToModel(List<TurtleImage> turtles,
			String command, String language) {

		List<Turtle> backendTurtles = new ArrayList<Turtle>();
		
		for(ITurtle it : convertImagetoITurtle(turtles)){
			backendTurtles.add(new Turtle(it));
		}
		setModelProperty(backendTurtles, command, language);

	}

	public void addModel(Model m) {
		// TODO Auto-generated method stub
		
	}

}

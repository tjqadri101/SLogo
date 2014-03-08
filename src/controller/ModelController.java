package controller;

import java.util.List;
import java.util.Map;

import turtle.Turtle;

public class ModelController extends AbstractController {

	/*
	 * Setting up Communication between Controller and Model
	 */

	public static final String MODEL = "Model";

	public ModelController() {
	}

	public void passToModel(Map<String, List<Turtle>> turtleMap,
			Map<String, String> commandMap, Map<String, String> languageMap) {

		setModelProperty(turtleMap, commandMap, languageMap);

	}

}

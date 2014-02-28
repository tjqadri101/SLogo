package nodes;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import parse.CommandReader;
import turtle.Turtle;

public class NodeFactory {

	private Turtle myTurtle;
	private final String COMMAND_LIST = "Commands.Properties";
	private final String[] PACKAGES = { "nodes.booleannodes.",
			"nodes.commandnodes.", "nodes.controlnodes.", "nodes." };

	public NodeFactory(Turtle turtle) {
		myTurtle = turtle;
	}

	public AbstractNode createNode(String word) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException {

		AbstractNode genericNode = null;

		Map<String, String> commandsMap = CommandReader
				.readCommands(COMMAND_LIST);

		if (!isNumeric(word)) {

			String command = commandsMap.get(word.toUpperCase());
			Class<?> c = Class.forName(findClass(command));

			Constructor<?> constructor = c.getConstructor(Turtle.class);
			genericNode = (AbstractNode) constructor.newInstance(myTurtle);

		}

		else if (isNumeric(word)) {
			return new NumberNode(myTurtle, Double.parseDouble(word));
		}

		return genericNode;

	}

	private String findClass(String command) {
		for (String s : PACKAGES) {
			if (classExists(s + command + "Node")) {
				String classLocation = s + command + "Node";
				return classLocation;
			}
		}
		return null;
	}

	private boolean classExists(String className) {
		try {
			Class.forName(className);

			return true;
		} catch (ClassNotFoundException ex) {
			return false;
		}
	}

	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}

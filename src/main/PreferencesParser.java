package main;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import jboxGlue.WorldManager;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PreferencesParser {
//	public void doParse(String path){
//		try {
//			NodeList root = buildDocument(path);
//			NodeList model = getNode("model", root).getChildNodes();
//			NodeList nodes = getNode("nodes", model).getChildNodes();
//			NodeList links = getNode("links", model).getChildNodes();
//			constructMasses(nodes);
//			constructSpringsAndMuscles(links);
//			WorldManager.getWorld().addMasses(getMassList());
//			WorldManager.getWorld().addSprings(getSpringList());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	protected Node getNode(String fieldName, NodeList nodes) {
//		for (int x = 0; x < nodes.getLength(); x++) {
//			Node node = nodes.item(x);
//			if (node.getNodeName().equalsIgnoreCase(fieldName)) {
//				return node;
//			}
//		}
//
//		return null;
//	}
//	
//	private NodeList buildDocument(String docToBuild){
//		try{
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			DocumentBuilder db = dbf.newDocumentBuilder(); 
//			Document doc = db.parse(new File(docToBuild));
//			return doc.getChildNodes();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	try {
//		NodeList root = buildDocument(path);
//		NodeList model = getNode("model", root).getChildNodes();
//		NodeList nodes = getNode("nodes", model).getChildNodes();
//		NodeList links = getNode("links", model).getChildNodes();
//		constructMasses(nodes);
//		constructSpringsAndMuscles(links);
//		WorldManager.getWorld().addMasses(getMassList());
//		WorldManager.getWorld().addSprings(getSpringList());
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
}

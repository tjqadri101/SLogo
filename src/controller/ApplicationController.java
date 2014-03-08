//package controller;
//
//import model.Model;
//
//public class ApplicationController extends AbstractController {
//	
//	
//
//	public void showView(Object selectedValue) {
//		
//		// construct the MVC binding
//		
//		Model m = (Model) selectedValue;
//		ModelController modelController = new ModelController();
//		ProgrammingPanel pView = new ProgammingPanel(Turtle t,modelController);
//		
//		modelController.addModel(m);
//		modelController.addView(pView);
//
//		// show the view
//		pView.setVisible(true);
//
//		// display initial properties on view by firing the property actions
//		pView.fireInitialProperties();
//	}
//}
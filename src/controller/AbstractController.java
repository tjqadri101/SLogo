package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

import turtle.AbstractModel;
import view.IView;

public abstract class AbstractController implements PropertyChangeListener {

	   private ArrayList<IView> registeredViews;
	   private ArrayList<AbstractModel> registeredModels;

	   public AbstractController() {
	       registeredViews = new ArrayList<IView>();
	       registeredModels = new ArrayList<AbstractModel>();
	   }


	   public void propertyChange(PropertyChangeEvent evt) {

	       for (IView view: registeredViews) {
	           view.modelPropertyChange(evt);
	       }
	   }

	   protected void setModelProperty(String propertyName, Object newValue) {

	       for (AbstractModel model: registeredModels) {
	           try {

	               Method method = model.getClass().
	                   getMethod("set"+propertyName, new Class[] {
	                                                     newValue.getClass()
	                                                 }


	                            );
	               method.invoke(model, newValue);

	           } catch (Exception ex) {
	               //  Handle exception.
	           }
	       }
	   }
	}
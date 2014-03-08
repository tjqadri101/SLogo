package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Model;
import turtle.Turtle;
import view.IView;

public abstract class AbstractController implements PropertyChangeListener {

	private ArrayList<IView> registeredViews;
	private ArrayList<Model> registeredModels;

	public AbstractController() {
		registeredViews = new ArrayList<IView>();
		registeredModels = new ArrayList<Model>();
	}

	public void propertyChange(PropertyChangeEvent evt) {

		for (IView view : registeredViews) {
			view.modelPropertyChange(evt);
		}
	}

	public void setModelProperty(Map<String, List<Turtle>> newValue1,
			Map<String, String> newValue2, Map<String, String> newValue3) {

		Object[] parameters = { newValue1, newValue2, newValue3 };

		for (Model model : registeredModels) {
			try {

				Method method = model.getClass().getMethod(
						"processCommands",
						new Class[] { newValue1.getClass(),
								newValue2.getClass(), newValue3.getClass() }
				// Check line 37 for errors!

						);
				method.invoke(model, parameters);

			} catch (Exception ex) {
				// Handle exception.
			}
		}
	}
}
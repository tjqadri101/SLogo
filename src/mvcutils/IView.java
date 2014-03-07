package mvcutils;

import java.beans.PropertyChangeEvent;

//Implemented by the views
public interface IView {
public void modelPropertyChange(PropertyChangeEvent evt);
}
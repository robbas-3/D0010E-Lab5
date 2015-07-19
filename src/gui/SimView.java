package gui;

import java.util.Observable;
import java.util.Observer;

import event.Event;
import model.SimState;

/**
 * 
 * abstract class that represents the view.
 *
 */
public abstract class SimView implements Observer {

	protected SimState state;

	/**
	 * The constructor.
	 * 
	 * @param state
	 */
	public SimView(SimState state) {
		this.state = state;
		state.addObserver(this);
	}

	/**
	 * Not defined update method.
	 */
	public void update(Observable o, Object arg) {

	}

	/**
	 * Method for displaying the object.
	 * 
	 * @param o
	 *            object.
	 */
	protected void write(Object o) {
		System.out.println(o);
	}

	/**
	 * Method for using a formatted string.
	 * 
	 * @param str
	 *            string
	 * @param args
	 *            objects
	 */
	protected void write(String str, Object... args) {
		System.out.println(String.format(str, args));
	}
}

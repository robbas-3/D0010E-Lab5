package model;

import java.util.ArrayList;
import java.util.Observable;

import event.Event;

/**
 * 
 * Abstract class simstate that extends observable
 *
 */
public abstract class SimState extends Observable {

	private double time;
	private Event currentEvent;

	/**
	 * constructor sets the time
	 */
	public SimState() {
		time = System.currentTimeMillis();
	}

	/**
	 * gets the current time
	 * 
	 * @return value of the current time
	 */
	public double getTime() {
		return (System.currentTimeMillis() - time) / 1000;
	}

	/**
	 * getting the current event that we are in
	 * 
	 * @return returns that event
	 */
	public Event getCurrentEvent() {
		return currentEvent;
	}

	/**
	 * an method for updates
	 */
	public void end() {
		setChanged();
		notifyObservers();
	}

	/**
	 * sets event to desired event
	 * 
	 * @param event
	 *            the event that is set.
	 */
	public void setEvent(Event event) {
		this.currentEvent = event;
	}

}

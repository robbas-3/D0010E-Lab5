package event;

import model.SimState;

/**
 * Abstract class Event which implements comparable
 */
public abstract class Event implements Comparable<Event> {

	private String name;
	private double eventTime;

	/**
	 * The constructor
	 * 
	 * @param time
	 *            time of the event
	 * @param s
	 *            name of the event
	 */
	public Event(double time, String s) {
		this.eventTime = time;
		this.name = s;
	}

	/**
	 * Method to get the eventTime
	 * 
	 * @return returns the event time.
	 */
	public double getEventTime() {
		return eventTime;
	}

	/**
	 * An abstract method which will execute an event.
	 * 
	 * @param state
	 *            sending state so it can be changed accordingly.
	 * @param eventQueue
	 *            making the event occur and creates a future event
	 * @return
	 */
	public abstract void execEvent(SimState state, EventQueue eventQueue);

	/**
	 * Method to create a new event and adding it to the sequence of events
	 * 
	 * @param sortedSequence
	 *            sending sortedSeq so it can be updated with the newly created
	 *            event
	 */
	public void createNewEvent(SortedSequence sortedSequence, Event event) {
		sortedSequence.addNsort(event);
	}

	/**
	 * Abstract method for creating a future event which will need to be
	 * concrete in the specific events.
	 * 
	 * @param event
	 *            the future event
	 * @param eventQueue
	 *            queue of events
	 */
	public abstract void createNextEvent(Event event, EventQueue eventQueue);

	/**
	 * Method to get the name of an event
	 * 
	 * @return returning the private name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Method to compare if this events time value is the same,smaller or bigger
	 * than another event If the time is equal we return 0. If this event time
	 * is lower than the other event we return 1. If this event time is bigger
	 * than the other event we return -1.
	 */
	public int compareTo(Event otherEvent) {
		if (this.eventTime == otherEvent.eventTime) {
			return 0;
		} else if (this.eventTime < otherEvent.eventTime) {
			return 1;
		} else {
			return -1;
		}
	}
}

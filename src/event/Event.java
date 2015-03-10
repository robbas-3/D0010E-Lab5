package event;

import model.SimState;

//import states.SimState;

/*
 * Skapar bland annat event som skickas till sorted Seq som sorterar den
 * denna sorterade sekvens sparas sedan i eventQueue
 */
public abstract class Event implements Comparable <Event> {
	
	//public SimState state;
	private SortedSequence sortedSequence;
	private String name;
	public double eventTime;

	public Event(double time, String s) {
		this.eventTime = time;
		this.name = s;
	}

	public double getEventTime() {
		return eventTime;
	}
	/**
	 * 
	 * @param state
	 *            sending state so it can be changed accordingly.
	 * @param eventQueue
	 *            making it occur and jumps to next one
	 * @return
	 */
	public void execEvent(SimState state,EventQueue eventQueue){
		
	}

	// hmm gör något kul här
	/**
	 * 
	 * @param sortedSequence
	 *            sending sortedSeq so it can be updated with the newly created
	 *            event
	 */
	public void createNewEvent(SortedSequence sortedSequence, Event event) {
		this.sortedSequence.addNsort(event);
	}
	public void createNextEvent(double time){
		//Event newEvent = new Event(time);
	}

	/**
	 * 
	 * @return returning the private name
	 */
	public String getName() {
		return this.name;
	}
	
	@Override
	public int compareTo(Event otherEvent) {
		if(this.eventTime==otherEvent.eventTime){
			return 0;
		}
		else if(this.eventTime<otherEvent.eventTime){
			return 1;
		}
		else {
			return -1;
		}
	}
}

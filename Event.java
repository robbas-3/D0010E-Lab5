package event;

//import states.SimState;

/*
 * Skapar bland annat event som skickas till sorted Seq som sorterar den
 * denna sorterade sekvens sparas sedan i eventQueue
 */
public class Event implements Comparable <Event> {
	
	//public SimState state;
	private SortedSequence sortedSequence;
	private String name;
	public double eventTime;

	public Event(double time,String s) {
		eventTime = time;
		name = s;
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
	public void execEvent( EventQueue eventQueue) {
	}

	// hmm gör något kul här
	/**
	 * 
	 * @param sortedSequence
	 *            sending sortedSeq so it can be updated with the newly created
	 *            event
	 */
	public void createNewEvent(SortedSequence sortedSequence, Event event) {
		sortedSequence.addNsort(event);
	}

	/**
	 * 
	 * @return returning the private name
	 */
	public String getName() {
		return this.name;
	}

	/**

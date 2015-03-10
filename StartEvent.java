package event;

/**
 * StartEvent is an Event that starts the simulation.
 * 
 * @author Robin
 * 
 */
public class StartEvent extends Event {
	/**
	 * Skuggning
	 * 
	 * @param state
	 *            sending state so it can be changed accordingly.
	 * @param eventQueue
	 *            making it occur and jumps to next one
	 */
	public StartEvent(double time) {
		super(time,"Start");
	}

	public void execEvent(SimState state, EventQueue eventQueue) {
		// hmm gör något kul här
		state.start();
		eventQueue.next(); // hoppar till nästa??
	}
}

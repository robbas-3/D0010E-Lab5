package event;
import simulator.Simulator;
import model.SimState;

/**
 * StartEvent is an Event that starts the simulation.
 * 
 * @author Robin
 * 
 */
public class StartEvent extends Event {
	private Simulator simulator;
	/**
	 * Skuggning
	 * 
	 * @param state
	 *            sending state so it can be changed accordingly.
	 * @param eventQueue
	 *            making it occur and jumps to next one
	 */
	public StartEvent(double time,String s) {
		super(time,s);
	}

	@Override
	public void execEvent(SimState state,EventQueue eventQueue) {
		// hmm gör något kul här
		simulator.start();
		eventQueue.next(); // hoppar till nästa??
		
	}

	@Override
	public void createNextEvent(double time, Event event) {
		// TODO Auto-generated method stub
		
	}
}

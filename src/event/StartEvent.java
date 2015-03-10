package event;
import model.CarWashState;
import model.SimState;
import simulator.Simulator;

/**
 * StartEvent is an Event that starts the simulation.
 * 
 * @author Robin
 * 
 */
public class StartEvent extends Event {
	private Simulator simulator;
	private EventQueue eventQueue;
	private CarWashState cWS;
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


	public void execEvent(SimState state,EventQueue eventQueue) {
		// hmm gör något kul här
		simulator.start();
		state.setEvent(this);
		createNextEvent(cWS.arrivalTime(),new ArriveEvent(cWS.arrivalTime(),"Arrive",eventQueue,cWS));
		
	}

	
	public void createNextEvent(double time, Event event) {
		
		createNewEvent(eventQueue.getSortedSequence(),event);
	}
}

package event;

import model.CarWashState;
import model.SimState;
import simulator.Simulator;

/**
 * StartEvent is an Event that starts the simulation.
 */
public class StartEvent extends Event {
	private Simulator simulator;
	private CarWashState cWS;
	
	/**
	 * Constructor
	 * 
	 * @param time
	 *            time to start the simulation
	 * 
	 * @param s
	 *            name of the event
	 * @param simulator
	 *            which simulator we want to start.
	 * 
	 */
	public StartEvent(double time, String s, Simulator simulator) {
		super(time, s);
		this.simulator = simulator;
		
	}

	/**
	 * the execute method for start event, changning it to start, starts the
	 * simulation.
	 */
	public void execEvent(SimState state, EventQueue eventQueue) {
		state.setEvent(this);
		cWS = (CarWashState) state;
		simulator.start();
		createNextEvent(new ArriveEvent(cWS.arrivalTime(),"Arrive",eventQueue,cWS),eventQueue);

	}

	/**
	 * 
	 */
	public void createNextEvent(Event event, EventQueue eventQueue) {

		createNewEvent(eventQueue.getSortedSequence(), event);
	}
}

package event;

import model.SimState;
import simulator.Simulator;

/**
 * Stopevent is a generalEvent which stops the simulation.
 * 
 * @author Robin
 * 
 */
public class StopEvent extends Event {
	private Simulator simulator;
	public StopEvent(double time, String s,Simulator simulator) {
		super(time,s);
		this.simulator = simulator;
	}

	/**
	 * 
	 * @param state
	 *            changing state to stop, halts the simulation.
	 * @param eventQueue
	 *            clearing eventQueue from all future events.
	 */
	
	@Override
	public void execEvent(SimState state, EventQueue eventQueue) {
	
		state.setEvent(this);
		eventQueue.getSortedSequence().clearSeq();
		state.end();
		simulator.stop();
		
	}

	@Override
	public void createNextEvent(Event event,EventQueue eventQueue) {
		
		
	}
}

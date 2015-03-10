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
	public StopEvent(double time, String s) {
		super(time,s);
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
		// hmm gör något kul här
		simulator.stop();
		eventQueue.getSortedSequence().clearSeq();
		// behöver utvecklas mer??
		
	}

	@Override
	public void createNextEvent(double time, Event event) {
		// TODO Auto-generated method stub
		
	}
}

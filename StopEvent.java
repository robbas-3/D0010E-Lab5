package evvent;

import lists.EventQueue;
import states.SimState;
/**
 * Stopevent is a generalEvent which stops the simulation.
 * @author Robin
 *
 */
public class StopEvent extends Event {
	
	public StopEvent(double time){
		super(time);
	}
	/**
	 * 
	 * @param state changing state to stop, halts the simulation.
	 * @param eventQueue clearing eventQueue from all future events.
	 */
	public void execEvent(SimState state,EventQueue eventQueue){
		// hmm gör något kul här
		state.stop();
		eventQueue.getSortedSequence().clearSeq();
		 // behöver utvecklas mer??
		
	}
}

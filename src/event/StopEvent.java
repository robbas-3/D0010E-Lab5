package event;

import model.SimState;
import simulator.Simulator;

/**
 * Event that stops the simulation.
 */
public class StopEvent extends Event {
	private Simulator simulator;

	public StopEvent(double time, String s, Simulator simulator) {
		super(time, s);
		this.simulator = simulator;
	}

	/**
	 * execute the stop event.
	 * 
	 * @param state
	 *            changing state to stop, halts the simulation.
	 * @param eventQueue
	 *            clearing eventQueue from all future events.
	 */
	public void execEvent(SimState state, EventQueue eventQueue) {

		state.setEvent(this);
		eventQueue.getSortedSequence().clearSeq();
		state.end();
		simulator.stop();

	}

	/**
	 * Do not create a future event.
	 */
	public void createNextEvent(Event event, EventQueue eventQueue) {

	}
}

package event;

import model.SimState;

public class LeaveEvent extends Event {

	public LeaveEvent( double time,String s) {
		super(time,s);
		// TODO Auto-generated constructor stub
	}

	
	public void execEvent(SimState state, EventQueue eventQueue) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void createNextEvent(double time, Event event) {
		// TODO Auto-generated method stub
		
	}

}
// kollar först om det finns bilar i queue.

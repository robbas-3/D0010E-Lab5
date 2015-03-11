package event;

import java.lang.Thread.State;

import model.CarWash;
import model.CarWashState;
import model.SimState;

public class LeaveEvent extends Event {
CarWashState state;
CarWash carWash;
	public LeaveEvent( double time,String s,CarWash carWash) {
		super(time,s);
		this.carWash = carWash;
	}

	
	public void execEvent(SimState sState, EventQueue eventQueue) {
		// TODO Auto-generated method stub
		state = (CarWashState)sState;
		state.setEvent(this);
		
		carWash.emptyCarWash();
		if(state.getCarQueueSize()!=0)
			// ta första bilen i kön (stått i kö längst) state.carQueue.SHABLAM.
			// carFromQueue.carWashEvent-->LeaveEvent
			if(state.emptyFastCarWashes()!=0){
				// måste få bil från kön  
				//createNextEvent(double time,Event event,EventQueue eventQueue); och ArriveEvent(double time, String s,EventQueue eventQueue, CarWashState state)
				createNextEvent(state.getFastTime(),new ArriveEvent(state.getFastTime(),"Arrive",eventQueue,state),eventQueue);
			}
			else if(state.emptyFastCarWashes()==0 && state.emptySlowCarWashes()!=0){
				createNextEvent(state.getFastTime(),new ArriveEvent(state.getFastTime(),"Arrive",eventQueue,state),eventQueue);
			}
		}
	


	@Override
	public void createNextEvent(double time, Event event,EventQueue eventQueue) {
		createNewEvent(eventQueue.getSortedSequence(),event);
		
		
	}

}

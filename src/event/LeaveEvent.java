package event;

import model.CarWashState;
import model.SimState;

public class LeaveEvent extends Event {
CarWashState state;
	public LeaveEvent( double time,String s) {
		super(time,s);
		
	}

	
	public void execEvent(CarWashState state, EventQueue eventQueue) {
		// TODO Auto-generated method stub
		state.setEvent(this);
		if(state.getCarQueueSize()==0){
			// queue empty carwash machine +1
		}
		else{
			// ta första bilen i kön (stått i kö längst) state.carQueue.SHABLAM.
			// carFromQueue.carWashEvent-->LeaveEvent
			if(state.emptyFastCarWashes()!=0){												// måste få bil från kön
				createNextEvent(state.getFastTime(),new CarWashEvent(state.getFastTime(),"",state.addCar((state.getCarQueue().get(state.getCarQueueSize()-1))));
			}
			else if(state.emptySlowCarWashes()!=0){
				createNextEvent(state.getSlowTime(),new CarWashEvent(state.getSlowTime(),"",state.addCar(car)),eventQueue);
			}
			
		}
	}


	@Override
	public void createNextEvent(double time, Event event,EventQueue eventQueue) {
		createNewEvent(eventQueue.getSortedSequence(),event);
		
		
	}

}

package event;

import java.lang.Thread.State;

import model.Car;
import model.CarWash;
import model.CarWashState;
import model.SimState;

public class LeaveEvent extends Event {
CarWashState state;
CarWash carWash;
Car car;
	public LeaveEvent( double time,String s,CarWash carWash, Car car) {
		super(time,s);
		this.carWash = carWash;
		this.car = car;
	}

	
	public void execEvent(SimState sState, EventQueue eventQueue) {
		// TODO Auto-generated method stub
		state = (CarWashState)sState;
		state.setEvent(this);
		state.leaveCarWash(car);
	
		cleanCar();
		carWash.emptyCarWash();
		
		if(state.getCarQueueSize() > 0){
				if(state.emptyFastCarWashes() > 0){
					Car car = state.getCarNRemove(0);
					CarWash carWash = state.addCar(car);
					createNextEvent(new LeaveEvent(state.getFastTime()+state.getTime(),"Leave",carWash, car),eventQueue);
				}
				else if(state.emptySlowCarWashes()> 0){
					Car car = state.getCarNRemove(0);
					CarWash carWash = state.addCar(car);
					createNextEvent(new LeaveEvent(state.getSlowTime()+state.getTime(),"Leave",carWash, car),eventQueue);
				}
			}

//		if(state.getCarQueueSize()!=0)
//			// ta första bilen i kön (stått i kö längst) state.carQueue.SHABLAM.
//			// carFromQueue.carWashEvent-->LeaveEvent
//			if(state.emptyFastCarWashes()!=0){
//				// måste få bil från kön  
//				//createNextEvent(double time,Event event,EventQueue eventQueue); och ArriveEvent(double time, String s,EventQueue eventQueue, CarWashState state)
//				//createNextEvent(new ArriveEvent(state.getFastTime()+state.getTime(),"Arrive",eventQueue,state),eventQueue);
//			}
//			else if(state.emptyFastCarWashes()==0 && state.emptySlowCarWashes()!=0){
//				//createNextEvent(new ArriveEvent(state.getFastTime()+state.getTime(),"Arrive",eventQueue,state),eventQueue);
//			}
		}
	


	@Override
	public void createNextEvent(Event event,EventQueue eventQueue) {
		createNewEvent(eventQueue.getSortedSequence(),event);
		
		
	}
	private void cleanCar(){
		carWash.cleanCar();
	}

}

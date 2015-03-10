package event;

import model.CarFactory;
import model.CarWashState;
import model.SimState;
import random.ExponentialRandomStream;
import random.UniformRandomStream;

public class ArriveEvent extends Event {
	UniformRandomStream randomFastWash;
	UniformRandomStream randomSlowWash;
	ExponentialRandomStream arrivalRandomTime;
	CarWashState state;
	EventQueue eventQueue;
	CarFactory carFactory;
	
	private double arriveTime;

	public ArriveEvent(double time, String s,EventQueue eventQueue, CarWashState state) {
		super(time,s);
//		this.eventQueue = eventQueue;
//		this.state = state;
		
		
		
	}

		/**
		 * updates state and queue
		 */
	public void execEvent(SimState state,EventQueue eventQueue) {
		//state changes
		
//arriveTime +=state.getTime();
		
//		state.getIdleTime+= // ska utveckla denna
		// queue changes
	if(this.state.emptyFastCarWashes()!=0 || this.state.emptySlowCarWashes()!=0){
		if(this.state.addCar(carFactory.createCar())){
			
		}
		
//		LeaveEvent newLeaveEvent = new LeaveEvent(arriveTime, "Leave");
		createNewEvent(eventQueue.getSortedSequence(),new LeaveEvent(arriveTime,"Leave")); // ändra till rätt tid.
//		setChanged();
//		notifyObservers();
	}
	
		
	}

}

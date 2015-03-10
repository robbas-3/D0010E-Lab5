package event;

import model.CarFactory;
import model.CarWashState;
import model.SimState;

public class ArriveEvent extends Event {
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
	if(this.state.emptyFastCarWashes()!=0 && this.state.emptySlowCarWashes()!=0){
		this.state.addCar(carFactory.createCar());
//		setChanged();
//		notifyObservers();
	}
		
	}

}

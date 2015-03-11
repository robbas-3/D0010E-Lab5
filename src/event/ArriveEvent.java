package event;

import model.CarFactory;
import model.CarWash;
import model.CarWashState;
import model.FastCarWash;
import model.SimState;
import model.SlowCarWash;

public class ArriveEvent extends Event {
	FastCarWash fCW;
	SlowCarWash sCW;
	CarWashState state;
	EventQueue eventQueue;
	CarFactory carFactory;
	


	public ArriveEvent(double time, String s,EventQueue eventQueue, CarWashState state) {
		super(time,s);
		this.eventQueue = eventQueue;
		this.state = state;	
	}
		/**
		 * updates state and queue
		 */
	public void execEvent(CarWashState state,EventQueue eventQueue) {
		
		state.setEvent(this);
		
		if(this.state.emptyFastCarWashes()!=0){
			createNextEvent(state.getFastTime(),new CarWashEvent(state.getFastTime(),"",state.addCar(carFactory.createCar())),eventQueue);
		}
		else if(this.state.emptySlowCarWashes()!=0){
			createNextEvent(state.getSlowTime(),new CarWashEvent(state.getSlowTime(),"",state.addCar(carFactory.createCar())),eventQueue);
		}
		else{
			state.addCar(carFactory.createCar());
		}
	}
	
	@Override
	public void createNextEvent(double time, Event event,EventQueue eventQueue) {
		createNewEvent(eventQueue.getSortedSequence(),event);
	}
	
	/**
	 * not used
	 * @return
	 */
	public double fastCWorSlowCW(){
		return (this.state.addCar(carFactory.createCar())) == this.fCW ? this.state.getFastTime() : this.state.getSlowTime();
	}
}

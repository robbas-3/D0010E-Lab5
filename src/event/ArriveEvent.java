package event;

import model.Car;
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
		this.carFactory = state.getCarFactory();
	}
		/**
		 * updates state and queue
		 */
	public void execEvent(SimState sState,EventQueue eventQueue) {
		
		state.setEvent(this);
			Car car = state.getCarFactory().createCar();
			CarWash carWash = state.addCar(car);
			if(carWash != null){
				state.enterCarWash(car);
				if(this.state.emptyFastCarWashes()!=0){
					createNextEvent(new LeaveEvent(state.getFastTime(),"Leave",carWash, car),eventQueue);
				}
				else if(this.state.emptySlowCarWashes()!=0){
					createNextEvent(new LeaveEvent(state.getSlowTime(),"Leave",carWash, car),eventQueue);
				}
		
			}
	}
	
	@Override
	public void createNextEvent(Event event,EventQueue eventQueue) {
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

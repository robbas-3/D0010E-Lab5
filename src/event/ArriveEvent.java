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
			
				state.enterCarWash(car);
				
				if(state.emptyFastCarWashes() > 0){
					CarWash carWash = state.addCar(car);
					createNextEvent(new LeaveEvent(state.getFastTime() + state.getTime(),"Leave",carWash, car),eventQueue);
				}
				else if(state.emptySlowCarWashes() > 0){
					CarWash carWash = state.addCar(car);
					createNextEvent(new LeaveEvent(state.getSlowTime() + state.getTime(),"Leave",carWash, car),eventQueue);
				}
				else{
					state.addCar(car);
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

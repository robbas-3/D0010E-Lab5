package event;

import model.CarWash;
import model.SimState;

public class CarWashEvent extends Event{
	EventQueue eventQueue;
	private CarWash carWash;
	private double washTime;
	public CarWashEvent(double time,String s, CarWash carWash){
		super(time,s);
		this.washTime = time;
		this.carWash = carWash;
		
	}
	
	public void execEvent(SimState state,EventQueue eventQueue){
		state.setEvent(this);
		cleanCar();
		createNextEvent(washTime,new LeaveEvent(washTime, "Leave"));
	}

	private void cleanCar(){
		carWash.cleanCar();
	}


	public void createNextEvent(double time, Event event) {
		
		createNewEvent(eventQueue.getSortedSequence(),new LeaveEvent(time,"Leave"));
		
	}

}

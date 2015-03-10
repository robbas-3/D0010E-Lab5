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
	public void execEvent() {
		
		state.setEvent(this);
		
		if(this.state.emptyFastCarWashes()!=0 || this.state.emptySlowCarWashes()!=0){
			createNextEvent(fastCWorSlowCW(),new CarWashEvent(fastCWorSlowCW(),"",this.state.addCar(carFactory.createCar())));
		}
	}
	

	public void createNextEvent(double time, Event event) {
		createNewEvent(eventQueue.getSortedSequence(),event);
	}
	public double fastCWorSlowCW(){
		return (this.state.addCar(carFactory.createCar())) == this.fCW ? this.state.getFastTime() : this.state.getSlowTime();
	}
}
//arriveTime +=state.getTime();
// två metoder som ger random slow time och fast. gör ny car wash event då ska jag använda den metoden för att skicka med en tid.

//state.getIdleTime+= // ska utveckla denna
// queue changes
//,"",this.state.addCar(carFactory.createCar()))
//leave event där ska jag också göra en ny carwash event om kön är ledig. eller i simulatorn.

//this.state.setEvent(event);;

//LeaveEvent newLeaveEvent = new LeaveEvent(arriveTime, "Leave");
//createNewEvent(eventQueue.getSortedSequence(),new CarWashEvent(fastCWorSlowCW(),"",this.state.addCar(carFactory.createCar()))); // ändra till rätt tid.
//setChanged();
//notifyObservers();

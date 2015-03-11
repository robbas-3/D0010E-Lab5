package event;

import model.Car;
import model.CarFactory;
import model.CarWash;
import model.CarWashState;
import model.FastCarWash;
import model.SimState;
import model.SlowCarWash;

/**
 * Event for an arriving Car. extends Event
 *
 */
public class ArriveEvent extends Event {
	FastCarWash fCW;
	SlowCarWash sCW;
	CarWashState state;
	EventQueue eventQueue;
	CarFactory carFactory;

	/**
	 * The constructor.
	 * 
	 * @param time
	 *            time of the even
	 * @param s
	 *            name of the event
	 * @param eventQueue
	 *            queue of events
	 * @param state
	 *            represent the state
	 */
	public ArriveEvent(double time, String s, EventQueue eventQueue,
			CarWashState state) {
		super(time, s);
		this.eventQueue = eventQueue;
		this.state = state;
		this.carFactory = state.getCarFactory();
	}

	/**
	 * Method for execution of the ArriveEvent First of all changing state to
	 * ArriveEvent and creating a car. If there are any unused fast or slow
	 * machines we add the car to this machine and schedule a future LeaveEvent.
	 * If all machines are occupied we add a car anyways, which will go into a
	 * carQueue.
	 * 
	 * @param sState
	 *            sending state so it can be changed
	 * @param eventQueue
	 *            sending eventQueue so it can be changed
	 */
	public void execEvent(SimState sState, EventQueue eventQueue) {

		state.setEvent(this);
		Car car = state.getCarFactory().createCar();
		state.enterCarWash(car);

		if (state.emptyFastCarWashes() > 0) {
			CarWash carWash = state.addCar(car);
			createNextEvent(
					new LeaveEvent(state.getFastTime() + state.getTime(),
							"Leave", carWash, car), eventQueue);
		} else if (state.emptySlowCarWashes() > 0) {
			CarWash carWash = state.addCar(car);
			createNextEvent(
					new LeaveEvent(state.getSlowTime() + state.getTime(),
							"Leave", carWash, car), eventQueue);
		} else {
			state.addCar(car);
		}

	}

	/**
	 * Method for creating a future event uses a method for creating a new
	 * method
	 * 
	 * @param event
	 *            the event which we want to create
	 * @param eventQueue
	 *            the queue of events which will need to be updated.
	 */
	public void createNextEvent(Event event, EventQueue eventQueue) {
		createNewEvent(eventQueue.getSortedSequence(), event);
	}

}

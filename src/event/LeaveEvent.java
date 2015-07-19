package event;

import model.Car;
import model.CarWash;
import model.CarWashState;
import model.SimState;

/**
 * Event for an Leaving Car. extends Event
 *
 */
public class LeaveEvent extends Event {
	CarWashState state;
	CarWash carWash;
	Car car;

	/**
	 * The constructor.
	 * 
	 * @param time
	 *            time of the even
	 * @param s
	 *            name of the event
	 * @param carWash
	 *            represents the car wash
	 * @param car
	 *            car that is about to leave
	 * 
	 */
	public LeaveEvent(double time, String s, CarWash carWash, Car car) {
		super(time, s);
		this.carWash = carWash;
		this.car = car;
	}

	/**
	 * Method for execution of the LeaveEvent. First of all changing state to
	 * LeaveEvent, cleaning the car and then removing it from the car wash. If
	 * the is any cars in the car queue we take the car first in line and add it
	 * to a car unoccupied car wash. The car added to the car wash needs to be
	 * washed, therefore we schedule a future leave event for this car.
	 * 
	 * @param sState
	 *            sending state so it can be changed
	 * @param eventQueue
	 *            sending eventQueue so it can be changed
	 */
	public void execEvent(SimState sState, EventQueue eventQueue) {

		state = (CarWashState) sState;
		state.setEvent(this);
		state.leaveCarWash(car);

		cleanCar();
		carWash.emptyCarWash();

		if (state.getCarQueueSize() > 0) {
			if (state.emptyFastCarWashes() > 0) {
				Car car = state.getCarNRemove(0);
				CarWash carWash = state.addCar(car);
				createNextEvent(
						new LeaveEvent(state.getFastTime() + state.getTime(),
								"Leave", carWash, car), eventQueue);
			} else if (state.emptySlowCarWashes() > 0) {
				Car car = state.getCarNRemove(0);
				CarWash carWash = state.addCar(car);
				createNextEvent(
						new LeaveEvent(state.getSlowTime() + state.getTime(),
								"Leave", carWash, car), eventQueue);
			}
		}

	}

	/**
	 * Method used to create the future event
	 * 
	 * @param event
	 *            future event
	 * @param eventQueue
	 *            the current event queue
	 * 
	 */
	public void createNextEvent(Event event, EventQueue eventQueue) {
		createNewEvent(eventQueue.getSortedSequence(), event);

	}

	/**
	 * Private method to clean the car.
	 */
	private void cleanCar() {
		carWash.cleanCar();
	}

}

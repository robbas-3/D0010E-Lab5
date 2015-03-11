package model;

/**
 * 
 * Class that represents the a car wash.
 *
 */
public class CarWash {

	private Car car = null;
	private boolean empty = true;

	/**
	 * Method for returning a car.
	 * 
	 * @return returns a car.
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * Method for cleaning a car, no longer needed in a car wash
	 */
	public void cleanCar() {
		car = null;
	}

	/**
	 * Putting a car in a car wash
	 * 
	 * @param car
	 */
	public void setCar(Car car) {
		this.car = car;
		empty = false;
	}

	/**
	 * Checking if car wash is empty
	 * 
	 * @return returning either true or false.
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * The cleaned car are being removed from the car wash by this method.
	 */
	public void emptyCarWash() {
		empty = true;
	}

}

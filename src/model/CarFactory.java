package model;

/**
 * 
 * Class for a car factory which creates cars.
 *
 */
public class CarFactory {

	private int carAmount = 0;

	/**
	 * Method to create a car with an id.
	 * 
	 * @return returns the created car.
	 */
	public Car createCar() {
		Car car = new Car(carAmount);
		carAmount++;
		return car;
	}

	/**
	 * cars which get rejected.
	 */
	public void rejected() {
		carAmount--;
	}

	/**
	 * cars in the car factory.
	 * 
	 * @return returns the amount of cars there is in the car factory
	 */
	public int getCarAmount() {
		return carAmount;
	}

}

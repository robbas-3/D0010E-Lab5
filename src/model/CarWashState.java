package model;

import java.util.ArrayList;

import random.*;

/**
 * 
 * Represents the state of the car wash simulation.
 *
 */
public class CarWashState extends SimState {

	private ArrayList<SlowCarWash> slowCarWash;
	private ArrayList<FastCarWash> fastCarWash;
	private ArrayList<Car> carQueue;

	private int queueSize;
	private int rejectedCarsSize = 0;
	private double idleTime, idleTimeTemp;
	private double queueTime, queueTimeTemp;
	private int seed;
	private int queueCars;

	private CarFactory carFactory;

	private UniformRandomStream randomFastWash;
	private UniformRandomStream randomSlowWash;
	private ExponentialRandomStream arrivalRandomTime;
	double fastLower, fastUpper, slowLower, slowUpper, lambda;

	/**
	 * The constructor creating car washes,car queue, car factory and starts
	 * with random stream times.
	 * 
	 * @param seed
	 *            sending desired seed
	 * @param fastAmount
	 *            sending desired fast car wash amount
	 * @param slowAmount
	 *            sending desired slow car wash amount
	 * @param queueSize
	 *            sending desired size of the max queue for the cars to be in
	 * @param fastLower
	 *            sending desired fast distribution lower
	 * @param fastUpper
	 *            sending desired fast distribution upper
	 * @param slowLower
	 *            sending desired slow distribution lower
	 * @param slowUpper
	 *            sending desired slow distribution upper
	 * @param lambda
	 *            sending desired lambda
	 */
	public CarWashState(int seed, int fastAmount, int slowAmount,
			int queueSize, double fastLower, double fastUpper,
			double slowLower, double slowUpper, double lambda) {
		fastCarWash = new ArrayList<FastCarWash>();
		slowCarWash = new ArrayList<SlowCarWash>();

		for (int i = 0; i < fastAmount; i++) {
			fastCarWash.add(new FastCarWash());
		}
		for (int i = 0; i < slowAmount; i++) {
			slowCarWash.add(new SlowCarWash());
		}
		carQueue = new ArrayList<Car>();
		idleTime = 0;
		idleTimeTemp = System.currentTimeMillis();
		queueTime = 0;
		queueTimeTemp = System.currentTimeMillis();

		this.queueSize = queueSize;

		this.seed = seed;

		this.fastLower = fastLower;
		this.fastUpper = fastUpper;
		this.slowLower = slowLower;
		this.slowUpper = slowUpper;
		this.lambda = lambda;

		randomFastWash = new UniformRandomStream(fastLower, fastUpper, seed);
		randomSlowWash = new UniformRandomStream(slowLower, slowUpper, seed);
		arrivalRandomTime = new ExponentialRandomStream(lambda, seed);

		carFactory = new CarFactory();
	}

	/**
	 * getting a random arrival time
	 * 
	 * @return returns the time value
	 */
	public double arrivalTime() {
		return arrivalRandomTime.next();
	}

	/**
	 * getting lambda
	 * 
	 * @return value of lambda
	 */
	public double getLambda() {
		return lambda;
	}

	/**
	 * getting a streamed time for a fast car wash
	 * 
	 * @return returns the time value
	 */
	public double getFastTime() {
		return randomFastWash.next();
	}

	/**
	 * getting a streamed time for a slow car wash
	 * 
	 * @return returns the time value
	 */
	public double getSlowTime() {
		return randomSlowWash.next();
	}

	/**
	 * getting lower value of slow car wash
	 * 
	 * @return low value
	 */
	public double getSlowLower() {
		return slowLower;
	}

	/**
	 * getting lower value of fast car wash
	 * 
	 * @return low value
	 */
	public double getFastLower() {
		return fastLower;
	}

	/**
	 * getting upper value of slow car wash
	 * 
	 * @return upper value
	 */
	public double getSlowUpper() {
		return slowUpper;
	}

	/**
	 * getting upper value of fast car wash
	 * 
	 * @return upper value
	 */
	public double getFastUpper() {
		return fastUpper;
	}

	/**
	 * checking how many car washes that is empty
	 * 
	 * @return returns value of not used slow car washes
	 */
	public int emptySlowCarWashes() {
		int spots = slowCarWash.size();
		for (SlowCarWash scw : slowCarWash) {
			if (!scw.isEmpty()) {
				spots--;
			}
		}
		return spots;
	}

	/**
	 * checking how many car washes that is empty
	 * 
	 * @return returns value of not used fast car washes
	 */
	public int emptyFastCarWashes() {
		int spots = fastCarWash.size();
		for (FastCarWash fcw : fastCarWash) {
			if (!fcw.isEmpty()) {
				spots--;
			}
		}
		return spots;
	}

	/**
	 * getting the number of rejected cars
	 * 
	 * @return returns the amount of rejected cars.
	 */
	public int getRejectedCars() {
		return rejectedCarsSize;
	}

	/**
	 * trying to add a car to an empty car wash, first checks if there is any
	 * unused fast car washes, and the slow car washes. if all car washes are
	 * occupied we add it to the car queue. if the car queue is full we reject
	 * the car.
	 * 
	 * @param car
	 *            the car that is about to be added
	 * @return
	 */
	public CarWash addCar(Car car) {

		for (FastCarWash fcw : fastCarWash) {
			if (fcw.isEmpty()) {
				fcw.setCar(car);
				return fcw;
			}
		}
		for (SlowCarWash scw : slowCarWash) {
			if (scw.isEmpty()) {
				scw.setCar(car);
				return scw;
			}
		}
		if (carQueue.size() < queueSize) {
			carQueue.add(car);
			queueCars++;
		} else {
			rejectedCarsSize++;
		}
		return null;
	}

	/**
	 * Setting the idle time temp time and dividing it to get seconds. then
	 * adding it to the idle time
	 * 
	 */
	private void setIdleTime() {
		idleTimeTemp = (System.currentTimeMillis() - idleTimeTemp) / 1000;
		idleTime += emptyFastCarWashes() * idleTimeTemp + emptySlowCarWashes()
				* idleTimeTemp;
		idleTimeTemp = System.currentTimeMillis();
	}

	/**
	 * sets the queue time for the cars.
	 */
	private void setQueueTime() {

		queueTime += (System.currentTimeMillis() - queueTimeTemp)
				* carQueue.size();
		queueTimeTemp = System.currentTimeMillis();

	}

	/**
	 * how big the max queue is
	 * 
	 * @return the queue size
	 */
	public int getQueueSize() {
		return queueSize;
	}

	/**
	 * getting the current size of the queue
	 * 
	 * @return size of current queue
	 */
	public int getCarQueueSize() {
		return carQueue.size();
	}

	/**
	 * getting the cars in queue
	 * 
	 * @return amount of queued cars
	 */
	public int getQueueCars() {
		return queueCars;
	}

	/**
	 * getting the idle time
	 * 
	 * @return idle time
	 */
	public double getIdleTime() {
		return idleTime;
	}

	/**
	 * getting the queued time for the cars
	 * 
	 * @return the queued time
	 */
	public double getQueueTime() {
		return queueTime / 1000;
	}

	/**
	 * leaving the car wash, updating the queued time and idle time.
	 * 
	 * @param car
	 *            the car that leaves
	 */
	public void leaveCarWash(Car car) {
		setQueueTime();

		setIdleTime();
		setChanged();
		notifyObservers(car);
	}

	/**
	 * entering the car wash, updating the queued time and idle time.
	 * 
	 * @param car
	 *            the car that enters
	 */
	public void enterCarWash(Car car) {
		setQueueTime();

		setIdleTime();
		setChanged();
		notifyObservers(car);
	}

	/**
	 * updates the queue time and idle time.
	 */
	public void end() {
		setQueueTime();

		setIdleTime();
		setChanged();
		notifyObservers();
	}

	/**
	 * getting the car factory
	 * 
	 * @return the car factory
	 */
	public CarFactory getCarFactory() {
		return carFactory;
	}

	/**
	 * to get the seed
	 * 
	 * @return the seed
	 */
	public int getSeed() {
		return seed;
	}

	/**
	 * how many slow car washes we have
	 * 
	 * @return the number of car washes
	 */
	public int slowCarWashes() {
		return slowCarWash.size();
	}

	/**
	 * how many fast car washes we have
	 * 
	 * @return the number of car washes
	 */
	public int fastCarWashes() {
		return fastCarWash.size();
	}

	/**
	 * to get the car queue
	 * 
	 * @return the car queue
	 */
	public ArrayList<Car> getCarQueue() {
		return carQueue;
	}

	/**
	 * computing mean queue time
	 * 
	 * @return returns the mean queue time
	 */
	public double meanQueueingTime() {
		return (getQueueCars() == 0) ? 0 : getQueueTime()
				/ (carFactory.getCarAmount() - rejectedCarsSize);
	}

	/**
	 * gets car and removes it from the queue
	 * 
	 * @param i
	 *            which index from the car queue
	 * @return the car from the queue
	 */
	public Car getCarNRemove(int i) {
		Car carElement = getCarQueue().get(0);
		getCarQueue().remove(i);
		return carElement;
	}
}

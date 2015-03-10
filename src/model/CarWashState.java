package model;

import java.util.ArrayList;

import random.*;

public class CarWashState extends SimState{

	private ArrayList<SlowCarWash> slowCarWash;
	private ArrayList<FastCarWash> fastCarWash;
	private ArrayList<Car> carQueue;

	private int queueSize;
	private int rejectedCarsSize = 0;
	private double idleTime, idleTimeTemp;
	private double queueTime;
	private int seed;
	private int queueCars;
	
	private CarFactory carFactory;
	
	private UniformRandomStream randomFastWash;
	private UniformRandomStream randomSlowWash;
	private ExponentialRandomStream arrivalRandomTime;
	double fastLower, fastUpper, slowLower, slowUpper, lambda;
	
	public CarWashState(int seed, int fastAmount, int slowAmount, int queueSize, double fastLower, double fastUpper ,double slowUpper, double slowLower, double lambda){
		for(int i = 0; i < fastAmount; i++){
			fastCarWash.add(new FastCarWash());
		}
		for(int i = 0; i < slowAmount; i++){
			slowCarWash.add(new SlowCarWash());
		}
		
		idleTime = 0;
		idleTime = System.currentTimeMillis();
		
		this.queueSize = queueSize;
		
		this.seed = seed;
		
		this.fastLower = fastLower;
		this.fastUpper = fastUpper;
		this.slowLower = slowLower;
		this.slowUpper = slowUpper;
		this.lambda = lambda;
		
		randomFastWash = new UniformRandomStream(fastLower,fastUpper,seed);
		randomSlowWash = new UniformRandomStream(slowLower,slowUpper,seed);
		arrivalRandomTime = new ExponentialRandomStream(lambda, seed);
		
		carFactory = new CarFactory();
	}

	public double arrivalTime(){
		return arrivalRandomTime.next();
	}
	public double getLambda(){
		return lambda;
	}
	public double getFastTime(){
		return randomFastWash.next();
	}
	public double getSlowTime(){
		return randomSlowWash.next();
	}
	public double getSlowLower(){
		return slowLower;
	}
	public double getFastLower(){
		return fastLower;
	}
	public double getSlowUpper(){
		return slowUpper;
	}
	public double getFastUpper(){
		return fastUpper;
	}
	
	public int emptySlowCarWashes(){
		int spots = slowCarWash.size();
		
		for(SlowCarWash scw:slowCarWash){
			if(!scw.isEmpty()){
				spots--;
			}
		}
		return spots;
	}
	
	public int emptyFastCarWashes(){
		int spots = fastCarWash.size();
		
		for(FastCarWash fcw:fastCarWash){
			if(!fcw.isEmpty()){
				spots--;
			}
		}
		return spots;
	}
	
	public int getRejectedCars(){
		return rejectedCarsSize;
	}
	public CarWash addCar(Car car){
		
		for(FastCarWash fcw:fastCarWash){
			if(fcw.isEmpty()){
				fcw.setCar(car);
				car.stopQueue();
				setIdleTime();
				setQueueTime(car);
				setChanged();
				notifyObservers(car);
				return fcw;
			}
		}
		for(SlowCarWash scw:slowCarWash){
			if(scw.isEmpty()){
				scw.setCar(car);
				car.stopQueue();
				setIdleTime();
				setQueueTime(car);
				setChanged();
				notifyObservers(car);
				return scw;
			}
		}
		
		if(carQueue.size() <= queueSize){
			carQueue.add(car);
			car.startQueue();
			queueCars++;
		}else{
			rejectedCarsSize ++;
			getCarFactory().rejected();
		}

		return null;
	}
	
	private void setIdleTime(){
		idleTimeTemp = System.currentTimeMillis() - idleTimeTemp;
		idleTime += emptyFastCarWashes() * idleTimeTemp + emptySlowCarWashes() * idleTimeTemp;
		idleTimeTemp = System.currentTimeMillis();
	}
	private void setQueueTime(Car car){
		queueTime += car.getQueueTime();
	}
	
	public int getQueueSize(){
		return queueSize;
	}
	public int getCarQueueSize(){
		return carQueue.size();
	}
	public int getQueueCars(){
		return queueCars;
	}
	public double getIdleTime(){
		return idleTime;
	}
	public double getQueueTime(){
		return queueTime;
	}
	
	public CarFactory getCarFactory(){
		return carFactory;
	}
	public int getSeed(){
		return seed;
	}
	public int slowCarWashes(){
		return slowCarWash.size();
	}
	public int fastCarWashes(){
		return fastCarWash.size();
	}
	
	public double meanQueueingTime() {
		return (getQueueCars() == 0) ? 0 :  getQueueTime() / carFactory.getCarAmount() ;
	}
}


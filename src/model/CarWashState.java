package model;

import java.util.ArrayList;

import random.*;

public class CarWashState extends SimState{

	private ArrayList<SlowCarWash> slowCarWash;
	private ArrayList<FastCarWash> fastCarWash;
	private ArrayList<Car> carQueue;

	private int queueSize;
	private int rejectedCarsSize = 0;
	private double idleTime;
	private double queueTime;
	private int seed;
	private double lower, upper;
	
	private CarFactory carFactory;
	
	private UniformRandomStream randomFastWash;
	private UniformRandomStream randomSlowWash;
	private ExponentialRandomStream arrivalRandomTime;
	
	public CarWashState(int seed, int fastAmount, int slowAmount, int queueSize, double fastLower, double fastUpper ,double slowUpper, double slowLower, double lambda){
		for(int i = 0; i < fastAmount; i++){
			fastCarWash.add(new FastCarWash());
		}
		for(int i = 0; i < slowAmount; i++){
			slowCarWash.add(new SlowCarWash());
		}
		
		this.queueSize = queueSize;
		
		this.seed = seed;
		
		randomFastWash = new UniformRandomStream(fastLower,fastUpper,seed);
		randomSlowWash = new UniformRandomStream(slowLower,slowUpper,seed);
		arrivalRandomTime = new ExponentialRandomStream(lambda, seed);
		
		carFactory = new CarFactory();
	}
	
	
	public double getFastTime(){
		return randomFastWash.next();
	}
	public double getSlowTime(){
		return randomSlowWash.next();
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
				return fcw;
			}
		}
		for(SlowCarWash scw:slowCarWash){
			if(scw.isEmpty()){
				scw.setCar(car);
				return scw;
			}
		}
		
		if(carQueue.size() <= queueSize){
			carQueue.add(car);
		}else{
			rejectedCarsSize ++;
		}

		return null;
	}
	
	public int getQueueSize(){
		return queueSize;
	}
	
	public double getIdleTime(){
		return idleTime;
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
}

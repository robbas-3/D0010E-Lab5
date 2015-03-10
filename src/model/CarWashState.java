package model;

import java.util.ArrayList;

import random.*;

public class CarWashState extends SimState{

	private ArrayList<SlowCarWash> slowCarWash;
	private ArrayList<FastCarWash> fastCarWash;
	private ArrayList<Car> carQueue;

	private int queueSize;
	private int rejectedCarsSize;
	private double idleTime;
	private double queueTime;
	
	private ExponentialRandomStream ers;
	private UniformRandomStream urs;
	
	public CarWashState(int seed, int fastAmount, int slowAmount){
		for(int i = 0; i < fastAmount; i++){
			fastCarWash.add(new FastCarWash());
		}
		for(int i = 0; i < slowAmount; i++){
			slowCarWash.add(new SlowCarWash());
		}
		
		
		
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
	public boolean addCar(Car car){
		
		for(FastCarWash fcw:fastCarWash){
			if(fcw.isEmpty()){
				fcw.addCar(car);
				return true;
			}
		}
		for(SlowCarWash scw:slowCarWash){
			if(scw.isEmpty()){
				scw.addCar(car);
				return true;
			}
		}
		
		if(carQueue.size() <= queueSize){
			carQueue.add(car);
		}else{
			rejectedCarsSize ++;
		}

		return false;
	}
	
}

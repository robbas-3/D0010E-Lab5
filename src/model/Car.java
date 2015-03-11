package model;

public class Car {
	
	private int id;
	private double queueTime = 0;;
	
	public Car(int id){
		this.id = id;
		queueTime = System.currentTimeMillis();
	}
	
	public int getId(){
		return id;
	}
	
	public double getQueueTime(){
		double time = System.currentTimeMillis() - queueTime;
		queueTime = System.currentTimeMillis();
		return time/1000;
	}
	
}

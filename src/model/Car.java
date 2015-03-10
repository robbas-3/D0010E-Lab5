package model;

public class Car {
	
	private int id;
	private double queueTime;
	
	public Car(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void startQueue(){
		queueTime = System.currentTimeMillis();
	}
	public void stopQueue(){
		queueTime = System.currentTimeMillis() - queueTime;
	}
	public double getQueueTime(){
		return queueTime;
	}
	
}

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
	public double getQueueTime(){
		return System.currentTimeMillis() - queueTime;
	}

	
}

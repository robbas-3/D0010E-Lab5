package model;

public class CarWash {
	
	private Car car = null;
	private boolean empty = true;
	
	public Car getCar(){
		return car;
	}
	
	public void cleanCar(){
		empty = true;
		car = null;
	}
	
	public void addCar(Car car){
		this.car = car;
		empty = false;
	}
	
	public boolean isEmpty(){
		return empty;
	}

}

package model;

public class CarWash {
	
	private Car car = null;
	private boolean empty = true;
	
	public Car getCar(){
		return car;
	}
	
	public void cleanCar(){
		car = null;
	}
	
	public void setCar(Car car){
		this.car = car;
		empty = false;
	}
	
	public boolean isEmpty(){
		return empty;
	}
	public void emptyCarWash(){
		empty = true;
	}
	
}

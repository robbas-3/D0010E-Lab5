package event;

import model.CarWash;
import model.CarWashState;

public class CarWashEvent extends Event{
	
	private CarWash carWash;
	
	public CarWashEvent(double time, String s, CarWash carWash){
		super(time,s);
		
		this.carWash = carWash;
		
	}
	
	public void execEvent(){
		cleanCar();
	}

	private void cleanCar(){
		carWash.cleanCar();
	}

}

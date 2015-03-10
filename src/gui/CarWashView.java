package gui;

import java.util.Observable;

import model.CarWashState;
import model.SimState;

public class CarWashView extends SimView {

	private CarWashState CWState;
	
	public CarWashView(SimState state){
		super(state);
		
		CWState = (CarWashState)state;
		Writefirst();
	}
	private void Writefirst(){
		 write("Fast machines: %d", CWState.);// fast machines amount
		 write("Fast machines: %d", CWState.);// slow machines amount
		 
		 write("Fast distribution: (%.1f, %.1f)", CWState. );// Fast distribution low/high
	     write("Slow distribution: (%.1f, %.1f)", CWState.);// slow distribution low/
	     write("Exponential distribution with lambda = %.1f", CWState.);// lambda distribution
		 
		 write("Seed = %d", CWState.);//seed
	     write("Max Queue size: %d", CWState.);// Max queue size
		 
		
	}
	private void Writelast(){
		write("---------------------------");
	    write("Total idle matchine time: %.2f", CWState.getIdleTime());
	    write("Total queueing time: %.2f", CWState.);//queue time total
	    write("Mean queueing time: %.2f", ); //?
	    write("Rejected cars: %d", CWState.getRejectedCars());
	}
}
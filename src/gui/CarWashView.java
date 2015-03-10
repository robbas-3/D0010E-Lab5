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
		 write("Fast machines: %d", CWState.fastCarWashes());
		 write("Fast machines: %d", CWState.slowCarWashes());
		 
		 write("Fast distribution: (%.1f, %.1f)", CWState.getFastLower()(), CWState.getFastUpper() );
	     write("Slow distribution: (%.1f, %.1f)", CWState.getSlowLower(), CWState.getSlowUpper()));
	     write("Exponential distribution with lambda = %.1f", CWState.);// lambda distribution
		 
		 write("Seed = %d", CWState.getSeed());
	     write("Max Queue size: %d", CWState.getQueueSize());
	     write("-----------------------------------");
	     write("%-8s%-6s%-6s%-5s%-11s%-10s%-11s%-11s%-10s", "Time", "Fast", "Slow", "Id", "Event", "IdleTime", "QueueTime", "QueueSize", "Rejected");
		 
		
	}
	private void Writelast(){
		write("---------------------------");
	    write("Total idle matchine time: %.2f", CWState.getIdleTime());
	    write("Total queueing time: %.2f", CWState.getTime());//queue time total
	    write("Mean queueing time: %.2f", CWState. ); //?
	    write("Rejected cars: %d", CWState.getRejectedCars());
	}
	
	
}
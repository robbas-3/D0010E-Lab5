package gui;

import java.util.Observable;

import event.Event;
import model.Car;
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
		 
		 write("Fast distribution: (%.1f, %.1f)", CWState.getFastLower(), CWState.getFastUpper());
	     write("Slow distribution: (%.1f, %.1f)", CWState.getSlowLower(), CWState.getSlowUpper());
	     write("Exponential distribution with lambda = %.1f", CWState.getLambda());
		 
		 write("Seed = %d", CWState.getSeed());
	     write("Max Queue size: %d", CWState.getQueueSize());
	     write("-----------------------------------");
	     write("%-8s%-6s%-6s%-5s%-11s%-10s%-11s%-11s%-10s", "Time", "Fast", "Slow", "Id", "Event", "IdleTime", "QueueTime", "QueueSize", "Rejected");
		 
		
	}
	private void Writelast(){
		write("---------------------------");
	    write("Total idle matchine time: %.2f", CWState.getIdleTime());
	    write("Total queueing time: %.2f", CWState.getTime());
	    write("Mean queueing time: %.2f", CWState. ); 
	    write("Rejected cars: %d", CWState.getRejectedCars());
	}
	
	public void update(Observable o, Object arg){
		
		
		super.update(o, arg);
		Event ev = (Event)arg;
		Car c = (Car)arg;
		
		String id = "-";
		if ( instanceof ArriveEvent){
			
		}
		
		if ( instanceof LeavEvent){
			
			
		}
		write("%-8.2f%-6d%-6d%-5s%-11s%-10.2f%-11.2f%-11d%-10d",
                ev.getEventTime(),
                CWState.fastCarWashes(),
                CWState.slowCarWashes(),
                id,
                ev.getName(),
                CWState.getIdleTime(),
                c.getQueueTime(),
                CWState.getQueueSize(),
                CWState.getRejectedCars());
	}
	
}
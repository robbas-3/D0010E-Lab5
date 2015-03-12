package gui;

import java.text.DecimalFormat;
import java.util.Observable;

import event.LeaveEvent;
import event.ArriveEvent;
import event.Event;
import event.StartEvent;
import event.StopEvent;
import model.Car;
import model.CarWashState;
import model.SimState;

/**
 * 
 * Class for showing the view of a simulation of a car wash
 *
 */
public class CarWashView extends SimView {

	private CarWashState CWState;

	/**
	 * Constructor
	 * 
	 * @param state
	 *            state.
	 */
	public CarWashView(SimState state) {
		super(state);

		CWState = (CarWashState) state;
		Writefirst();
	}

	/**
	 * Method for the writing that should be displayed at the beginning.
	 */
	private void Writefirst() {
		write("Fast machines: %d", CWState.fastCarWashes());
		write("Fast machines: %d", CWState.slowCarWashes());

		write("Fast distribution: (%.1f, %.1f)", CWState.getFastLower(),
				CWState.getFastUpper());
		write("Slow distribution: (%.1f, %.1f)", CWState.getSlowLower(),
				CWState.getSlowUpper());
		write("Exponential distribution with lambda = %.1f",
				CWState.getLambda());

		write("Seed = %d", CWState.getSeed());
		write("Max Queue size: %d", CWState.getQueueSize());
		write("-----------------------------------");
		write("%-8s%-6s%-6s%-5s%-11s%-10s%-11s%-11s%-10s", "Time", "Fast",
				"Slow", "Id", "Event", "IdleTime", "QueueTime", "QueueSize",
				"Rejected");
//		write("%-8.2f%-6d%-6d%-5s%-11s%-10.2f%-11.2f%-11d%-10d",0.0,
//				CWState.emptyFastCarWashes(), CWState.emptySlowCarWashes(),
//				"-","Start", CWState.getIdleTime(), CWState.getQueueTime(),
//				CWState.getCarQueueSize(), CWState.getRejectedCars());

	}

	/**
	 * Method for writing that should be displayed at the end.
	 */
	public void Writelast() {
		write("---------------------------");
		write("Total idle matchine time: %.2f", CWState.getIdleTime());
		write("Total queueing time: %.2f", CWState.getQueueTime());
		write("Mean queueing time: %.2f", CWState.meanQueueingTime());
		write("Rejected cars: %d", CWState.getRejectedCars());
	}

	/**
	 * Method that updates the console view with the current data.
	 */
	public void update(Observable o, Object arg) {

		super.update(o, arg);
		Event ev = CWState.getCurrentEvent();
		Car c = (Car) arg;

		String id = "-";
		if (ev instanceof ArriveEvent) {
			id = Integer.toString(c.getId());
		}

		if (ev instanceof LeaveEvent) {
			id = Integer.toString(c.getId());
		}
//		if(ev instanceof StartEvent){
//			id = "-";
//			
//			
//		}
		
		write("%-8.2f%-6d%-6d%-5s%-11s%-10.2f%-11.2f%-11d%-10d",
				ev.getEventTime(), CWState.emptyFastCarWashes(),
				CWState.emptySlowCarWashes(), id, ev.getName(),
				CWState.getIdleTime(), CWState.getQueueTime(),
				CWState.getCarQueueSize(), CWState.getRejectedCars());
		if (ev instanceof StopEvent) {
			
			Writelast();
		}
		

	}

}

package gui;

import java.util.Observable;
import java.util.Observer;

import event.Event;
import model.SimState;

public abstract class SimView implements Observer{
	
	protected SimState state;
	
	public SimView(SimState state) {
        this.state = state;
        state.addObserver(this);
	}
	public void update(Observable o, Object arg) {
	        Event e = (Event)arg;
	}
	    
	protected void write(Object o) {
		System.out.println(o);
		}
	protected void write(String str, Object... args) {
		System.out.println(String.format(str, args));		
	}
}
package model;

import java.util.ArrayList;
import java.util.Observable;

import event.Event;

public class SimState extends Observable {
	
	private double time;
	private Event currentEvent;
	
	public SimState(){
		
	}
	
	public double getTime(){
		return time;
	}
	
	public Event getCurrentEvent(){
		return currentEvent;
	}
	
	public void setEvent(Event event){
		this.currentEvent = event;
	}
	
}

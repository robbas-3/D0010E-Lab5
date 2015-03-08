package lists;

import evvent.Event;

public class EventQueue {
	private SortedSequence sortSeq;
	public EventQueue(Event startEvent){
		sortSeq= new SortedSequence();
	}
	/**
	 * Checking if the queue has another element.
	 */
	public Boolean hasNext(){
		if(sortSeq.getSize()==0){
			System.out.println("finns inga mer event.");
			return false;	
		}
		else{
			return true;
		}
	}
	/**
	 * Taking the first element in the queue and then removes it from the sortedSeq. 
	 * Because if we execute an event it must PERISH!
	 */
	public Event next(){
		if(hasNext())
			return null;
		else{
			Event nextElement = sortSeq.getElement(0);
			sortSeq.removeEvent(0);
			return nextElement;
			
		}
	}
	/**
	 * 
	 * @return returns the private var sortSeq.
	 */
	public SortedSequence getSortedSequence(){
		return sortSeq;
	}
	
}

package event;

public class EventQueue {
	private SortedSequence sortSeq;

	public EventQueue(SortedSequence sortSeq) {
		sortSeq = new SortedSequence();
	}

	/**
	 * Checking if the queue has another element.
	 */
	public Boolean hasNext() {
		if (sortSeq.getSize() == 0) {
			System.out.println("finns inga mer event.");
			return false;
		} else {
			return true;
		}
	}
 
	/**
	 * Taking the first element in the queue and then removes it from the
	 * sortedSeq. Because if we execute an event it must PERISH!
	 */
	public Event next() {
		if (hasNext()){
			Event nextElement = sortSeq.getElement(0);
			sortSeq.removeEvent(0);
			return nextElement;
	
		}
		else {
			return null;
		}
	
	}

	/**
	 * 
	 * @return returns the private var sortSeq.
	 */
	public SortedSequence getSortedSequence() {
		return sortSeq;
	}
}

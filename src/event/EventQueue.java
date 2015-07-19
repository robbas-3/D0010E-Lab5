package event;

/**
 * This class represents the queue of events
 * 
 * 
 *
 */
public class EventQueue {
	private SortedSequence sortSeq;

	/**
	 * The constructor
	 * 
	 * @param sortSeq
	 *            a sequence that is sorted by events time values.
	 */
	public EventQueue(SortedSequence sortSeq) {
		this.sortSeq = sortSeq;
	}

	/**
	 * Checking if the queue has another element. if the sequence of events
	 * equals 0 then we don't have any more events
	 */
	public Boolean hasNext() {
		if (sortSeq.getSize() == 0) {

			return false;
		} else {
			return true;
		}
	}

	/**
	 * Taking the first element in the queue and then removes it from the
	 * sortedSequence. Because if we execute an event it disappears.
	 */
	public Event next() {
		if (hasNext()) {
			Event nextElement = sortSeq.getElement(0);
			sortSeq.removeEvent(0);
			return nextElement;

		} else {
			return null;
		}

	}

	/**
	 * Method to return the sorted sequence.
	 * 
	 * @return returns the private variable sortSeq.
	 */
	public SortedSequence getSortedSequence() {
		return sortSeq;
	}
}

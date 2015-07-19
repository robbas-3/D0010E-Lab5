package event;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collections;

/**
 * class to sort events by time
 * 
 *
 */
public class SortedSequence {

	ArrayList<Event> sortedEventList = new ArrayList<Event>();

	/**
	 * The constructor.
	 */
	public SortedSequence() {

	}

	/**
	 * Adding event to the sequence, sorting with the event time with help from
	 * the compareTo method.
	 * 
	 */
	public void addNsort(Event event) {
		this.sortedEventList.add(event);

		Collections.sort(sortedEventList);
		Collections.reverse(sortedEventList);
	}

	/**
	 * Removing event from the sequence
	 * 
	 * @param index
	 *            represents which event index in the list we want to remove
	 */
	public void removeEvent(int index) throws NoSuchElementException {
		if (sortedEventList.isEmpty()) {
			throw new NoSuchElementException(
					"Cant remove anything. Seq is empty!");
		} else {
			sortedEventList.remove(index);
		}
	}

	/**
	 * Method for returning the size of the sorted sequence
	 * 
	 * @return returns the size of the sequence
	 */
	public int getSize() {
		return sortedEventList.size();
	}

	/**
	 * Method for getting element.
	 * 
	 * @param k
	 *            element index
	 * @return returns the specific wanted index
	 */
	public Event getElement(int k) {
		return sortedEventList.get(k);
	}

	/**
	 * Method for clearing the sequence of all events.
	 */
	public void clearSeq() {
		sortedEventList.clear();
	}

}

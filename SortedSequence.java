package lists;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import evvent.Event;
public class SortedSequence {
 //PriorityQueue<Event> priQueue = new PriorityQueue<Event>();
 private static ArrayList<Event> sortedEventList;
 
 /**
  * 
  * @param startEvent placing event in seq??
  * 
  */
 
 public SortedSequence(){
	 sortedEventList = new ArrayList<Event>();
 }
 /**
  * Adding event to the sequence, sorting with EventTime. {0.14,0.14,0.15,1.0}
  */
 public void add(Event event){
	if (sortedEventList.size()==0){
			sortedEventList.add(event);
			System.out.println("adding "+event+" list empty");
		}
	else{
			
		for(int i=0;i<sortedEventList.size();i++){
			elementBiggerThanInsert(sortedEventList.get(i).getEventTime(),event.getEventTime(),i,event);
			elementSmallerThanInsert(sortedEventList.get(i).getEventTime(),event.getEventTime(),i,event);
			elementSameAsInsert(sortedEventList.get(i).getEventTime(),event.getEventTime(),i,event);
		}
	}
 } 
 
 /**
  * Removing event from the sequence
  */
 public void removeEvent(int index) throws NoSuchElementException {
	if(sortedEventList.isEmpty()){
		throw new NoSuchElementException("Cant remove anything. Seq is empty!");
	}else{
		sortedEventList.remove(index);
	}
 }
 /**
  * 
  * @return returns the size of the sequence
  */
 public int getSize(){
		return sortedEventList.size();
	}
 /**
  * 
  * @param k element index
  * @return returns the specific wanted index
  */
 public Event getElement(int k){
	 return sortedEventList.get(k);
 }
 /**
  * clearing seq
  */
 public void clearSeq(){
	 sortedEventList.clear();
 }
 private void elementBiggerThanInsert(double elementTime,double insertTime, int index, Event event ){
	 if(elementTime > insertTime){
			sortedEventList.add(index,event);
			System.out.println("adding "+event+" before "+sortedEventList.get(index));
		}
 }
 private void elementSameAsInsert(double elementTime,double insertTime, int index, Event event ){
	 if(elementTime == insertTime){
			sortedEventList.add(event);
			System.out.println("adding "+event+" after "+sortedEventList.get(index));
		} 
 }
 private void elementSmallerThanInsert(double elementTime,double insertTime, int index, Event event ){
	 if(elementTime < insertTime){
			sortedEventList.add(event);
			System.out.println("adding "+event+" after "+sortedEventList.get(index));
		}
 }
 
 }
 



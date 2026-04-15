package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Event;

public class EventRepository {

	ArrayList<Event> events = new ArrayList<Event>();
	
	
	//add event 
	public void save(Event event) {
		events.add(event);
	}
	
	//find all
	public List<Event> findAll(){
		return events;
	}
	
	
	//find by date
	public List<Event> findByDate(LocalDate date){
		List<Event> result = new ArrayList<Event>();
		
		for(Event e : events) {
			if(e.getDate().equals(date))
				result.add(e);
		}
		return result;
		
	}
	
	
	//event by title
	public Event findByTitle(String title) {
		for(Event e :events) {
			if(e.getTitle().equalsIgnoreCase(title))
				return e;
		}
		return null;
	}
	
	// event delete
	public void delete(Event event) {
		events.remove(event);
	}
	
	
}

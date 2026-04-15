package service;

import java.time.LocalDate;
import java.util.List;

import exception.DuplicateEventException;
import exception.EventNotFoundException;
import model.Event;
import repository.EventRepository;

public class EventService {

	private EventRepository repository = new EventRepository();
	
	
	//add element with duplicate check
	public void addEvent(Event event) {
		
		List <Event> list =repository.findAll();
		for(Event e :list) {
			if(e.getDate().equals(event.getDate()) && e.getStartTime().equals(event.getStartTime())) {
				
				throw new DuplicateEventException("Duplicate Event | same date and Same time");
			}
		}
		repository.save(event);
	}
	
	//get all event 
	public List<Event> getAllEvents(){
		return repository.findAll();
	}
	
	
	//search by
	public List<Event> getEventByDate(LocalDate date){
		return repository.findByDate(date);
	}
	
	//delete event
	public void deleteEvent(String title) {
		Event event =repository.findByTitle(title);
		if(event==null) 
			throw new EventNotFoundException("Event not found ");
			repository.delete(event);
		
	}
	
}

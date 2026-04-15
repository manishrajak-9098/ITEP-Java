package service;

import java.time.LocalDate;
import java.util.List;

import exception.BookingNotFoundException;
import model.Booking;
import repository.BookRepo;

public class BookService {
	
	private BookRepo repo = new BookRepo();
	
	// add data
	public void add(Booking book) {
		for(Booking b : repo.findAll()) {
            // same room and same date
            if(b.getRoomNo().equals(book.getRoomNo())
               && b.getBookingDate().equals(book.getBookingDate())) {
            	
            	if(book.getStartTime().isBefore(b.getEndTime()) && book.getEndTime().isAfter(b.getStartTime())) {
            		throw new BookingNotFoundException(" Time Slot Already booked");
            	}
            }	
		}
		repo.save(book);
	}
	
	//view all booking 
	public List<Booking> viewAll(){
		return repo.findAll();
	}
	
	
	 // View booking by date
    public List<Booking> getBookingByDate(LocalDate date) {
        return repo.findByDate(date);
    }


    // View booking by room
    public Booking getBookingByRoom(String roomNo) {
        return repo.findByRoomNo(roomNo);
    }

	
	//cancel booking 
	public void cancel(int i) {
		repo.delete(i);
	}

}

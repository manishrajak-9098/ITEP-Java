package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Booking;

public class BookRepo {
	
	ArrayList<Booking> book = new ArrayList<Booking>();
	
	//Add book
	public void save(Booking books) {
		book.add(books);
	}

	
	//Read Book
	public List<Booking> findAll(){
		return book;
	}
	
	//Find by date
	public List<Booking> findByDate(LocalDate date){
		ArrayList<Booking> result = new ArrayList<Booking>();
		
		for(Booking b :book) {
			if(b.getBookingDate().equals(date))
				result.add(b);
		}
	return result;
	}
	
	//find booking by room
	
	public Booking findByRoomNo(String roomNo) {
		for(Booking bb :book) {
			if(bb.getRoomNo().equalsIgnoreCase(roomNo)) 
				return bb;
			
		}
		return null;
	}
	
	  // Delete booking
    public void delete(int id) {
        book.removeIf(b -> b.getBookingId() == id);
    }
	
	
	
}

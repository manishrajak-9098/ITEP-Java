package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import exception.BookingNotFoundException;

public class Booking {
	
	private int bookingId;
	private String title;
	private String roomNo;
	private LocalDate bookingDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String organizerName;
	private String description;
	public Booking(int bookingId, String title, String roomNo, LocalDate bookingDate, LocalTime startTime,
			LocalTime endTime, String organizerName, String description) {
		super();
		this.bookingId = bookingId;
		this.title = title;
		this.roomNo = roomNo;
		this.bookingDate = bookingDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.organizerName = organizerName;
		this.description = description;
		
			if(title==null || title.trim().isEmpty()) {
				throw new BookingNotFoundException("TItle can not be empty");
				
			}
			if(!startTime.isBefore(endTime)) {
				throw new  BookingNotFoundException("Start time must be less thand end time ");
			}
			
			if (roomNo==null || roomNo.trim().isEmpty()) {
				throw new BookingNotFoundException("Room number can not be empty");
			}
			
			 // Booking date validation
	        if(bookingDate.isBefore(LocalDate.now())) {
	            throw new BookingNotFoundException("Booking date cannot be in past");
	        }
	        
	        // Duration must be >= 30 minutes
	        long minutes = Duration.between(startTime, endTime).toMinutes();
	        if(minutes < 30) {
	            throw new BookingNotFoundException("Meeting must be at least 30 minutes");
	        }
		
	}
	
	
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public String getOrganizerName() {
		return organizerName;
	}
	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", title=" + title + ", roomNo=" + roomNo + ", bookingDate="
				+ bookingDate + ", startTime=" + startTime + ", endTime=" + endTime + ", organizerName=" + organizerName
				+ ", description=" + description + "]";
	}
	
	
	
	
	

}

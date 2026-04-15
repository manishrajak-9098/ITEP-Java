package model;

import java.time.LocalDate;
import java.time.LocalTime;

import exception.InvalidEventException;

public class Event {
	
	private String title;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private String description;
	public Event(String title, LocalDate date, LocalTime startTime, LocalTime endTime, String description) {
		super();
		this.title = title;
		this.date = date;
		this.startTime= startTime;
		this.endTime= endTime;
		this.description = description;
		
		if(title==null || title.trim().isEmpty())
			throw new InvalidEventException("Title can't be empty");
		
		if(!startTime.isBefore(endTime)) {
			throw new  InvalidEventException("Time  can't be empty");
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Event [title=" + title + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", description=" + description + "]";
	}
	
	
	

}

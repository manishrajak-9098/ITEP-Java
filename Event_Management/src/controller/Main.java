package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import model.Event;
import service.EventService;

public class Main {
		
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		
		EventService service = new EventService();
		
		while(true) {
			
			try {
				System.out.println("\n 1.Add event");
				System.out.println("\n 2.View All event");
				System.out.println("\n 3.View Events By Date");
				System.out.println("\n 4.Delete event");
				System.out.println("\n 5.Exit");
				System.out.println("Choose option");
				
				int choice = Integer.parseInt(sc.nextLine());
				
				switch (choice) {
				case 1:
					System.out.println("title : ");
					String title = sc.nextLine();
					
					System.out.println("Date (yyyy-mm-dd) : ");
					LocalDate date = LocalDate.parse(sc.nextLine());
					
					System.out.println("Hours (HH:MM) : ");
					LocalTime startTime= LocalTime.parse(sc.nextLine());
				
					System.out.println("Hours (HH:MM) : ");
					LocalTime endTime = LocalTime.parse(sc.nextLine());
			
					System.out.println("Description : ");
					String description= sc.nextLine();
					
					service.addEvent(new Event(title, date, startTime, endTime, description));
					System.out.println("Data inserted successfully...!");
					
					break;
					
				case 2:
					List<Event> allEvent =service.getAllEvents();
					if(allEvent.isEmpty()) {
						System.out.println("No events found");
					}
					else {
						
						for(Event e : allEvent) {
							System.out.println("Events : "+e);
						}
					}
					break;
					
				case 3 :
					
					System.out.println("Enter Date (yyyy-mm-dd): ");
					LocalDate searchDate = LocalDate.parse(sc.nextLine());
					List<Event> eventByDate = service.getEventByDate(searchDate);
					if(eventByDate.isEmpty()) {
						System.out.println("no event fount");
					}else
						for(Event e : eventByDate) {
							System.out.println(e);
						  }
					break;
					
				case 4 :
					System.out.println("Enter Title to delete ");
					String deleteTitle = sc.nextLine();
					service.deleteEvent(deleteTitle);
					System.out.println("Data Deleted Successfully ");
					break;
					
				case 5 :
					System.out.println("Exit....");
					System.exit(0);
					break;
					
				default : 
					System.out.println("Invalid Choice ......");
					
				}
				
				
			}catch(Exception e) {
				System.out.println("Exception : "+e);
			}
				
			
		}
			
	}
}

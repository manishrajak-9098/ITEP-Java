package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import model.Booking;
import service.BookService;


public class Main {
public static void main(String args[]) {
			
			Scanner sc = new Scanner(System.in);
			
			BookService service = new  BookService();
			
			while(true) {
			
				try {
					    System.out.println("\n1. Add Booking");
		                System.out.println("2. View All Bookings");
		                System.out.println("3. View Bookings By Date");
		                System.out.println("4. View Booking By Room");
		                System.out.println("5. Update Booking");
		                System.out.println("6. Cancel Booking");
		                System.out.println("7. Exit");
					int choice = Integer.parseInt(sc.nextLine());
					
					switch(choice) {
					//Add Book
					case 1 :
					
							System.out.println("Enter id");
							int id = Integer.parseInt(sc.nextLine());
					
							System.out.println("Enter Meeting Title");
							String title = sc.nextLine();
							
							System.out.println("Enter Booking date (YYYY-MM-DD)");
							LocalDate date = LocalDate.parse(sc.nextLine());
							
							System.out.println("Enter CheckIn time (HH:MM)");
							LocalTime start = LocalTime.parse(sc.nextLine());
					
							System.out.println("Enter Checkout time(HH:MM)");
							LocalTime end = LocalTime.parse(sc.nextLine());
							
							System.out.println("Enter Organization Name");
							String  org = sc.nextLine();
							
							System.out.println("Enter Description ");
							String des = sc.nextLine();
					
							
							Booking booking = new Booking(id, title, title, date, start, end, org, des);
							service.add(booking);
							
							System.out.println("Booking added Successfully ....!");
							break;
					
					//view all book		
					case 2 :
		                    List<Booking> allBookings = service.viewAll();

		                    if(allBookings.isEmpty()) {
		                        System.out.println("No bookings found");
		                    }
		                    else {
		                        for(Booking b : allBookings) {
		                            System.out.println(b);
		                        }
		                    }
		                    break;
		       
		            // View booking by date      
					case 3 :
	                    System.out.println("Enter Date (yyyy-mm-dd) : ");
	                    LocalDate searchDate = LocalDate.parse(sc.nextLine());

	                    List<Booking> bookingByDate = service.getBookingByDate(searchDate);

	                    if(bookingByDate.isEmpty()) {
	                        System.out.println("No booking found");
	                    }
	                    else {
	                        for(Booking b : bookingByDate) {
	                            System.out.println(b);
	                        }
	                    }
	                    break;
	                    
	                  //view room by room number
					case 4 :
						
						   System.out.println("Enter Room Number : ");
		                    String roomNo = sc.nextLine();

		                    Booking roomBooking = service.getBookingByRoom(roomNo);

		                    if(roomBooking == null) {
		                        System.out.println("No booking found for this room");
		                    }
		                    else {
		                        System.out.println(roomBooking);
		                    }

		                    break;
		             
		                    // Update booking
	                case 5:

//	                    System.out.println("Enter Booking ID to update : ");
//	                    int updateId = Integer.parseInt(sc.nextLine());
//
//	                    System.out.println("New Meeting Title : ");
//	                    String newTitle = sc.nextLine();
//
//	                    System.out.println("Room Number : ");
//	                    String newRoom = sc.nextLine();
//
//	                    System.out.println("Booking Date (yyyy-mm-dd) : ");
//	                    LocalDate newDate = LocalDate.parse(sc.nextLine());
//
//	                    System.out.println("Start Time (HH:MM) : ");
//	                    LocalTime newStart = LocalTime.parse(sc.nextLine());
//
//	                    System.out.println("End Time (HH:MM) : ");
//	                    LocalTime newEnd = LocalTime.parse(sc.nextLine());
//
//	                    System.out.println("Organizer Name : ");
//	                    String newOrganizer = sc.nextLine();
//
//	                    System.out.println("Description : ");
//	                    String newDesc = sc.nextLine();
//
//	                    Booking updatedBooking = new Booking(updateId,newTitle,newRoom,newDate,newStart,newEnd,newOrganizer,newDesc);
//
//	                    service.updateBooking(updatedBooking);
//
//	                    System.out.println("Booking Updated Successfully!");
//
	                    break;


	                // Cancel booking
	                case 6:

	                    System.out.println("Enter Booking ID to cancel : ");
	                    int deleteId = Integer.parseInt(sc.nextLine());

	                    service.cancel(deleteId);

	                    System.out.println("Booking Cancelled Successfully!");

	                    break;


	                // Exit
	                case 7:

	                    System.out.println("Exiting...");
	                    System.exit(0);
	                    break;


	                default:
	                    System.out.println("Invalid Choice");

	                }
	                

							
					
					
				
       }catch(Exception e) {
          	System.out.println("Exception : "+e);
     }
				
				
   }
			
  }
}
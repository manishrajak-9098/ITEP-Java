package controller;

import java.util.List;
import java.util.Scanner;

import entity.Book;
import service.BookService;

public class Main {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		 BookService service = new BookService();
		 
		 while(true) {
			 
			try {
				
				System.out.println("===============Library Book Management============");
				System.out.println("\n 1. Add Book ");
				System.out.println(" 2. View All Book ");
				System.out.println(" 3. Search By Isbn ");
				System.out.println(" 4. Delete");
				System.out.println(" 5. Exit");
				
				
				System.out.println("Enter Choice");
				int choice = Integer.parseInt(sc.nextLine());
				
				switch (choice) {
				
				case 1 :
					 System.out.print("Title: ");
                     String title = sc.nextLine();

                     System.out.print("Author: ");
                     String author = sc.nextLine();

                     System.out.print("ISBN: ");
                     String isbn = sc.nextLine();

                     System.out.print("Year: ");
                     int year = Integer.parseInt(sc.nextLine());

                     System.out.print("Copies: ");
                     int copies = Integer.parseInt(sc.nextLine());
                     
                     service.addBook(new Book(title, author, isbn, year, copies));
                     System.out.println("Book added Successfully");
                     break;
                     
                    
				case 2 : 
					List<Book> book = service.viewBook();
					if(book==null) {
						System.out.println("book not found");
					}else {
						for(Book b:book){
							System.out.println("View all Book : "+b);
						}
					}
					
		       case 3 :
					
					System.out.println("Enter ISBN:");
					String is = sc.nextLine();
					System.out.println(service.searchByIsbn(is));
					break;
					
				case 4 :
					System.out.println("Enter ISBN for Delete");
					String deletion = sc.nextLine();
					service.delete(deletion);
					break;
				
					
			   case 5:
                    System.out.println("Exit...");
                    return;
                    

               default:
                      System.out.println("Invalid choice");
				}
				
				
			}catch(Exception e) {
				System.out.println("Exception : "+e);
			}
		 }
		 
	}

}

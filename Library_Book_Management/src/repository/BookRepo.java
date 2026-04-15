package repository;

import java.util.ArrayList;
import java.util.List;

import entity.Book;

public class BookRepo {

	
	private ArrayList<Book> list = new ArrayList<Book>();
	
	//add book
	public void save(Book book) {
		list.add(book);
	}
	
	
	//view all book
	public List<Book> viewAllBook(){
		return list;
	}
	
	
	//find by ISBN
	  public Book findByISBN(String isbn) {
	        for (Book b : list) {
	            if (b.getIsbn().equals(isbn))
	                return b;
	        }
	        return null;
	    }
	
	//delete Book
	public void delete(Book book) {
		list.remove(book);
	}
	
	
}

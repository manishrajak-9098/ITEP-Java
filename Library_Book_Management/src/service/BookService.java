package service;

import java.util.List;

import entity.Book;
import exception.BookNotFoundException;
import repository.BookRepo;

public class BookService {

	private BookRepo repo= new BookRepo();
	
	//add book
	public void addBook(Book book) {
		repo.save(book);
	}
	
	//view book
	public List<Book> viewBook(){
		return repo.viewAllBook();
	}
	
	
	//search by isbn 
	public Book searchByIsbn(String isbn) {
		Book book=repo.findByISBN(isbn);
		if(book==null) {
			throw new BookNotFoundException("Book not Found ");
		}
		return book;
	}
	
	//Delete book
	public void delete(String isbn) {
	    Book book = repo.findByISBN(isbn);
	    if (book == null)
	        throw new BookNotFoundException("Book not found");
	    repo.delete(book);
	
	}
}

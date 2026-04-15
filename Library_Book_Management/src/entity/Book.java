package entity;

import exception.InvalidBookException;

public class Book {
	
	private String title;
	private String author;
	private String isbn;
	private int publicationYear;
	private int availableCopies;
	public Book(String title, String author, String isbn, int publicationYear, int availableCopies) {
		super();
		
		if(title==null || title.trim().isEmpty()) {
			throw new InvalidBookException("Title cannot be Empty");
		}
		if(author==null || author.trim().isEmpty()) {
			throw new InvalidBookException("author cannot be Empty");
		}
		if(isbn==null || isbn.trim().isEmpty()) {
			throw new InvalidBookException("ISBN cannot be Empty");
		}
		if(title==null || title.trim().isEmpty()) {
			throw new InvalidBookException("Title cannot be Empty");
		}
		if(publicationYear>java.time.Year.now().getValue()) {
			throw new InvalidBookException("Publication year can't be in Future");
		}
		if(availableCopies<0) {
			throw new InvalidBookException("Copies is not available");
		}
		
		
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
		this.availableCopies = availableCopies;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
	
	
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", isbn=" + isbn + ", publicationYear=" + publicationYear
				+ ", availableCopies=" + availableCopies + "]";
	}	

}

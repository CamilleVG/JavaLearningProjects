package assign02;

import java.util.GregorianCalendar;
/**
 * This class extends Book. Creates holder and due date for each Library Book
 * 
 * @author Camille Van Ginkel and Julia Ma
 * @version January 23, 2020
 */

public class LibraryBook extends Book{

	
	private String holder;
	
	private GregorianCalendar dueDate;
	
	/*
	 * Calls book from parent class Book
	 */
	 public LibraryBook(long isbn, String author, String title) {
		super (isbn, author, title);
		this.holder = null;
		this.dueDate = null;
	}
	 
	 /*
	  * Accessor method for holder field
	  * 
	  * @return holder of library book
	  */
	 public String getHolder() {
		 return this.holder;
	 }
	
	 /*
	  * Accessor method for due date field
	  * 
	  * @return due date
	  */
	 public GregorianCalendar getDueDate() {
		 return this.dueDate;
	 }
	 
	 /*
	  * Sets a checked in library book's holder and due date to null
	  */
	 public void checkIn () {
		 this.dueDate = null;
		 this.holder = null;
	 }
	 
	 /*
	  * assigns a due date and holder to a checked out library book
	  */
	 public void checkOut(String holder, GregorianCalendar dueDate ) {
		 this.dueDate = dueDate;
		 this.holder = holder;
	 }
}

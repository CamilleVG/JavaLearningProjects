package assign02;

import java.util.GregorianCalendar;
/**
 * This class extends Book and creates a Generic library book. 
 * Creates holder and due date for each library book
 * 
 * @author Camille Van Ginkel and Julia Ma
 * @version January 23, 2020
 */

public class LibraryBookGeneric<Type> extends Book{

	
	private Type holder;
	
	private GregorianCalendar dueDate;
	
	/*
	 * Calls book from parent class Book
	 */
	 public LibraryBookGeneric(long isbn, String author, String title) {
		super (isbn, author, title);
		this.holder = null;
		this.dueDate = null;
	}
	 
	 /*
	  * Accessor method for holder field
	  * 
	  * @return holder of library book
	  */
	 public Type getHolder() {
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
	 public void checkOut(Type holder, GregorianCalendar dueDate ) {
		 this.dueDate = dueDate;
		 this.holder = holder;
	 }
}

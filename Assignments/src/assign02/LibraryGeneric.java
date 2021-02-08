package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * This class represents a library, which is a collection of library books.
 * 
 * @author Erin Parker and Camille Van Ginkel and Julia Ma
 * @version January 23, 2020
 */
public class LibraryGeneric<Type>  {

	private ArrayList<LibraryBookGeneric<Type>> library;

	/**
	 * Creates an empty library.
	 */
	public LibraryGeneric() {
		library = new ArrayList<LibraryBookGeneric<Type>>();
	}

	/**
	 * Adds to the library the book, specified by the given ISBN, author, and title.
	 * Assumes there is no possibility of duplicate library books.
	 * 
	 * @param isbn   - ISBN of the book to be added
	 * @param author - author of the book to be added
	 * @param title  - title of the book to be added
	 */
	public void add(long isbn, String author, String title) {
		library.add(new LibraryBookGeneric<Type>(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library. Assumes there is no possibility
	 * of duplicate library books.
	 * 
	 * @param list - list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
		library.addAll(list);
	}

	/**
	 * Adds to the library the books specified by the input file. Assumes the input
	 * files specifies one book per line with ISBN, author, and title separated by
	 * tabs.
	 * 
	 * If file does not exist or format is violated, prints an error message and
	 * does not change the library.
	 * 
	 * @param filename
	 */
	public void addAll(String filename) {
		ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNum = 1;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();

				Scanner lineIn = new Scanner(line);
				lineIn.useDelimiter("\\t");

				if (!lineIn.hasNextLong()) {
					lineIn.close();
					throw new ParseException("ISBN", lineNum);
				}
				long isbn = lineIn.nextLong();

				if (!lineIn.hasNext()) {
					lineIn.close();
					throw new ParseException("Author", lineNum);
				}
				String author = lineIn.next();

				if (!lineIn.hasNext()) {
					lineIn.close();
					throw new ParseException("Title", lineNum);
				}
				String title = lineIn.next();

				toBeAdded.add(new LibraryBookGeneric<Type>(isbn, author, title));

				lineNum++;
				lineIn.close();
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
	}

	/**
	 * Returns the holder of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns null.
	 * 
	 * @param isbn - ISBN of the book to be looked up
	 */
	public Type lookup(long isbn) {

		for (int i = 0; i < library.size(); i++) {
			if (isbn == library.get(i).getIsbn()) {
				return library.get(i).getHolder();
			}
		}

		return null;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * @param holder - holder whose checked out books are returned
	 */
	public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {
		ArrayList<LibraryBookGeneric<Type>> holderList = new ArrayList<LibraryBookGeneric<Type>>();

		for (int i = 0; i < library.size(); i++) {
			if (holder == library.get(i).getHolder()) {
				holderList.add(library.get(i));
			}
		}
		return holderList;
	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked out, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn   - ISBN of the library book to be checked out
	 * @param holder - new holder of the library book
	 * @param month  - month of the new due date of the library book
	 * @param day    - day of the new due date of the library book
	 * @param year   - year of the new due date of the library book
	 * 
	 */
	public boolean checkout(long isbn, Type holder, int month, int day, int year) {
		// if book is not in library
		boolean bookNotFound = true;
		for (int i = 0; i < library.size() && bookNotFound; i++) {
			if (isbn == library.get(i).getIsbn()) {
				bookNotFound = false;
			}
		}
		if (bookNotFound)
			return false;

		// if book is already checked out
		boolean holderFound = false;
		for (int i = 0; i < library.size() && !holderFound; i++) {
			if (library.get(i).getHolder() != null) {
				holderFound = true;
			}
		}
		if (holderFound)
			return false;

		// find book by isbn, and sets holder and due date
		for (int i = 0; i < library.size(); i++) {
			if (isbn == library.get(i).getIsbn()) {
				GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
				library.get(i).checkOut(holder, dueDate);
				return true;
			}
		}
		return true;
	}

	/**
	 * Unsets the holder and due date of the library book.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked in, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn - ISBN of the library book to be checked in
	 */
	public boolean checkin(long isbn) {
		// if book is not in library
		boolean bookNotFound = true;
		for (int i = 0; i < library.size() && bookNotFound; i++) {
			if (isbn == library.get(i).getIsbn()) {
				bookNotFound = false;
			}
		}
		if (bookNotFound)
			return false;

		// if book is already checked in
		boolean holderFound = true;
		for (int i = 0; i < library.size() && holderFound; i++) {
			if (library.get(i).getHolder() == null) {
				holderFound = false;
			}
		}
		if (holderFound)
			return false;

		// find book by isbn, and sets holder and due date to null
		for (int i = 0; i < library.size(); i++) {
			if (isbn == library.get(i).getIsbn()) {
				library.get(i).checkIn();
				return true;
			}
		}
		return true;

	}

	/**
	 * Unsets the holder and due date for all library books checked out by the
	 * specified holder.
	 * 
	 * If no books with the specified holder are in the library, returns false;
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param holder - holder of the library books to be checked in
	 */
	public boolean checkin(Type holder) {
		// creates array list of holder's books, returns false if holder has not checked
		// out books
		ArrayList<LibraryBookGeneric<Type>> holderBooks = new ArrayList<LibraryBookGeneric<Type>>();
		holderBooks = lookup(holder);
		if (holderBooks.size() < 1)
			return false;
		for (int i = 0; i < holderBooks.size(); i++) {
			holderBooks.get(i).checkIn();

		}
		return true;
	}


	/**
	 * * Returns the list of library books, sorted by ISBN (smallest ISBN first).
	 */
	public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);
		OrderByIsbn comparator = new OrderByIsbn();
		sort(libraryCopy, comparator);
		return libraryCopy;
	}

	/**
	 * Returns the list of library books whose due date is older than the input
	 * date. The list is sorted by date (oldest first). If no library books are
	 * overdue, returns an empty list.
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);
		OrderByDueDate comparator = new OrderByDueDate();
		sort(libraryCopy, comparator);
		GregorianCalendar dueDate = new GregorianCalendar(year, month, day) ;
		int index = 0;
		for (int i =0 ; i < libraryCopy.size(); i++) {	
			if (libraryCopy.get(i).getDueDate().after(dueDate)) {
				index = index + 1;
			}
		}
		ArrayList<LibraryBookGeneric<Type>> libraryCopy2 = new ArrayList<LibraryBookGeneric<Type>>(libraryCopy.subList(0, index)) ;
		return libraryCopy2;
	}

	/** * Returns the list of library books, sorted by title */
	public ArrayList<LibraryBookGeneric<Type>> getOrderedByTitle() {
		
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);
		Comparator<LibraryBookGeneric<Type>> orderByTitle = (LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) -> lhs.getTitle().compareTo(rhs.getTitle());
		sort (libraryCopy, orderByTitle);
		
		
		return libraryCopy;
	}

	/**
	 * * Performs a SELECTION SORT on the input ArrayList. * 1. Finds the smallest
	 * item in the list. * 2. Swaps the smallest item with the first item in the
	 * list. * 3. Reconsiders the list be the remaining unsorted portion (second
	 * item to Nth item) and * repeats steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/** * Comparator that defines an ordering among library books using the ISBN. */
	protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * * Returns a negative value if lhs is smaller than rhs. * Returns a positive
		 * value if lhs is larger than rhs. * Returns 0 if lhs and rhs are equal.
		 */
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			return lhs.getIsbn() > rhs.getIsbn() ? 1 : (lhs.getIsbn() < rhs.getIsbn() ? -1 : 0);
		}
	}

	/**
	 * * Comparator that defines an ordering among library books using the due *
	 * date.
	 */
	protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			if (lhs.getDueDate().compareTo(rhs.getDueDate()) < 0) {
				return -1;
			} else if (lhs.getDueDate().compareTo(rhs.getDueDate()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}


}

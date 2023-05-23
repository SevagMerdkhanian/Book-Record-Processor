/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package ExceptionClasses;
/**
 * Exception class for faulty 13 digits ISBNs
 * @author Sevag and Alec
 *
 */
public class BadIsbn13Exception extends Exception{
	/**
	 * Default Constructor
	 */
	public BadIsbn13Exception() {
		super("Error: bad 13 digit isbn");
	}
	/**
	 * Parameterized Constructor
	 * @param msg passed message
	 */
	public BadIsbn13Exception(String msg) {
		super(msg);
	}
}

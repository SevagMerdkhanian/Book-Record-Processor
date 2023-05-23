/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package ExceptionClasses;
/**
 * Exception class for faulty 10 digits ISBNs
 * @author Sevag and Alec
 *
 */
public class BadIsbn10Exception extends Exception{
	/**
	 * Default Constructor
	 */
	public BadIsbn10Exception() {
		super("Error: bad 10 digit isbn");
	}
	/**
	 * Parameterized Constructor
	 * @param msg passed message
	 */
	public BadIsbn10Exception(String msg) {
		super(msg);
	}
}

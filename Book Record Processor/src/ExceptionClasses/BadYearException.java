/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package ExceptionClasses;
/**
 * Exception class for faulty years
 * @author Sevag and Alec
 *
 */
public class BadYearException extends Exception{
	/**
	 * Default Constructor
	 */
	public BadYearException() {
		super("Error: bad year");
	}
	/**
	 * Parameterized Constructor
	 * @param msg passed message
	 */
	public BadYearException(String msg) {
		super(msg);
	}
}

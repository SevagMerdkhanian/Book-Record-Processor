/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package ExceptionClasses;
/**
 * Exception class for too few fields
 * @author Sevag and Alec
 *
 */
public class TooFewFieldsException extends Exception {
	/**
	 * Default Constructor
	 */
	public TooFewFieldsException() {
		super("Error: too few fields");
	}
	/**
	 * Parameterized Constructor
	 * @param msg passed message
	 */
	public TooFewFieldsException(String msg) {
		super(msg);
	}
}

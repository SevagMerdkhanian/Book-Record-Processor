/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package ExceptionClasses;
/**
 * Exception class for too many fields
 * @author Sevag and Alec
 *
 */
public class TooManyFieldsException extends Exception{
	/**
	 * Default Constructor
	 */
	public TooManyFieldsException() {
		super("Error: too many fields");
	}
	/**
	 * Parameterized Constructor
	 * @param msg passed message
	 */
	public TooManyFieldsException(String msg) {
		super(msg);
	}
}

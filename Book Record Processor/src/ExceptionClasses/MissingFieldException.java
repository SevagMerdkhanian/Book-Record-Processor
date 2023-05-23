/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package ExceptionClasses;
/**
 * Exception class for missing fields
 * @author Sevag and Alec
 *
 */
public class MissingFieldException extends Exception{
	/**
	 * Default Constructor
	 */
	public MissingFieldException() {
		super("Error: missing field");
	}
	/**
	 * Parameterized Constructor
	 * @param msg passed message
	 */
	public MissingFieldException(String f) {
		super("Error: missing field " + f);
	}
	
}

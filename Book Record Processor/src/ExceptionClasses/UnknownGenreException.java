/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package ExceptionClasses;
/**
 * Exception class for unknown genres
 * @author Sevag and Alec
 *
 */
public class UnknownGenreException extends Exception{
	/**
	 * Default Constructor
	 */
	public UnknownGenreException() {
		super("Error: invalid genre");
	}
	/**
	 * Parameterized Constructor
	 * @param msg passed message
	 */
	public UnknownGenreException(String msg) {
		super(msg);
	}
}

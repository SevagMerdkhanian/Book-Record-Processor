/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package ExceptionClasses;
/**
 * Exception class for faulty prices
 * @author Sevag and Alec
 *
 */
public class BadPriceException extends Exception{
	/**
	 * Default Constructor
	 */
	public BadPriceException() {
		super("Error: bad price");
	}
	/**
	 * Parameterized Constructor
	 * @param msg passed message
	 */
	public BadPriceException(String msg) {
		super(msg);
	}
}

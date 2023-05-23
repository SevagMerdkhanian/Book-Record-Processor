/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package assignment_3;

import java.io.Serializable;
/**
 * The Book Class
 * @author Sevag and Alec
 *
 */
public class Book implements Serializable{
	private String title;
	private String authors;
	private double price;
	private String isbn;
	private String genre;
	private int year;
	/**
	 * Default Constructor
	 */
	public Book() {}
	/**
	 * Parameterized Constructor
	 * @param t the title
	 * @param a the authors
	 * @param p the price
	 * @param i the isbn
	 * @param g the genre
	 * @param y the year
	 */
	public Book(String t, String a, double p, String i, String g, int y) {
		title = t;
		authors = a;
		price = p;
		isbn = i;
		genre = g;
		year = y;
	}
	/**
	 * Accessor method for title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Accessor method for authors
	 * @return authors
	 */
	public String getAuthors() {
		return authors;
	}
	/**
	 * Accessor method for price
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Accessor method for isbn
	 * @return isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * Accessor method for genre
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * Accessor method for year
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * Mutator method for title
	 * @param t title
	 */
	public void setTitle(String t) {
		title = t;
	}
	/**
	 * Mutator method for authors
	 * @param a authors
	 */
	public void setAuthors(String a) {
		authors = a;
	}
	/**
	 * Mutator method for price
	 * @param p price
	 */
	public void setPrice(double p) {
		price = p;
	}
	/**
	 * Mutator method for isbn
	 * @param i isbn
	 */
	public void setIsbn(String i) {
		isbn = i;
	}
	/**
	 * Mutator method for genre
	 * @param g genre
	 */
	public void setGenre(String g) {
		genre = g;
	}
	/**
	 * Mutator method for year
	 * @param y year
	 */
	public void setYear(int y) {
		year = y;
	}
	/**
	 * Equals method for Book
	 */
	public boolean equals(Object other) {
		if (this == null || other==null || getClass() != other.getClass())
			return false;
		else {
			Book b = (Book)other;
			return (title.equals(b.title) && authors.equals(b.authors) && price == b.price 
					&& isbn.equals(b.isbn) && genre.equals(b.genre) && year == b.year);
		}	
	}
	/**
	 * ToString method for Book
	 */
	public String toString() {
		return("Book information... Title: " + title + ", Authors: " + authors + ", isbn: " +
				isbn + ", Genre: " + genre + ", Year: " + year);
				
	}
}


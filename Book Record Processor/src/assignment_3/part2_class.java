/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package assignment_3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import ExceptionClasses.BadIsbn10Exception;
import ExceptionClasses.BadIsbn13Exception;
import ExceptionClasses.BadPriceException;
import ExceptionClasses.BadYearException;
/**
 * Part 2: validating semantics, reading the genre files each into arrays of Book objects,
 * then serializing the arrays of Book objects each into binary files.
 * @author Sevag and Alec
 *
 */
public class part2_class {
	/**
	 * Part 2 static method
	 */
	public static void do_part2() {
		//Creating Scanner, ObjectOutputStream and PrintWriter objects
		Scanner sc = null;
		ObjectOutputStream oos = null;
		PrintWriter pw = null;
		/*Creating 9 Strings for all valid .csv files as well as
		 * a SyntaxErrorFile String, then putting all valid .csv file Strings inside an array for processing
		 */
		String CCB = "Cartoons_Comics.csv.txt";
		String HCB = "Hobbies_Collectibles.csv.txt";
		String MTV = "Movies_TV_Books.csv.txt";
		String MRB = "Music_Radio_Books.csv.txt";
		String NEB = "Nostalgia_Eclectic_Books.csv.txt";
		String OTR = "Old_Time_Radio_Books.csv.txt";
		String SSM = "Sports_Sports_Memorabilia.csv.txt";
		String TPA = "Trains_Planes_Automobiles.csv.txt";
		String semanticErrorFile = "semantic_error_file.txt";
 		String[] validCSVFiles = {CCB,HCB,MTV,MRB,NEB,OTR,SSM,TPA};
 		//Creating 9 Strings holding the corresponding binary files for each .csv file, then putting them in an array
		String binaryCCB = "Cartoons_Comics.csv.ser";
		String binaryHCB = "Hobbies_Collectibles.csv.ser";
		String binaryMTV = "Movies_TV_Books.csv.ser";
		String binaryMRB = "Music_Radio_Books.csv.ser";
		String binaryNEB = "Nostalgia_Eclectic_Books.csv.ser";
		String binaryOTR = "Old_Time_Radio_Books.csv.txt";
		String binarySSM = "Sports_Sports_Memorabilia.csv.ser";
		String binaryTPA = "Trains_Planes_Automobiles.csv.ser";
		String[] binaryCSVFiles = {binaryCCB,binaryHCB,binaryMTV,binaryMRB,
								   binaryNEB,binaryOTR,binarySSM,binaryTPA};
		//Iterating through each .csv file from part 1
		for (int i = 0; i < validCSVFiles.length; i++) {
			try {
				//Opening a stream to each file in validCSVFiles and creating a Book array for later
				sc = new Scanner(new FileInputStream(validCSVFiles[i]));
				Book[] bookArr = new Book[0];
				//Reading each line in the current file. This essentially reads every record in all .csv files from part 1
				while (sc.hasNextLine()) {
					//Storing the current line in a String variable
					String line = sc.nextLine();
					//Splitting the line by field using a regular expression that accounts for titles with or without quotation marks
					String[] record = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
					//Storing each field in the record in a variable. price and year are converted from Strings to double and int respectively
					String title = record[0];
					String authors = record[1];
					double price = Double.parseDouble(record[2]);
					String isbn = record[3];
					String genre = record[4];
					int year = Integer.parseInt(record[5]);
					try {
						/*Opening a stream to write to the semantic error file, then checking the current record for each 
						 * type of semantic error. If an error is found, using a method that throws an exception
						 */
						pw = new PrintWriter(new FileOutputStream(semanticErrorFile, true));
						if (price < 0) {
							extractedBadPrice();
						}
						if (year > 2010 && year < 1995) {
							extractedBadYear();
						}
						if (isbn.length() == 13) 
							if (!isValidIsbn13(isbn) )
								extractedBadIsbn13();
						if (isbn.length() == 10) 
							if (!isValidIsbn10(isbn))
								extractedBadIsbn10();
						/*If no exceptions have been thrown, create a Book object with the current record and append it to
						 * the array of Book objects (the array will contain Book objects all from the same .csv file)
						 */
						Book b = new Book(title, authors, price, isbn, genre, year);
						Book[] newBookArr = Arrays.copyOf(bookArr, bookArr.length + 1);
						newBookArr[newBookArr.length - 1] = b;
						bookArr = newBookArr;
					}
					//Catching relevant exceptions and printing appropriately to the semantic error file if an exception was caught
					catch (BadPriceException e) {
						printExceptionPart2(e,pw,line,validCSVFiles[i]);
					}
					catch (BadYearException e) {
						printExceptionPart2(e,pw,line,validCSVFiles[i]);
					}
					
					catch (BadIsbn13Exception e) {
						printExceptionPart2(e,pw,line,validCSVFiles[i]);
					}
					catch (BadIsbn10Exception e) {
						printExceptionPart2(e,pw,line,validCSVFiles[i]);
					}
				}
				//Serializing the array of Book objects to the appropriate binary file
				oos = new ObjectOutputStream(new FileOutputStream(binaryCSVFiles[i]));
				oos.writeObject(bookArr);
				sc.close();
				oos.close();
				
			}
			//Catching necessary exceptions for PrintWriter, Scanner and ObjectOutputStream
			catch (FileNotFoundException e) {
				System.out.println("File was not found. ");
			}
			catch (IOException e) {
				System.out.println("There was an issue. ");
				System.exit(0);
			}
		}
	}
	/**Static method that checks for the validity of a 10 digit isbn
	 * First checks if the isbn is composed of only digits, then checks
	 * if (10x1 + 9x2 + 8x3 + 7x4 + 6x5 + 5x6 + 4x7 + 3x8 + 2x9 + 1x10) is
	 * divisible by 11
	 * @param isbn the isbn 
	 * @return a boolean
	 */
	private static boolean isValidIsbn10(String isbn) {
		for (int i = 0; i < isbn.length(); i++) {
			if (Character.isAlphabetic(isbn.charAt(i))) {
				return false;
			}
		}
		int isbnSum = 0;
		int[] isbnNumArr = new int[isbn.length()];
		for (int i = 0; i < isbn.length(); i++) 
			isbnNumArr[i] = Character.getNumericValue(isbn.charAt(i));
		for (int i = 0, j = 10; i < isbnNumArr.length; i++,j--) {
			isbnSum += (isbnNumArr[i]*j);
		}
		return (isbnSum % 11 == 0);
	}
	/**Static method that checks for the validity of a 13 digit isbn
	 * First checks if the isbn is composed of only digits, then checks
	 * if (x1 +3x2 +x3 +3x4 +x5 +3x6 +x7 +3x8 +x9 +3x10 +x11 +3x12 +x13) is
	 * divisible by 10
	 * @param isbn the isbn 
	 * @return a boolean
	 */
	private static boolean isValidIsbn13(String isbn) {
		for (int i = 0; i < isbn.length(); i++) {
			if (Character.isAlphabetic(isbn.charAt(i))) {
				return false;
			}
		}
		int isbnSum = 0;
		int[] isbnNumArr = new int[isbn.length()];
		for (int i = 0; i < isbn.length(); i++) 
			isbnNumArr[i] = Character.getNumericValue(isbn.charAt(i));
		for (int i = 0; i < isbnNumArr.length; i++) {
			if (i % 2 == 0) 
				isbnSum += isbnNumArr[i];
			else
				isbnSum += (isbnNumArr[i]*3);
		}
		return (isbnSum % 10 == 0);
	}
	/*
	 * Creating four static methods that simply throw Exceptions. 
	 * This was done to keep Eclipse happy (it reported a warning before)
	 */
	/**
	 * static method for bad price
	 * @throws BadPriceException
	 */
	private static void extractedBadPrice() throws BadPriceException {
		throw new BadPriceException();
	}
	/**
	 * static method for bad year
	 * @throws BadYearException
	 */
	private static void extractedBadYear() throws BadYearException {
		throw new BadYearException();
	}
	/**
	 * static method for bad 13 digit isbn
	 * @throws BadIsbn13Exception
	 */
	private static void extractedBadIsbn13() throws BadIsbn13Exception {
		throw new BadIsbn13Exception();
	}
	/**
	 * static method for bad 10 digits isbn
	 * @throws BadIsbn10Exception
	 */
	private static void extractedBadIsbn10() throws BadIsbn10Exception {
		throw new BadIsbn10Exception();
	}
	/**
	 * A static method that prints the error to the semantic error file 
	 * @param e the Exception Object caught
	 * @param pw stream to the semantic error file
	 * @param line the written record
	 * @param fileName the name of the .csv file from which the record comes
	 */
	public static void printExceptionPart2(Exception e, PrintWriter pw, String line, String fileName) {
		pw.println("semantic error in file: " + fileName);
		pw.println("======================");
		pw.println(e.getMessage());
		pw.println("Record: " + line + "\n");
		pw.close();
	}
}
	


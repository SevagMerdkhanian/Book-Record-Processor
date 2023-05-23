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
import java.io.PrintWriter;
import java.util.Scanner;

import ExceptionClasses.MissingFieldException;
import ExceptionClasses.TooFewFieldsException;
import ExceptionClasses.TooManyFieldsException;
import ExceptionClasses.UnknownGenreException;
/**
 * Part 1: validating syntax, partitioning book records based on genre.
 * @author Sevag and Alec
 *
 */
public class part1_class {
	/**
	 * Part 1 static method
	 * @param fileName the input file
	 */
	public static void do_part1(String fileName) {
		//Creating Scanner and PrintWriter objects
		Scanner sc = null;
		PrintWriter pw = null;
		/*Creating a numOfFiles int variable (for later), 9 Strings for all valid .csv files as well as
		 * a SyntaxErrorFile String, then putting all valid .csv file Strings inside an array for processing
		 */
		int numOfFiles = 0;
		String CCB = "Cartoons_Comics.csv.txt";
		String HCB = "Hobbies_Collectibles.csv.txt";
		String MTV = "Movies_TV_Books.csv.txt";
		String MRB = "Music_Radio_Books.csv.txt";
		String NEB = "Nostalgia_Eclectic_Books.csv.txt";
		String OTR = "Old_Time_Radio_Books.csv.txt";
		String SSM = "Sports_Sports_Memorabilia.csv.txt";
		String TPA = "Trains_Planes_Automobiles.csv.txt";
		String SyntaxErrorFile = "syntax_error_file.txt";
		String[] validCSVFiles = {CCB,HCB,MTV,MRB,NEB,OTR,SSM,TPA};
		//Creating an array holding the codes for each valid genre
		String[] validCSVFilesCodes = {"CCB","HCB","MTV","MRB","NEB","OTR","SSM","TPA"};
		//Creating an inputFiles String array for later
		String[] inputFiles = new String[0];
		/*Opening Scanner to read from fileName (which will be "part1_input_file_names.txt"),
		 * Creating an array the size of the number of files in fileName (the first int in the
		 * file), then adding the csv file names (Strings) to inputFiles
		 */
		try {
			sc = new Scanner(new FileInputStream(fileName));
			numOfFiles = sc.nextInt();
			inputFiles = new String[numOfFiles];
			for (int i = 0; i < numOfFiles; i++) 
				inputFiles[i] = sc.next();	
			sc.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File was not found. ");
			//Exiting if this particular file does not exist because the whole program depends on this file
			System.exit(0);
		}
		//Interating through each file in inputFiles
		for (int i = 0; i < inputFiles.length; i++) {
			try {
				//Opening a stream to each file in inputFiles
				sc = new Scanner(new FileInputStream(inputFiles[i]));
				//Reading each line in the current file. This essentially reads every record in all input files
				while (sc.hasNextLine()) {
					//Creating a boolean variable that checks if an exception was thrown for later
					boolean threwException = false;
					//Storing the current line in a String variable
					String line = sc.nextLine();
					//Splitting the line by field using a regular expression that accounts for titles with or without quotation marks
					//It is worth mentioning that we acquried outside help to write the regular expression
					String[] record = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");					
					try {
						//Opening the stream to write (append) syntax errors to the syntax error file
						//For each syntax error, also checking if an exception has already been thrown or not
						pw = new PrintWriter(new FileOutputStream(SyntaxErrorFile, true));
						/*If length of the record is less than 6 AND if the last character in the line
						 * is not a comma, throwing a TooFewFieldsException in a seperate static method
						 */
						if (record.length < 6 && !threwException  && line.charAt(line.length()-1) != ',')
							extractedTooFewFields();
						/*If length of the record is more than 6 throwing a TooFewFieldsException 
						 * in a seperate static method
						 */
						if (record.length > 6 && !threwException)
							extractedTooManyFields();
						/*If there is a trailing comma at the end of the line, the missing field is the year,
						 * Then throwing MissingFieldException in a seperate static method
						 */
						String missingField;
						if (line.charAt(line.length()-1) == ',' && !threwException) {
							missingField = "year";
							extractedMissingField();
						}
						else {
							/*Creating an array of the names of the fields in the same order
							 * as the records are supposed to be in
							 */
							String[] fields = {"title","authors","price","isbn","genre","year"};
							/*Iterating through the current record array, checking if one of the fields
							 * is empty, and throwing a MissingFieldException with the passed parameter
							 * being the missing field name. The missing field name will have the same 
							 * index as the empty element in the record array
							 */
							for (int j = 0; j < record.length; j++ ) {
								try {
									if (record[j].isEmpty() && !threwException) {									 
										missingField = fields[j];
										throw new MissingFieldException(missingField);
									}
								}
							//Catching MissingFieldException
								catch (MissingFieldException e) {
									threwException = true;
									printExceptionPart1(e, pw, inputFiles, i, line);
								}
							}
						}
						String genre = record[4];
						//If the genre field is not equal to any of the valid genres, throwing an UnknownGenreException
						try {
							if (!(genre.equals("CCB") || genre.equals("HCB") || genre.equals("MTV") || 
								genre.equals("MTV") || genre.equals("MRB") || genre.equals("NEB") ||
								genre.equals("OTR") || genre.equals("SSM") || genre.equals("TPA"))
							    && !threwException)
								throw new UnknownGenreException();
						}
						//Catching UnknownGenreException
						catch (UnknownGenreException e) {
							threwException = true;
							printExceptionPart1(e, pw, inputFiles, i, line);

						}
						/*After checking for any syntax files, finally appending a valid record
						 * to the correct .csv file
						 */
						try {
							/*For each of the valid .csv files, opening the file, checking if
							 * the current record's genre equals that file's code, and appending the line
							 * to the file if that is the case. Also incrementing the amount of records in 
							 * that particular file, kept track of in the validCSVFilesCount array
							 */
							for (int j = 0; j < validCSVFiles.length; j++) {
								pw = new PrintWriter(new FileOutputStream(validCSVFiles[j], true));
								if (!threwException && genre.equals(validCSVFilesCodes[j])) {
									pw.println(line);
								}
								pw.close();
							}

						}
						catch (FileNotFoundException e) {
							System.out.println("File was not found. ");
						}
					}
					//Catching TooFewFieldsException
					catch (TooFewFieldsException e) {
						threwException = true;
						printExceptionPart1(e, pw, inputFiles, i, line);
						
					}
					//Catching TooManyFieldsException
					catch (TooManyFieldsException e) {
						threwException = true;
						printExceptionPart1(e, pw, inputFiles, i, line);
					}
					//Catching MissingFieldException
					catch (MissingFieldException e) {
						threwException = true;
						printExceptionPart1(e, pw, inputFiles, i, line);
					}
				}
				//Closing scanner
				sc.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("File was not found. ");
			}
		}
	}
	/*Creating three static methods that simply throw Exceptions. 
	 * This was done to keep Eclipse happy (it reported a warning before)
	 */
	/**
	 * static method for missing field
	 * @throws MissingFieldException
	 */
	private static void extractedMissingField() throws MissingFieldException {
		throw new MissingFieldException("year");
	}
	/**
	 * static method for too many fields
	 * @throws TooManyFieldsException
	 */
	private static void extractedTooManyFields() throws TooManyFieldsException {
		throw new TooManyFieldsException();
	}
	/**
	 * static method for too few fields
	 * @throws TooFewFieldsException
	 */
	private static void extractedTooFewFields() throws TooFewFieldsException {
		throw new TooFewFieldsException();
	}
	/**
	 * 
	 * @param e the Exception Object caught
	 * @param pw stream to the syntax error file
	 * @param inputFiles the array of input files
	 * @param i index of the current file in inputFiles
	 * @param line the written record
	 */
	public static void printExceptionPart1(Exception e, PrintWriter pw, String[] inputFiles, int i, String line) {
		pw.println("syntax error in file: " + inputFiles[i]);
		pw.println("====================");
		pw.println(e.getMessage());
		pw.println("Record: " + line + "\n");
		pw.close();
	}
}

/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package assignment_3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
/**
 * Part 3: reading the binary files, deserializing the array objects in each file, and
 * then providing an interacive program to allow the user to navigate the arrays.
 * @author Sevag and Alec
 *
 */
public class part3_class {
	/**
	 * Part 3 static method
	 */
	public static void do_part3() {
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
		//Creating a 2D array, with the outer elements all being the arrays of Book objects from each respective binary file
		Book[][] bookArr2D = new Book[8][];
		//Also creating an array that will hold the amount of Book objects in each Book array of the binary files
		int[] binaryRecordCount = new int[binaryCSVFiles.length];
		ObjectInputStream ois= null;
		/*For each binary fily, adding the respective Book array to bookArr2D and recording the 
		 * amount of Book objects in the array in binaryRecordCount
		 */
		for (int i = 0; i < binaryCSVFiles.length; i++) {
			try {
				ois = new ObjectInputStream(new FileInputStream(binaryCSVFiles[i]));
				bookArr2D[i] = (Book[]) ois.readObject(); 
				binaryRecordCount[i] = bookArr2D[i].length;
				ois.close();
			}
			//Catching the necessary exceptions for Object Input Stream
			catch(FileNotFoundException e) {
				System.out.println("File was not found. ");
			}
			catch(IOException e) {
				System.out.println("There was a problem. ");
			}
			catch(ClassNotFoundException e) {
				System.out.println("The class was not found ");
			}
		}
		//currentFile is the selected file to be viewed, starting at the first file (index 0)
		int currentFile = 0;
		while (true) {
			//Initializing Scanner objects for user input
			Scanner kb = new Scanner(System.in);
			Scanner kb2 = new Scanner(System.in);
			Scanner kb3 = new Scanner(System.in);
			//Printing main meny along with the current selected file and the amount of records it has
			System.out.println("------------------------");
			System.out.println("Main Menu");
			System.out.println("------------------------");
			System.out.println("v View the selected file: " + binaryCSVFiles[currentFile]+ " (" + binaryRecordCount[currentFile] + " records)");
			System.out.println("s Select a file to view");
			System.out.println("x Exit");
			System.out.println("------------------------\n");
			System.out.println("Enter Your Choice: ");
			String choice = kb.next().toLowerCase();
			boolean exitSubMenu = false;
			//currentRecord is the current record pointed at in the array, starting at the first record (index 0)
			int currentRecord = 0;
			//Reprompting user to try again in case they enter an invalid choice
			if (!(choice.equals("v") || choice.equals("s") || choice.equals("x")))
				System.out.println("Invalid choice, please try again. ");
			//Choice v
			if (choice.equals("v")) {
				boolean done = false;
				while (!done) {
					System.out.println("Enter an integer n: (Entering 0 will go back to the main menu)");
					String nString = kb.next();
					//Trying to convert n to an integer. If it cannot be done, it throws NumberFormatException
					try {
						int n = Integer.parseInt(nString);
						//if n == 0, exit
						if (n == 0)
							done = true;
						//if n = 1 or n = -1, print ONLY the value of the array of the current position
						else if (n ==1 || n == -1) {
							System.out.println(bookArr2D[currentFile][currentRecord]);
						}
						/*If n > 0, printing the records that are BELOW the current position (including the current record).
						 * The current record is updated to be the last record that was printed
						 */
						else if (n > 0) {
							int originalPosition = currentRecord;
							for (int i = originalPosition; i < n+originalPosition; i++) {
								try {
									System.out.println(bookArr2D[currentFile][i]);
									currentRecord++;
			                    }
								catch (IndexOutOfBoundsException e) {
									System.out.println("EOF has been reached.");
									break;
								}
							}
							currentRecord--;
						}
						/*If n > 0, printing the records that are ABOVE the current position (including the current record).
						 * The current record is updated to be the last record that was printed
						 */
						else if (n < 0) {
							int absoluteValueN = Math.abs(n);
							int originalPosition = currentRecord;
							for (int i = originalPosition; i > originalPosition-absoluteValueN; i--) {
								try {
									System.out.println(bookArr2D[currentFile][i]);
									currentRecord--;
								}
								catch (IndexOutOfBoundsException e) {
									System.out.println("BOF has been reached. ");
									break;
			                    }
							}
							currentRecord++;
						}
					}
					//If the choice for n could not be parsed to an integer, catching NumberFormatException and reprompting user for an integer
					catch (NumberFormatException e) {
						System.out.println("Invalid n, please enter an integer. ");
					}
		        }
			}
			//Choice s
			while (choice.equals("s") && !exitSubMenu) {
				System.out.println("------------------------");
				System.out.println("File Sub-Menu");
				System.out.println("------------------------");
				System.out.println("1 Cartoons_Comics_Books.csv.ser       (" + binaryRecordCount[0] + " records)");
				System.out.println("2 Hobbies_Collectibles_Books.csv.ser  (" + binaryRecordCount[1] + " records)");
				System.out.println("3 Movies_TV.csv.ser                   (" + binaryRecordCount[2] + " records)");
				System.out.println("4 Music_Radio_Books.csv.ser           (" + binaryRecordCount[3] + " records)");
				System.out.println("5 Nostalgia_Eclectic_Books.csv.ser    (" + binaryRecordCount[4] + " records)");
				System.out.println("6 Old_Time_Radio.csv.ser              (" + binaryRecordCount[5] + " records)");
				System.out.println("7 Sports_Sports_Memorabilia.csv.ser   (" + binaryRecordCount[6] + " records)");
				System.out.println("8 Trains_Planes_Automobiles.csv.ser   (" + binaryRecordCount[7] + " records)");
				System.out.println("9 Exit");
				System.out.println("------------------------\n");
				System.out.print("Enter your Choice: ");
				String subChoice = kb2.next();
				//Checking if user entered a valid sub choice
				boolean validSubChoice = false;
				try {
					for (int i = 1; i < 10; i++) {
						if (i == Integer.parseInt(subChoice)) {
							validSubChoice = true;
							break;
						}
					}
				}
				catch (NumberFormatException e) {}
				//Reprompting user to try again in case they enter an invalid choice
				if (!validSubChoice)
					System.out.println("Invalid choice, please try again. ");
				//Changing the current file and oing back to main menu if user enters anything from 1-8
				else if (!(subChoice.equals("9"))) {
					exitSubMenu = true;
					currentFile = Integer.parseInt(subChoice)-1;
				}
				//Going back to main menu if user enters 9
				else
					exitSubMenu = true;
			}
			//Exiting the program if user enters x
			if (choice.equals("x")) {
				System.out.println("Exiting Program.");
				kb.close();
				kb2.close();
				kb3.close();
				System.exit(0);
			}
		}
	}
}

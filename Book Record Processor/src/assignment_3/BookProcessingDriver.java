/**
 * Sevag Merdkhanian 40247912, Alec Kirakossian 40244852
 * COMP249
 * Assignment 3
 * Monday, March 27, 2023
 */
package assignment_3;
/**
 * Driver for Assignment 3
 * @author Sevag and Alec
 *
 */
public class BookProcessingDriver {
	public static void main(String[] args) {
		/*The actual code is found each part's respective class. 
		 * This was done so there wouldn't be too much clutter
		 */
		// validating syntax, partitioning book records based on genre.
		part1_class.do_part1("part1_input_file_names.txt");
		// validating semantics, reading the genre files each into arrays of Book objects,
		// then serializing the arrays of Book objects each into binary files.
		part2_class.do_part2();
		// reading the binary files, deserializing the array objects in each file, and
		// then providing an interacive program to allow the user to navigate the arrays.
		part3_class.do_part3();
	}
}

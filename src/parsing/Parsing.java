// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: trinhcon
// UT Student #: 1004353941
// Author: Conroy Trinh
//
// Student2:
// UTORID user_name: tsegirva
// UT Student #: 1003959941
// Author: Girvan Tse
//
// Student3:
// UTORID user_name: limzhi
// UT Student #: 1002183362
// Author: Lim Zhi Hua
//
// Student4:
// UTORID user_name: sureshm3
// UT Student #: 1004269365
// Author: Manish Suresh
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import errors.MissingFileNameException;
import errors.UnclosedQuotationError;

/**
 * Parsing prompts the user for input, adds the input to the log of commands,
 * trims the user input and then splits it into an array of Strings based on
 * the white spaces in the input. It then returns that array.
 *
 * @author Lim Zhi Hua
 *
 */
public class Parsing {

  /**
   * 
   * @param sc is a scanner used to receive input from the user
   * inputCommand is a list containing the log of all input commands
   * 
   * @return returns the user's input in the form of an array. Input is split
   * by whitespaces, except when they are enclosed by quotation marks
   * 		 (for Echo).
   * @throws UnclosedQuotationError 
   * @throws MissingFileNameException 
   * 
   */
  public static ArrayList<String>[] parse(Scanner sc, ArrayList<String> inputCommand) 
		  throws UnclosedQuotationError, MissingFileNameException {
	    
	  
	  String commandArray[];
	  // Read the next line of input
	  String inputValue = sc.nextLine();
	  // Store the user input onto the list of user commands, making sure to
	  // trim it beforehand so its neater
	  inputValue = inputValue.trim();
	  inputCommand.add(inputValue);
	  int numQuotes = 0;
	  for(int i = 0; i < inputValue.length(); i++) {
		  if(inputValue.charAt(i) == '"')
			  numQuotes++;
	  }
	  if((numQuotes % 2) == 0) {
		  // Create a regex which selects all the whitespaces where the end of
		  // the String can be reached (from the whitespace) through combinations
		  // of: word "words ", followed by non-quotation marks.
		  // This basically means all the white spaces not enclosed in quotation
		  // marks
		  // As those which are in one will have to go past a " to reach the end
		  String splitter = ("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		  // Use the regex to split the inputValue based on white spaces, except
		  // for when they are enclosed in quotation marks, storing them in an
		  // array
		  commandArray = inputValue.trim().split(splitter);
	  } else {
		  throw new UnclosedQuotationError("Unclosed quotation Mark in input");
	  }
    
	  // Declare the items in the 
	  ArrayList<String> arguments = new ArrayList<String>();
	  ArrayList<String> arrows = new ArrayList<String>();
	  ArrayList<String>[] formattedOutput = new ArrayList[2];
	  formattedOutput[0] = arguments;
	  formattedOutput[1] = arrows;
    
	  String current;
	  int i = 0;
	  while(i < commandArray.length) {
		  current = commandArray[i];
		  if(current.equals(">>") || current.equals(">")) {
			  formattedOutput[1].add(current);
			  i++;
			  if(i >= commandArray.length)
				  throw new MissingFileNameException("Please enter a file name"
				  		+ "after the " + current);
			  formattedOutput[1].add(commandArray[i]);			  
		  }
		  else {
			  formattedOutput[0].add(current);			  
		  }
		  i++;
	  }
	  System.out.println("This is the list of arguments");
	  for(String item: formattedOutput[0]) {
		  System.out.println(item);
	  }
	  System.out.println("This is the arrow list");
	  for(String item: formattedOutput[1] ){
		  System.out.println(item);
	  }
	  return formattedOutput;
    
    
  }
}

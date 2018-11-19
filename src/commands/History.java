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
package commands;

import java.util.ArrayList;

/**
 * History returns a String with the log of all user input commands, both 
 * valid ones, as well as invalid ones.
 * 
 * @author Lim Zhi Hua
 */
public class History extends Command{
  private ArrayList<String> commandHistory;

  /**
   * Constructor for the command
   * 
   * @param commandList is a reference to the log of commands
   */
  public History(ArrayList<String> commandList) {
    this.commandHistory = commandList;
  }

  /**
   * 
   * @param inputParameters is sometimes used to specify the number of commands
   * we want displayed.   * 
   * 
   * @return If inputParameters[1] contains a positive number less than or
   * equal to the amount of instructions we have entered, then return that
   * number of previous instructions.
   * 
   * If the input parameter is 0, we dont display anything
   * If the input parameter is negative, we display an error
   * If the input parameter is not an integer, then an error message is
   * returned
   * If the input parameter is bigger than the number of commands in the
   * history, then all previous commands are displayed.
    */
  public String execute (String[] inputParameters) {
    // Create a String to hold all our commands
    String commandList = "";
    // i will be used to keep track of how many commands we have taken
    // By default, we start from the 0th command and return all the way to the
    // end
    int i = 0;
     
    // If the user has given input, then we need to look at it to see how
    // many commands we need to give
	if(inputParameters.length > 1) {
	  // Be sure to catch an error if the user does not enter a number
	  // as an input
	  try {
		// First we make sure the number of commands the user wants is less
		// than or equal to the amount of commands we have stored.
		if((Integer.parseInt(inputParameters[1]) <= commandHistory.size())){
		  // If the user has entered a negative integer, then tell them
		  // that they cant do that
		  if(Integer.parseInt(inputParameters[1]) < 0) {
			return (inputParameters[1] + " is not a positive integer");
		  } else {
		    // If the user has entered a valid input, then we make sure
		    // to only return that number of commands
	    	i = commandHistory.size() - 
	    	Integer.parseInt(inputParameters[1]);	
		  } 
		}
	  } catch (NumberFormatException e) {
	    // If the user enters something that isn't an integer, then we
	    // tell them that they can't do that
		return (inputParameters[1] + " is not a valid integer value");
	  } 
    }

	// Loop through our list of commands, starting with the ith one, adding
	// them to the our list of commands with a line break in between them
    for(; i < this.commandHistory.size(); i++) {
      // Append to the list of commands, making sure to add a line between
      // each command as well as a number denoting how many commands we have
      // gone through. 
      // The last command does not need a line break after it
      if(i == commandHistory.size() -1 ) {
    	commandList = (commandList + (i+1) + ". " + this.commandHistory.get(i));
      } else
    	 commandList = (commandList + (i+1) 
            + ". " + this.commandHistory.get(i) + "\n");
    }
    // Return the String representation of the history of commands
    return commandList;
  }    
}

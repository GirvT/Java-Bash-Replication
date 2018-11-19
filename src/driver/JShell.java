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
package driver;
import commands.*;
import display.Display;
import errors.MissingFileNameException;
import errors.UnclosedQuotationError;
import filesystem.FileSystem;
import java.util.*;
import parsing.Parsing;


public class JShell {
	  /**
	   * JShell handles the interaction between the various classes and objects
	   * It contains an instance of each command object, as well as the 
	   * hashmap which maps the command name to the command object. 
	   * 
	   * It also runs the execute method on the appropriate method, passing
	   * the result to the display to be printed
	   * 
	   * @author Lim Zhi Hua, Conroy Trinh
	   * @throws UnclosedQuotationMarksException	 
	   *
	   */
  public static void main(String[] args){
    // TODO Auto-generated method stub
    //Stack<Directory> directoryStack = new Stack<Directory>();
    Scanner sc = new Scanner(System.in);
    // Create the fileSystem that we are using
    
    FileSystem fileSystem = new FileSystem(); // This nee
    
    // Create the an ArrayList to store the history of commands
    ArrayList<String> commandHistory = new ArrayList<String>();
    
    // Create the commands that we are using
    Command cd = new Cd(fileSystem);
    Command ls = new Ls(fileSystem);
    Command history = new History(commandHistory);
    Command cat = new Cat(fileSystem);
    Command popd = new Popd(fileSystem);
    Command pushd = new Pushd(fileSystem);
    Command echo = new Echo(fileSystem);
    Command mkdir = new Mkdir(fileSystem);
    Command pwd = new Pwd(fileSystem);
    Command man = new Manual();	

    // Create a hashtable of commands
    Hashtable<String, Command> commandDictionary= 
        new Hashtable<String, Command>();
    commandDictionary.put("cd", cd);
    commandDictionary.put("ls", ls);
    commandDictionary.put("history", history);
    commandDictionary.put("cat",  cat);
    commandDictionary.put("popd", popd);
    commandDictionary.put("pushd", pushd);
    commandDictionary.put("echo", echo);
    commandDictionary.put("mkdir", mkdir);
    commandDictionary.put("pwd",  pwd);
    commandDictionary.put("man",  man);
    
    
    //Create a while Loop to continuously ask for input
    boolean end = false;
    while(end == false) {
      // Display our current directory before the start of each line
      Display.displayItemSameLine(pwd.execute(null) + ": ");

	  // Runs the parse method from the parsing class to receive user input
		  // and convert it into an array form
		  ArrayList<String>[] inputCommand = null;
		try {
			inputCommand= Parsing.parse(sc, commandHistory);
		} catch (UnclosedQuotationError | MissingFileNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		if(inputCommand != null) {
			// First we check if the user is trying to exit shell
			if(inputCommand[0].get(0).equals("exit")) {
				end = true;
			}
			// Check if the user inputed a valid command
			else if(commandDictionary.containsKey(inputCommand[0].get(0))) {
	    	// Run the execute method on the object which corresponds to the 
	    	// user's input, making sure to pass the rest of the user's input
	    	// to the command.
				Display.displayItem(commandDictionary.
		        		get(inputCommand[0].get(0)).execute(inputCommand[0]));
		 
		      }
		      // If the user has entered an invalid command, then tell the user
			  // that
		      else {
		        Display.displayItem(inputCommand[0]
		        		+ " is not a valid command");
		      }
		}

    }
    // Close the scanner class once the program is done.
    sc.close();
  }

}




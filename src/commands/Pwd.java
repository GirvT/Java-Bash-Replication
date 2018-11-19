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

import filesystem.FileSystem;

/**
 * Pwd returns the path of our current directory as a String
 * 
 * @author Lim Zhi Hua
 *
 */
public class Pwd extends Command{
  private FileSystem fileSystem;
  
  /**
   * Constructor for the command
   * 
   * @param fileSystem a reference to the original fileSystem
   */
  public Pwd(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }
  
  /**
   * 
   * @param inputParameters is useless, but is passed to standardize the 
   * execution method across all commands
   * @return returns a String indicating the path to our current directory
   */
  public String execute (ArrayList<String> inputParameters) {
    String path =  fileSystem.getCurrentDirectory().getPath(); 
    // Display.displayItem(path);
    return path;
  }
}

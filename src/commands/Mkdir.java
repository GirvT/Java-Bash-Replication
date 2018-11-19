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

import filesystem.*;

/**
 * mkdir Command class for directory creation class
 * @author Conroy
 *
 */
public class Mkdir extends Command{
  private FileSystem fileSystem;

  /**
   * making directories of filesystem
   * @param fileSystem
   */
  public Mkdir(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  /**
   * 
   * @param inputParameters are the string arguments of the command
   * strings are paths to which we make new directories if possible
   */
  public String execute(String[] inputParameters) {
    // Initialize output string
    String output = "";
    // Go over each path in parameters
    for (int i = 1; (i < inputParameters.length); i++) {
      // Check if each Directory already exists
      if (this.fileSystem.getDirectory(inputParameters[i]) != null) {
        // If it does then add it to the output string
        output += "mkdir: cannot create directory '";
        output += inputParameters[i] + "' directory exists\n";
      } else {
        // Otherwise we can safely create a new directory
        this.fileSystem.addDirectory(inputParameters[i]);
      }
    }
    return output;
  }
}

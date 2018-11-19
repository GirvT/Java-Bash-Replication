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
 * Ls Command class for reading inodes in filesystem through use of shell
 * Can be used to read file contents
 * @author Conroy
 *
 */
public class Ls extends Command{
  private FileSystem fileSystem;
  
  /**
   * Constructor
   * @param fileSystem that shell modifies 
   */
  public Ls(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }
  
  /**
   * Executes command and prints Inodes in directory
   * @param Input arguments given to the command
   * display the contents of all directories or files of contents
   */
  public String execute(String[] inputParameters) {
    // Initialize String output
    String output;
    
    // Check if Arguments are given to Input Parameters
    if (inputParameters.length == 1) {
      // If so get the current path
      String currPath;
      currPath = fileSystem.getCurrentDirectory().getPath();
      // Display String form of current path Inode
      output = fileSystem.displayInode(currPath);
    } else {
      // If one or more paths, then display each of them one after the other
      output = this.fileSystem.displayInodes(inputParameters);
    }
    return output;
  }
}

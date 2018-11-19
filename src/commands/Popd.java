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

import directoryStack.DirectoryStack;
import errors.EmptyDirectoryStackException;
import filesystem.FileSystem;

/**
 * Popd command retrieves the directory from the directory stack and sets it as the current
 * directory
 * 
 * @author Manish Suresh
 *
 */
public class Popd extends Command {
  private FileSystem fileSystem;
  /**
   * @param fileSystem a reference to the original file system
   */
  public Popd(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }
  
  /**
   * Execution function of the for Popd, takes the top directory on the
   * stack and changes to the target directory
   * @param
   */
  public String execute(String[] inputParameters) {
    // create the directory stack to retrieve the working directory.
    DirectoryStack stack = DirectoryStack.createDirectoryStack();
    try {
      // set the retrieved from the directory stack as the current directory.
      this.fileSystem.setCurrentDirectory(stack.popDirectory());
    } catch (EmptyDirectoryStackException e) {
      // returning an error message.
      return "The directory stack is empty.";
    }
    return "";
  }
}

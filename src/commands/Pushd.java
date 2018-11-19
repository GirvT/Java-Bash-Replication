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
import filesystem.Directory;
import filesystem.FileSystem;

/**
 * Pushd command saves the current directory and pushes it into a stack, then sets the input PATH
 * parameter as a new working directory.
 * 
 * @author Manish Suresh
 */
public class Pushd extends Command {
  private FileSystem fileSystem;

  /**
   * Constructor for the command
   * 
   * @param fileSystem a refernce to the original fileSystem
   */
  public Pushd(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  /**
   * 
   * @param inputParameters contains the path of the new directory
   * @return error message or an empty string
   */
  public String execute(String[] inputParameters) {

    try {
      // find the directory specified by the path
      Directory newDirectory = (Directory) this.fileSystem.getInode(inputParameters[1]);

      // check if the directory exists
      if (newDirectory != null) {
        // create a directory stack
        DirectoryStack stack = DirectoryStack.createDirectoryStack();

        // push the current working directory into stack
        stack.pushDirectory(this.fileSystem.getCurrentDirectory());

        // set the directory specified by the path
        fileSystem.setCurrentDirectory(newDirectory);
      } else {
        // message for invalid PATH
        return "No such file or directory.";
      }
      return "";
    } catch (ArrayIndexOutOfBoundsException e) {
      // message for no input
      return "Please input a new working directory";
    }
  }
}

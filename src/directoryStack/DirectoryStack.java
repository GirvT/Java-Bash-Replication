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
package directoryStack;

/**
 * Directory Stack extends a JAVA stack and is able to perform the same methods as a stack.
 * The type of stack is a stack of directories.
 * 
 * @author Manish Suresh
 */
import java.util.Stack;
import errors.EmptyDirectoryStackException;
import filesystem.Directory;

public class DirectoryStack extends Stack<Directory> {
  public static DirectoryStack directoryStack = null;

  /**
   * Constructor for DirectoryStack
   */
  private DirectoryStack() {}

  /**
   * A factory method to create a new directory stack which ensures that only one instance is
   * created
   * 
   * @return a new Directory stack or a reference to an already created Directory Stack
   */
  public static final DirectoryStack createDirectoryStack() {
    // check if an instance of Directory has already been created
    if (directoryStack == null) {
      // create a new instance of directory stack
      directoryStack = new DirectoryStack();
    }
    // return the instance of directory stack
    return directoryStack;
  }

  /**
   * 
   * @param dir The directory to be put into the Directory stack
   */
  public void pushDirectory(Directory dir) {
    directoryStack.push(dir);
  }

  /**
   * Returns the directory from the Directory Stack
   * 
   * @return The directory from the top of the directory stack
   * @throws EmptyDirectoryStackException Error raised for empty directory stack
   */
  public Directory popDirectory() throws EmptyDirectoryStackException {
    // check if the directory stackis empty
    if (isEmpty()) {
      // if directory stack is empty then raise an error
      throw new EmptyDirectoryStackException("The directory stack is empty");
    }
    // else return the directory
    return (Directory) directoryStack.pop();
  }

  /**
   * Check if stack is empty
   * @return boolean determining wheter stack is empty
   */
  public boolean isEmpty() {
    return directoryStack.empty();
  }
}

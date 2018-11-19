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
package filesystem;

/**
 * Inode represents either a Directory or File within a file system
 * It contains its name, the path to the Inode and it's parent Directory
 * @author Conroy
 * 
 */
public class Inode {
  private String path;
  private String name;
  private Directory parentDirectory;
  
  /**
   * Constructor
   * @param parentDirectory     Parent Directory
   * @param name                File Name
   */
  public Inode(Directory parentDirectory, String name) {
   // Initialize Directory
   this.parentDirectory = parentDirectory;
   this.name = name;
   // Check to see if this is the root Directory
   if (parentDirectory != null) {
     // If not add parent directory's path to this path
     this.path = parentDirectory.getPath() + "/" + this.name;
   } else {
     // Otherwise the path is only the name
     this.path = this.name;
   }
  }

  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
    this.path = this.parentDirectory.getName() + '/' + name;
  }

  /**
   * @return the parentDirectory
   */
  public Directory getParentDirectory() {
    return this.parentDirectory;
  }

  /**
   * @param new parent directory to be set
   */
  public void setParentDirectory(Directory parentDirectory) {
    this.parentDirectory = parentDirectory;
    this.path = parentDirectory.getPath() + '/' + this.getName();
  }
}
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

import java.util.Hashtable;


/**
 * Directory extends Inode and it can contain other Inode objects
 * and stores a pointer to them in a Hashtable, mapped to them
 * using their names as keys
 * @author Conroy
 *
 */
public class Directory extends Inode{
  // This is a dictionary of getting Inode (files or directories)
  // Using a string key that is the name of the Inode
  private Hashtable<String,Inode> inodes;
  

  /**
   * Constructor
   * @param parentDirectory
   * @param name
   */
  public Directory(Directory parentDirectory, String name) {
    // Inherit characteristics from Inode
    super(parentDirectory, name);
    // Initialize Hashtable
    this.inodes = new Hashtable<String, Inode>();
  }
  
  
  /**
   * Method to retrieve Inode file within a directory
   * @param name
   * @return inode with the name in the directory
   * @exception null can be returned here
   */
  public Inode getSubInode(String name) {
    // Retrieve value using key and dictionary
    Inode inode;
    inode = (Inode) this.inodes.get(name);
    
    // Check if inode is in Directory
    if (inode == null) {
      inode = null; // throw an error here
    }
    return inode;
  }
  
  
  /**
   * Adds a new Inode to the Directory's Hashmap of subInodes
   * The Inode is considered to be "in the Directory"
   * @param inode
   */
  public void addInode(Inode inode) {
    this.inodes.put(inode.getName(), inode);
  }
  
  
  /**
   * 
   * @param name of the inode in the DIrectory
   * @return inode that was removed from the Directory
   * @exception Null can be returned here
   */
  public Inode removeInode(String name) {
    // Retrieve inode from Directory
    Inode inode = getSubInode(name);
    // Remove inode from Directory
    this.inodes.put(name, null);
    // Return the inode retrieved from the Directory and remove parent pointer
    inode.setParentDirectory(null);
    return inode;
  }
  
  public String toString() {
    // Initialize String and if boolean for first item
    String returnString = "";
    boolean first = true;
    // Loop over each content in the directory, NOTE each key is a String
    for (String key: this.inodes.keySet()) {
      // If first item omit space
      if (first){
        first = false;
      // Otherwise add space
      } else {
        returnString += ' ';
      }
      // add String key to total return String
      returnString += key;
    }
    // Return with an extra line
    return returnString + '\n';
  }
}

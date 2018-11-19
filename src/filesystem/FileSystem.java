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
// FileSystem Class

/**
 * FileSystem class used to access Directories and Files data objects   
 * @author Conroy
 *
 */
public class FileSystem {
  private Directory rootDirectory;
  private Directory currentDirectory;

  /**
   * Constructor with a root and current Directory
   * @param root
   * @param current
   */
  public FileSystem(Directory root, Directory current) {
    // Set Parameters
    this.rootDirectory = root;
    this.currentDirectory = current;
  }
  
  /**
   * Constructor, creates an empty file system with single root node
   */
  // Filesystem can initialize its own initial Directory to begin with
  public FileSystem() {
    this.rootDirectory = new Directory(null, "~");
    this.currentDirectory = this.rootDirectory;
  }

  /**
   * Takes a path and finds the parent diretory's path by slicing
   * the path string
   * @param path
   * @return the path to the parent directory of file from path
   */
  public static String[] processParentDirectoryPath(String path) {
    // Initialize String array
    String[] pathSplices = new String[2];
    // Get the length of the path to initialize index
    int index = path.length() - 1;
    if (!(path.equals("~/")) && !(path.equals("~"))) {
      // This accounts for if the last character is a slash
      index = (path.charAt(index) != '/') ?  index : index - 1;
      // From the back going backwards find the substring of last inode
      for (;(index >= 0) && (path.charAt(index) != '/'); index--) {}
      // get the substring of the Parent path and file name
    }

    // If we went through the entire string
    if (index == -1) {
      // We know that the path is the root
      pathSplices[0] = "";
      pathSplices[1] = path;
    // If we did not reach the front of the string
    } else {
      // Separate parent path and child path
      pathSplices[0] = path.substring(0, index);
      pathSplices[1] = path.substring(index + 1);
    }
    return pathSplices;
  }

  /**
   * Helper function used to parse names in between slashes
   * @param path
   * @param beginIndex
   * @return name of Inode in between '/'s beginning from beginIndex
   */
  public static String parseUntilSlash(String path, int beginIndex) {
    // Go until we get to a slash
    int i;
    for (i = beginIndex; (path.length() > i) && (path.charAt(i) != '/'); i++) {
    }

    return path.substring(beginIndex, i);
  }

  /**
   * Get any Inode in FileSystem using it's path
   * @param path
   * @return Inode in the FileSystem (null if does not exist)
   */
  public Inode getInode(String path) {
    Inode currentInode;
    int charPos = 0;
    String nextInodeName = "";
    boolean pathExists = true;
    // Check what if path is absolute
    // If absolute, then begin from root
    if (path.equals("")) {
      currentInode = this.getCurrentDirectory();
      pathExists = false;
    } else if (path.charAt(charPos) == '~'){
      // Increment to ignore root character notation
      currentInode = this.getRootDirectory();
      charPos += 2; // Compensates for tilde and slash
    // Otherwise begin from the current node
    } else {
      currentInode = this.getCurrentDirectory();
    }

    // Now traverse the Filesystem tree along the path until it breaks
    while ((path.length() > charPos) && (pathExists)) {
      // Parse the next file in the path
      nextInodeName = FileSystem.parseUntilSlash(path, charPos);
      // increment charPos by length of the file's name
      charPos += nextInodeName.length() + 1; // Plus 1 accomodates '/'
      // Get the next directory/file in path from current Directory
      currentInode = ((Directory)currentInode).getSubInode(nextInodeName);
      // if null returned, then path does not exists
      if (currentInode == null) {
        // Exit loop
        pathExists = !pathExists;
      } 
    }
    // return Inode (may be null)
    return currentInode;
  }

  /**
   * Gets Directory at string path and returns Directory object
   * @param path
   * @return Directory at path
   */
  public Directory getDirectory(String path) {
    // Call get an Inode at path
    Inode inode = this.getInode(path);
    // If it's no a Directory throw an error
    if (!(inode instanceof Directory)) {
      // Throw InodeNotDirectoryError
      return null;
    }
    // return the Directory
    return (Directory) inode;
  }

  /**
   * Gets File at string path and returns File object
   * @param path
   * @return File at path
   */
  public File getFile(String path) {
    // Call get an Inode at path
    Inode inode = this.getInode(path);
    // If it's no a File throw an error
    if (!(inode instanceof File)) {
      // Throw InodeNotFileError
      return null;
    }
    // return the Directory
    return (File) inode;
  }

  /**
   * Add new Directory on the location of path
   * @param path
   */
  public void addDirectory(String path) {
    // Initialize variables for paths and Inode variables
    String[] splices = new String[2];
    Directory parentDirectory;
    Directory newDirectory;
    
    // First access the parent directory's path from path reading method
    splices = FileSystem.processParentDirectoryPath(path);
    if ((splices[0].equals(""))) {
    // go to the respective directory along given path
      parentDirectory = this.getCurrentDirectory();
    } else {
      parentDirectory = this.getDirectory(splices[0]);
    }
    // Create a new directory and register parent-child relationship
    newDirectory = new Directory(parentDirectory, splices[1]);
    ((Directory) parentDirectory).addInode(newDirectory);
  }
  
  /**
   * Add new Directories on all paths given if possible
   * @param paths
   */
  public void addDirectories(String[] paths) {
    // Loop through each item in paths and add the Directory
    for (int i = 1; (i < paths.length); i++) {
      this.addDirectory(paths[i]);
    }
  }

  /**
   * Add a new File the file system at path if the
   * parent directory exists
   * @param path
   */
  public void addFile(String path) {
    // Initialize Parent Directory and String array
    File newFile;
    Directory parentDirectory;
    String[] pathSplices;
    // Slice path string into parts
    pathSplices = FileSystem.processParentDirectoryPath(path);
    // Get Parent File from the FileSystem
    parentDirectory = getDirectory(pathSplices[0]);
    // Create new File with name from path
    newFile = new File(parentDirectory, pathSplices[1], "");
    // Register new File with Directory Object
    parentDirectory.addInode(newFile);
  }

  /**
   * Display the Inode on the string path, directories have their
   * inodes also displayed and files only their names
   * @param path
   * @return String representation of Inode, if DNE then indicate error message
   */
  public String displayInode(String path){
    // Initialize String form and Inode on path
    String returnStr;
    Inode displayedInode;
    // First get the Inode by going through the path
    displayedInode = this.getInode(path);
    // Check if the path leads to an Inode
    if (displayedInode != null) {
      // print the path (not Inode's absolute path)
      returnStr = path;
      // if a Directory add its contents
      if (displayedInode instanceof Directory) {
        returnStr += (":" +'\n' + displayedInode.toString());
      } else if (displayedInode instanceof File) {
        returnStr += (":" + '\n' + ((File) displayedInode).getContent());
      }
    // If not a directory print error message
    } else {
      returnStr = "ls: cannot access " + path + " No such file or directory \n";
    }
    // return string
    return returnStr;
  }

  /**
   * Display the string representations of multiple inodes at locations
   * given in paths
   * @param paths
   * @return Total string representations of all inodes found + error messages
   */
  public String displayInodes(String[] paths) {
    // Initialize String for all outputs
    String output = "";
    // Go over each path in paths
    for (int i = 1; i < paths.length; i++) {
      // Add the string output for that Inode to the total output
      output += this.displayInode(paths[i]);
    }
    // return total output
    return output;
  }

  /**
   * Displays the content of a file in the file system at the given path
   * @param path either absolute or relative to get to file in file system
   * @return String containing all of the file's contents
   */
  public String displayFileContent(String path) {
    // Initialize string output and Inode at path
    File file; //
    String output = "";
    // Get inode from path
    file = this.getFile(path);
    
    output = file.getContent();
    
    // return contents
    return output;
  }

  /**
   * Sets the currentDirectory to Directory at path, if it exists
   * AS a directory
   * @param path
   */
  public void goToDirectory(String path) {
    // Get the Inode selected by Path
    Inode destinationDirectory = this.getInode(path);
    // Check if Inode is actually a directory
    if (destinationDirectory instanceof Directory) {
      // Set the current Directory to be the path's Inode
      this.setCurrentDirectory((Directory) destinationDirectory);
    }
  }

  /**
   * 
   * @return the currentDirectory
   */
  public Directory getCurrentDirectory() {
    return this.currentDirectory;
  }

  /**
   * 
   * @param newCurrent Directory
   */
  public void setCurrentDirectory(Directory newCurrent) {
    this.currentDirectory = newCurrent;
  }

  /**
   * 
   * @return the rootDirectory
   */
  public Directory getRootDirectory() {
    return this.rootDirectory;
  }

  /**
   * 
   * @param newRoot Directory
   */
  public void setRootDirectory(Directory newRoot) {
    this.rootDirectory = newRoot;
  }
}
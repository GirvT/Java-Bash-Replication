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
 * Cd Commnd class for changing current directory of shell
 * @author Conroy
 *
 */
public class Cd extends Command{
  private FileSystem fileSystem;

    /**
     * Constructor for 
     * @param fileSystem that shell modifies
     */
    public Cd(FileSystem fileSystem) {
      this.fileSystem = fileSystem;
   }

   /**
    * executes cd command, to change to new directory of first argument
    * @param inputParameters for string arguments to change directory
    * of the first string argument
    */
   public String execute(String[] inputParameters) {
     // Initialize filesystem Inode variables and output
     Directory curr = fileSystem.getCurrentDirectory();
     Directory root = fileSystem.getRootDirectory();
     Directory newCurrent;
     String output = "";
     
     // Verify appropriate arguments are present
     if (inputParameters.length <= 1){
       output = "Invalid arguments for cd\n";
     // Check cd .. case
     } else if (inputParameters[1].equals("..")) {
       // Check if it is the root Directory, if not then go to parent directory
       if (curr.getPath() != root.getPath()) {
         fileSystem.setCurrentDirectory(curr.getParentDirectory());
       } else {
         // If it is the root directory, return an error String
         output = "root directory does not have parent directory\n";
       }
     // Check cd "/" case for root
     } else if ((inputParameters[1] == "/")) {
       this.fileSystem.setCurrentDirectory(root);
     // Otherwise we assume it is a path
     } else {
       // get the directory at the path
       newCurrent = this.fileSystem.getDirectory(inputParameters[1]);
       // If the Directory exists set it as the new current directory
       if (newCurrent != null) {
         this.fileSystem.setCurrentDirectory(newCurrent);
       } else {
         // Otherwise return an error string
         output = "directory " + inputParameters[1] + " is not a directory\n";
       }
     }
     return output;
   }
}

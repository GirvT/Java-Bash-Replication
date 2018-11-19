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
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import filesystem.Inode;


public class Cat extends Command{

  /**
   * Cat retrieves the contents of a list of files, and returns them in the 
   * form of a String, with 3 line breaks in between every File's contents 
   * 
   * @author Lim Zhi Hua
   *
   */
  private FileSystem fileSystem;
  
  /**
   * Constructor for the command
   * 
   * @param fileSystem a reference to the original fileSystem
   */
  public Cat(FileSystem fileSystem ) {
    this.fileSystem = fileSystem;
  }

  /**
   * 
   * @param inputParameters contains the files whose contents we want 
   *        displayed
   * @return returns the contents of the files in the form of a String, with
   * 		 the contents separated by 3 line breaks
   * 		 returns an error message instead if the file we are looking for
   * 		 does not exist
   */
  public String execute(String[] inputParameters) {
    // Get our current directory
    Directory current = fileSystem.getCurrentDirectory();
    // Go through each item in our inputParameters
    // Create a String which will be used to store the fileContents
    String fileContents = "";
    Inode temp;

    for(int i = 1; i < inputParameters.length ; i++) {
      // temporarily store each Inode object 
    	temp = current.getSubInode(inputParameters[i]);

      // check if the Inode object we are looking at is a file
    	if(!(temp instanceof File )) {
        // If it isn't a file, then tell the user about it
    		
    		// If we are on the last file, we dont need a line break after it
    		if(i == inputParameters.length -1) {
        		fileContents = (fileContents + inputParameters[i]
        				+ " is not a file");
    		}
    		else {
    		fileContents = (fileContents + inputParameters[i]
    				+ " is not a file\n");
    		}
    	}
    	else {
    		// If we are on the last file, we dont need a line break after it
    		if( i == inputParameters.length -1)
	    		// If it does exist, then display the file contents
	    		fileContents = fileContents + ((File)temp).getContent();
    		else {
	    		// Have 3 line breaks in between each file's contents if
    			// we aren't on the last file
	    		fileContents = fileContents + ((File)temp).getContent() +  
	    				"\n\n\n";  
    		}
    	}
    }
    return fileContents;

  }
}

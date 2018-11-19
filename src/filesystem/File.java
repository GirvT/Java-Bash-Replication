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
 * File Class extends Inode, acts as a "file" that stores string
 * information given to the file
 * @author Girvan
 *
 */
public class File extends Inode {
	private String content;
	/**
	 * Constructor
	 * @param parentDirectory     Parent Directory
	 * @param name                File Name
	 * @param cinput 			  Content input of the File
	 */
	public File(Directory parentDirectory, String name, String cinput) {
		super(parentDirectory, name);
		this.setContent(cinput);
	}
	
	/**
	  * @return return the content currently set
	  */
	public String getContent() {
		return this.content;
	}
	
	/**
	  * @param cinput set new content
	  */
	public void setContent(String cinput) {
		this.content = cinput;
	}
	
	/**
	  * @param cinput append the new content 
	  */
	public void appendContent(String cinput) {
		this.content = this.content + cinput;
	}
}
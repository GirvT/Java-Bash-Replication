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
package display;

public class Display {
	  /**
	   * Receives a String from other classes and prints it out what it
	   * received isn't the empty String 
	   * 
	   * @author Lim Zhi Hua
	   *
	   *
	
	  /**
	   * 
	   * @param outputValue is the String which will be printed out on a
	   *		new line (Unless its equal to the empty String)
	   *
	   */
  public static void displayItem(String outputValue) {
		if(outputValue.length() != 0) {
		    System.out.println(outputValue);
		}
  }
  /**
   * 
   * @param outputValue is the String which will be printed out on the
   *		same line (Unless its equal to the empty String)
   *
   */
  
  public static void displayItemSameLine(String outputValue) {
		if(outputValue.length() != 0) {
		    System.out.print(outputValue);

		}  
  }
}

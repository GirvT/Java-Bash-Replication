package errors;

public class MissingFileNameException extends Exception {
	/**
	 * Error is thrown if the user enters a > or >> without specifying a file
	 * directly afterwards
	 * 
	 * @author Zhi Hua
	 *
	 */
		  
	  /**
	   * Constructor
	   * @param errorMessage
	   */
	  public MissingFileNameException(String errorMessage) {
		super(errorMessage);
	  }
		
}

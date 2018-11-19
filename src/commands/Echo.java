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

import filesystem.File;
import filesystem.FileSystem;
import commands.Pwd;
/**
 * Echo Class depending on the input returns an output
 * if the input is a array of strings with echo followed by
 * any number of strings possibly having a ">" or ">>" string
 * that denotes the output should override/append to the filename 
 * after the ">" or ">>". Where ">" overrides the content 
 * and ">>" appends the content.
 * @author Girvan Tse
 *
 */
public class Echo extends Command{
	private FileSystem fileSystem;
	/**
	 * Constructor
	 * @param fileSystem set the current file system that echo will be using
	 */
	public Echo(FileSystem fileSystem) 
	{
		this.fileSystem = fileSystem;
	}
	
	/**
	 * @param args		array of strings starting with "echo" and possbily
	 * 					containing an ">" or ">>" string to override or
	 * 					append to the file coming directly after
	 * @return 			Returns a string of some form, dependant on the input
	 */
	public String execute(String args[])
	{
		//Start at index 1 since we expect good input
		//specifically "echo" starts our array
		int i = 1;
		String inputStr = "";
		String putStr = "";
		Boolean putBool = false;
		//Compile our input string, if we do not read the special strings
		//">" or ">>" continue appending until the end of the array
		while(i < args.length && !args[i].equals(">") && !args[i].equals(">>"))
		{
			if(inputStr == "")
			{ 
				//We do not want unneeded whitespace at the start
				if(args[i].startsWith("\"") && args[i].endsWith("\"")
					&& !putBool)
				{
					putStr = args[i];
					putBool = true;
					inputStr = args[i].substring(1, args[i].length() - 1);
				} else {
					inputStr = args[i];
				}
			}
			else
			{
				inputStr = inputStr + " " + args[i];
			}
			i++;
		}
		//We want to see if element i is the one before ">",">>", or 
		//out of bounds exception, which means ">" or ">>" wasn't present
		try 
		{
			//Replace content detected
			if(args[i].equals(">"))
			{
				try
				{
					//If our file dosen't exist, create it
					if(fileFinder(args[i+1]) == null)
					{
						fileSystem.addFile(args[i+1]);
					}
					//Replace content of the file
					fileFinder(args[i+1]).setContent(putStr);
				}
				//If we try args[i+1] and it doesn't exist then we were not
				//given an outfile and cannot continue
				catch (ArrayIndexOutOfBoundsException e)
				{
					return "Syntax Error, no OUTFILE given";
				}
			}
			//Append content detected
			else if (args[i].equals(">>"))
			{
				try
				{
					//If our file dosen't exist, create it
					if(fileFinder(args[i+1]) == null)
					{
						fileSystem.addFile(args[i+1]);
					}
					//Append content to the file
					fileFinder(args[i+1]).appendContent(putStr);
				}
				//If we try args[i+1] and it doesn't exist then we were not
				//given an outfile and cannot continue
				catch (ArrayIndexOutOfBoundsException e)
				{
					return "Syntax Error, no OUTFILE given";
				}
			}
			//Return an empty string so we do not print the string
			return "";
		}
		//If we get out of bounds it means we dont have to replace or append
		//so just print the string by returning it
		catch (ArrayIndexOutOfBoundsException e)
		{
			return inputStr;
		}
	}

	/**
	 * @param fileName 	set the current filesystem that echo will be using
	 * @return 			returns the file if the filename exists in the
	 * 					file system declared at creation
	 */
	private File fileFinder(String fileName)
	{
		//Get working directory path
		Pwd wdPath = new Pwd(fileSystem);
		String[] temp =  new String[1];
		String filePath = wdPath.execute(temp);
		
		//Append our filename to the working directory
		filePath = filePath + "/" + fileName;
		
		//Attempt to return the file if it exists, otherwise expect null
		return fileSystem.getFile(filePath);
	}
}
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

import java.util.Hashtable;

/**
 * Manaul is a command which retrieves the manual of the the command asked to it.
 * 
 * @author Manish Suresh
 */
public class Manual extends Command {
  private Hashtable<String, String> manuals = new Hashtable<String, String>();

  /**
   * Constructor
   */
  public Manual() {
    addCommands();
  }

  /**
   * @param arguments may contain the input to which the respective manual should be returned.
   * @return returns the appropriate data depending on the input.
   */
  public String execute(String[] arguments) {
    // variable to store the information about the return statement
    String info = null;
    try {
      // get the manual for the given input
      info = manuals.get(arguments[1]);

      // check if the manual exists for the given input
      if (info == null) {
        info = "No manual entry for " + arguments[1];
      }
    // catch the error if no input is given
    } catch (ArrayIndexOutOfBoundsException e) {
      info = "Please also enter what manual page you want";
    }
    return info;
  }

  /**
   * Initialization by adding all commands to manual, Driver function
   */
  private void addCommands() {
    addCatManual();
    addCdManual();
    addEchoManual();
    addExitManual();
    addHistoryManual();
    addLsManual();
    addManManual();
    addMkdirManual();
    addPopdManual();
    addPushdManual();
    addPwdManual();

  }

  /**
   * Adding Cat to manual
   */
  private void addCatManual() {
    manuals.put("cat",
        "NAME:\r\n" + "    cat\r\n" + "\r\n" + "SYNTAX:\r\n" + "    cat FILE1 [FILE2 ...]\r\n"
            + "\r\n" + "DESCRIPTION:\r\n"
            + "    Concatenate and display the contents of FILE1 and optional files [FILE2 ...] "
            + "to standard output.\r\n"
            + "    The contents of each file are separated by three line breaks.\r\n" + "\r\n"
            + "EXAMPLES:\r\n"
            + "    cat cs.txt - displays the contents of file cs.txt onto standard output\r\n"
            + "    cat cs.txt mh.txt - displays the contents of files cs.txt and mh.txt onto "
            + "standard output");
  }

  /**
   * Adding Cd to manual
   */
  private void addCdManual() {
    manuals.put("cd",
        "NAME:\r\n" + "    cd\r\n" + "\r\n" + "SYNTAX:\r\n" + "    cd DIR\r\n" + "\r\n"
            + "DESCRIPTION:\r\n"
            + "    Changes the current working directory to DIR where DIR is a sub directory "
            + "to current directory or a full path.\r\n"
            + "    DIR can be replaced with the following symbols\r\n"
            + "    .. - represents the parent directory.\r\n"
            + "    .  - represents the current directory.\r\n"
            + "    /  - represents the root directory\r\n" + "\r\n" + "EXAMPLES:\r\n"
            + "    cd Desktop - changes the current directory to Desktop\r\n"
            + "    cd /Documents/CS/ - changes the current working directory to CS directory "
            + "located in the Documents directory");
  }

  /**
   * Adding Echo to manual
   */
  private void addEchoManual() {
    manuals.put("echo",
        "NAME:\r\n" + "    echo\r\n" + "\r\n" + "SYNTAX:\r\n" + "    echo STRING [> OUTIFLE]\r\n"
            + "\r\n" + "DESCRIPTION:\r\n"
            + "    Display the STRING to the standard output if OUTFILE is not present otherwise "
            + "display the STRING in the OUTFILE.\r\n"
            + "    STRING is a string of characters surrounded by double quotation marks.\r\n"
            + "    This creates a new file if OUTFILE does not exist and erases the old contents "
            + "if OUTFILE already exists.\r\n" + "\r\n" + "EXAMPLE:\r\n"
            + "    echo \"echo\" - displays the string \"echo\" in the shell\r\n"
            + "    echo \"computer science\" > cs.txt - displays the string \"computer science\" "
            + "in the file cs.txt");
  }

  /**
   * Adding exit to manual
   */
  private void addExitManual() {
    manuals.put("exit", "NAME:\r\n" + "    exit\r\n" + "\r\n" + "SYNTAX:\r\n" + "    exit\r\n"
        + "\r\n" + "DESCRIPTION:\r\n" + "    exits out of the current program");
  }

  /**
   * Adding History to manual
   */
  private void addHistoryManual() {
    manuals.put("history", "NAME:\r\n" + "    history\r\n" + "\r\n" + "SYNTAX:\r\n"
        + "    history [NUMBER]\r\n" + "\r\n" + "DESCRIPTION:\r\n"
        + "    This command will display the recent commands used (one command per line) to the "
        + "terminal.\r\n"
        + "    The NUMBER tells how many commands to be displayed (the default number of commands"
        + " displayed is 5).\r\n" + "\r\n" + "EXAMPLES:\r\n"
        + "    history - displays 5 recently used commands to the terminal\r\n"
        + "    history 3 - displays 3 recently used commnads to the terminal");
  }

  /**
   * Adding Ls to manual
   */
  private void addLsManual() {
    manuals.put("ls",
        "NAME:\r\n" + "    ls\r\n" + "\r\n" + "SYNTAX:\r\n" + "    ls [PATH]\r\n" + "\r\n"
            + "DESCRIPTION:\r\n"
            + "    List information about the FILEs (the current directory by default).\r\n"
            + "    Otherwise, for each path p, the order \r\n"
            + "    > if p leads to a file, prints p\r\n"
            + "    > if p leads to a directory, prints p then the contents of that directory\r\n"
            + "    > if p does not exist print a suitable message.\r\n" + "\r\n" + "EXMAPLE:\r\n"
            + "    ls - returns all the files in the current directory\r\n"
            + "    ls /users/Desktop - returns all the files in the desktop");
  }

  /**
   * Adding Man to manual
   */
  private void addManManual() {
    manuals.put("man",
        "NAME:\r\n" + "    man\r\n" + "\r\n" + "SYNTAX:\r\n" + "    man CMD\r\n" + "\r\n"
            + "DESCRIPTION:\r\n" + "    Display documentation CMD to the terminal\r\n" + "\r\n"
            + "EXAMPLE:\r\n" + "    man echo - displays documentation for echo");
  }

  /**
   * Adding Cat to manual
   */
  private void addMkdirManual() {
    manuals.put("mkdir", "NAME:\r\n" + "    mkdir\r\n" + "\r\n" + "SYNTAX:\r\n"
        + "    mkdir [PATH]DIR\r\n" + "\r\n" + "DESCRIPTION:\r\n"
        + "    creates a directory with name DIR which may be in the current directory "
        + "or at the given PATH.\r\n" + "\r\n" + "EXAMPLE:\r\n"
        + "    mkdir cscb07 - creates a directory named \"cscb07\" in the current "
        + "directory\r\n" + "    mkdir Desktop/cs - creates a directory named \"cs\" in Desktop.");
  }

  /**
   * Adding Popd to manual
   */
  private void addPopdManual() {
    manuals.put("popd",
        "NAME:\r\n" + "    popd\r\n" + "\r\n" + "SYNTAX:\r\n" + "    popd\r\n" + "\r\n"
            + "DESCRIPTION:\r\n"
            + "    Removes the top most directory stack and makes it the current working"
            + " directory.\r\n"
            + "    The directory if consistent with the behaviour if a LIFO stack.");
  }

  /**
   * Adding Pushd to manual
   */
  private void addPushdManual() {
    manuals.put("pushd", "NAME:\r\n" + "    pushd\r\n" + "\r\n" + "SYTNAX:\r\n"
        + "    pushd DIR\r\n" + "\r\n" + "DESCRIPTION:\r\n"
        + "    Saves the current working directory by putting it into a directory stack so that "
        + "it can be accessed later (popd command). Sets DIR as the new working directory where "
        + "DIR might be relative to current directory or a full path.\r\n"
        + "    The directory stack is consitent with the behaviour of LIFO stack and stack itself "
        + "is dynamic.\r\n" + "\r\n" + "EXAMPLE:\r\n"
        + "    pushd cs - puts the current working directory in the directory stack and sets cs"
        + " as the current working directory\r\n" + "");
  }

  /**
   * Adding Pwd to manual
   */
  private void addPwdManual() {
    manuals.put("pwd", "NAME:\r\n" + "    pwd\r\n" + "\r\n" + "SYNTAX:\r\n" + "    " + "pwd\r\n"
        + "\r\n" + "DESCRIPTION:\r\n" + "    Displays the path to the current working directory");
  }
}

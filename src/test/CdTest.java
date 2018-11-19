package test;

//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import commands.*;
import filesystem.*;

/**
 * Unit Test Class for Cd Class
 * @author Conroy
 *
 */
public class CdTest {
  Cd cd;
  FileSystem fileSystem;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    cd = new Cd(fileSystem);
  }

  /**
   * tests for no arguments with cd
   */
  @Test
  public void testEmptyCd() {
    String output;
    String expected;
    String [] arguments = new String[1];
    arguments[0] = "cd";
    output = cd.execute(arguments);
    expected = "Invalid arguments for cd\n";
    assertEquals(output, expected);
  }
  
  /**
   * tests for no Directory existing for cd
   */
  @Test
  public void testNoDirectoryCd() {
    String output;
    String expected;
    String [] arguments = new String[2];
    arguments[0] = "cd";
    arguments[1] = "hello";
    output = cd.execute(arguments);
    expected = "directory hello is not a directory\n";
    assertEquals(output, expected);
  }
  
  /**
   * tests for changing to root directory
   */
  @Test
  public void testRootCd() {
    String output;
    String expected;
    String [] arguments = new String[2];
    arguments[0] = "cd";
    arguments[1] = "~";
    output = cd.execute(arguments);
    expected = "";
    assertEquals(output, expected);
    Directory root = fileSystem.getRootDirectory();
    Directory current = fileSystem.getCurrentDirectory();
    assertEquals(root, current);
  }
  
  /**
   * tests for Cd pathing works for normal case
   */
  @Test
  public void testCdPathWorks() {
    String output;
    String expected;
    String [] arguments = new String[2];
    fileSystem.addDirectory("hey");
    arguments[0] = "cd";
    arguments[1] = "hey";
    output = cd.execute(arguments);
    expected = "";
    assertEquals(output, expected);
    assertEquals(fileSystem.getCurrentDirectory().getName(), "hey");
  }
  
  /**
   * tests for checking the parent directory
   */
  @Test
  public void testCdParentDirectory() {
    String output;
    String expected;
    String [] arguments1 = new String[2];
    String [] arguments2 = new String[2];
    fileSystem.addDirectory("rabbits");
    fileSystem.addDirectory("rabbits/are");
    fileSystem.addDirectory("rabbits/are/cute");
    arguments1[0] = "cd";
    arguments1[1] = "rabbits/are/cute";
    arguments2[0] = "cd";
    arguments2[1] = "..";
    output = cd.execute(arguments1);
    expected = "";
    assertEquals(output, expected);
    output = cd.execute(arguments2);
    assertEquals(output, expected);
    assertEquals(fileSystem.getCurrentDirectory().getName(), "are");
  }

  /**
   * tests for if we try to get the parent directory of the root directory
   */
  @Test
  public void testCdParentOfRootDirectory() {
    String output;
    String expected;
    String [] arguments = new String[2];
    arguments[0] = "cd";
    arguments[1] = "..";
    output = cd.execute(arguments);
    expected = "root directory does not have parent directory\n";
    assertEquals(output, expected);
  }

  /**
   * tests for if cd is provided unnecessary arguments to function properly
   */
  @Test
  public void testCdMultipleArguments() {
    String output;
    String expected;
    String [] arguments = new String[4];
    fileSystem.addDirectory("hares");
    arguments[0] = "cd";
    arguments[1] = "hares";
    arguments[2] = " are";
    arguments[3] = " so fast!";
    output = cd.execute(arguments);
    expected = "";
    assertEquals(output, expected);
    assertEquals(fileSystem.getCurrentDirectory().getName(), "hares");
  }
}

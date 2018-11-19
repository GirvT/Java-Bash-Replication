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
 * Unit Test Class for Ls Class
 * @author Conroy
 *
 */
public class LsTest {
  Ls ls;
  FileSystem fileSystem;
  
  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    ls = new Ls(fileSystem);
  }

  /**
   * test Blank Arguments for Ls
   */
  @Test
  public void testBlankArguments() {
    String output;
    String expected;
    String[] arguments = new String[1];
    fileSystem.addDirectory("bunny");
    fileSystem.addDirectory("hares");
    arguments[0] = "ls";
    //arguments[1] = "";
    expected = "~:\nbunny hares\n";
    output = ls.execute(arguments);
    assertEquals(output, expected);
  }

  /**
   * test a single directory for ls
   */
  @Test
  public void testDirectory() {
    String output;
    String expected;
    String[] arguments = new String[2];
    fileSystem.addDirectory("laporidae");
    fileSystem.addDirectory("laporidae/hares");
    fileSystem.addDirectory("laporidae/bunny");
    fileSystem.addDirectory("laporidae/rabbit");
    arguments[0] = "ls";
    arguments[1] = "laporidae";
    expected = "laporidae:\nbunny hares rabbit\n";
    output = ls.execute(arguments);
    assertEquals(output, expected);
  }

  /**
   * test a single file for ls
   */
  @Test
  public void testFile() {
    String output;
    String expected;
    String[] arguments = new String[2];
    fileSystem.addFile("laporidae.txt");
    File file = fileSystem.getFile("laporidae.txt");
    file.setContent("Rabbits are not food");
    arguments[0] = "ls";
    arguments[1] = "laporidae.txt";
    expected = "laporidae.txt:\nRabbits are not food";
    output = ls.execute(arguments);
    assertEquals(output, expected);
  }

  /**
   * test a nested file in ls
   */
  @Test
  public void testInnerFile() {
    String output;
    String expected;
    String[] arguments = new String[2];
    fileSystem.addDirectory("laporidae");
    fileSystem.addDirectory("laporidae/bunny");
    fileSystem.addDirectory("laporidae/bunny/rabbit");
    arguments[0] = "ls";
    arguments[1] = "laporidae/bunny";
    expected = "laporidae/bunny:\nrabbit\n";
    output = ls.execute(arguments);
    assertEquals(output, expected);
  }

  /**
   * test a nonexistent file for ls
   */
  @Test
  public void testNonExistentDirectory() {
    String output;
    String expected;
    String[] arguments = new String[2];
    arguments[0] = "ls";
    arguments[1] = "laporidae";
    expected = "ls: cannot access laporidae No such file or directory \n";
    output = ls.execute(arguments);
    assertEquals(output, expected);
  }

  /**
   * test multiple arguments for ls
   */
  @Test
  public void testMultipleArguments() {
    String output;
    String expected;
    String[] arguments = new String[4];
    fileSystem.addDirectory("hares");
    fileSystem.addDirectory("bunny");
    fileSystem.addDirectory("rabbit");
    fileSystem.addDirectory("hares/agile");
    fileSystem.addDirectory("bunny/cute");
    fileSystem.addDirectory("rabbit/regular");
    arguments[0] = "ls";
    arguments[1] = "bunny";
    arguments[2] = "hares";
    arguments[3] = "rabbit";
    expected = "bunny:\ncute\nhares:\nagile\nrabbit:\nregular\n";
    output = ls.execute(arguments);
    assertEquals(output, expected);
  }
}

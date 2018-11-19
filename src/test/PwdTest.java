/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commands.Cat;
import commands.Pwd;
import filesystem.FileSystem;

/**
 * Contains the various tests for the Pwd class
 * 
 * @author Lim Zhi Hua
 *
 */
class PwdTest {

	Pwd p1;
	FileSystem fs1;
	String[] inputCommands;
	
	@BeforeEach
	void setUp() throws Exception {
		fs1 = new FileSystem();
		p1 = new Pwd(fs1);

	}

	// Testing pwd when in the root
	@Test
	void testRoot() {
		inputCommands = new String[1];
		inputCommands[0] = "Pwd";
		assertEquals("~", p1.execute(inputCommands));
	}
	
	// Testing pwd when we are inside another directory
	@Test
	void testInOneDirectory() {
		inputCommands = new String[1];
		inputCommands[0] = "Pwd";
		fs1.addDirectory("~/temp");
		fs1.goToDirectory("~/temp");
		assertEquals("~/temp", p1.execute(inputCommands));
	}
	
	// Testing pwd when we are in multiple directories
	@Test
	void testInMultipleDirectories() {
		inputCommands = new String[1];
		inputCommands[0] = "Pwd";
		fs1.addDirectory("~/temp");
		fs1.addDirectory("~/temp/temp2");
		fs1.addDirectory("~/temp/temp2/temp3");
		fs1.goToDirectory("~/temp/temp2/temp3");
		assertEquals("~/temp/temp2/temp3", p1.execute(inputCommands));
	}
	
	// Same as all of the above, except with an extra input parameter
	@Test
	void testRootWithExtraInput() {
		inputCommands = new String[2];
		inputCommands[0] = "Pwd";
		inputCommands[1] = "Useless";
		assertEquals("~", p1.execute(inputCommands));
	}
	
	@Test
	void testInOneDirectoryWithExtraInput() {
		inputCommands = new String[2];
		inputCommands[0] = "Pwd";
		inputCommands[1] = "Useless";
		fs1.addDirectory("~/temp");
		fs1.goToDirectory("~/temp");
		assertEquals("~/temp", p1.execute(inputCommands));
	}
	
	@Test
	void testInMultipleDirectoriesWithExtraInput() {
		inputCommands = new String[2];
		inputCommands[0] = "Pwd";
		inputCommands[1] = "Useless";
		fs1.addDirectory("~/temp");
		fs1.addDirectory("~/temp/temp2");
		fs1.addDirectory("~/temp/temp2/temp3");
		fs1.goToDirectory("~/temp/temp2/temp3");
		assertEquals("~/temp/temp2/temp3", p1.execute(inputCommands));
	}

}

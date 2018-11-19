package test;

import static org.junit.Assert.*;
import org.junit.*;
import commands.Cat;
import filesystem.File;
import filesystem.FileSystem;

public class CatTest {
	/**
	 * Performs various tests on the Cat Class
	 * @author Lim Zhi Hua
	 *
	 */
	Cat c1;
	FileSystem fs1;
	
	/**
	 * Be sure to create the FileSystem we are using as well as the Cat object
	 * before each test
	 *
	 */
	@Before
	public void setup() {
		fs1 = new FileSystem();
		c1 = new Cat(fs1);
	}
	
	// A test for calling Cat when there are no input parameters
	@Test
	public void testEmpty() {
		String[] value = new String[1];
		value[0] = "cat";
		assertEquals(c1.execute(value),"");
	}
	
	// Test for calling cat when there is only a single file
	@Test
	public void testSingleFile() {
		String[] value = new String[2];
		fs1.addFile("~/testFile");
		value[0] = "cat";
		value[1] = "testFile";
		File f1 = fs1.getFile("~/testFile");
		f1.setContent("File Data");
		assertEquals(c1.execute(value), "File Data");		
	}
	
	// Test for calling cat for multiple files
	@Test
	public void testMultipleFiles() {
		String[] value = new String[4];
		fs1.addFile("~/testFile");
		fs1.addFile("~/testFile2");
		fs1.addFile("~/testFile3");
		value[0] = "cat";
		value[1] = "testFile";
		value[2] = "testFile2";
		value[3] = "testFile3";

		File f1 = fs1.getFile("~/testFile");
		File f2 = fs1.getFile("~/testFile2");
		File f3 = fs1.getFile("~/testFile3");

		f1.setContent("File Data");
		f2.setContent("File Data2");
		f3.setContent("File Data3");

		assertEquals(c1.execute(value), 
				"File Data\n\n\nFile Data2\n\n\nFile Data3");
	}
	
	// Test for calling cat using a file that doesn't exist
	@Test
	public void testNonExistantFile() {
		String[] value = new String[2];
		value[0] = "cat";
		value[1] = "Fake";

		assertEquals(c1.execute(value), "Fake is not a file");
	}
	
	// Test for calling cat with a real file first followed by a non-existent
	// one
	@Test
	public void testRealPlusNonExistentFile() {
		String[] value = new String[3];
		value[0] = "cat";
		value[1] = "testFile";
		value[2] = "Fake";
		
		fs1.addFile("~/testFile");
		File f1 = fs1.getFile("~/testFile");
		f1.setContent("File Data");
		assertEquals(c1.execute(value), "File Data\n\n\nFake is not a file");
	}
	
	// Test for calling cat with a non-existent file followed by a real file.
	@Test
	public void testNonExistentPlusRealFile() {
		String[] value = new String[3];
		value[0] = "cat";
		value[1] = "Fake";
		value[2] = "testFile";

		fs1.addFile("~/testFile");
		File f1 = fs1.getFile("~/testFile");
		f1.setContent("File Data");
		System.out.println(c1.execute(value));
		assertEquals(c1.execute(value), "Fake is not a file\nFile Data");
	}
	
	// Test for calling cat with a real file sandwiched between to non-existent
	// ones
	@Test
	public void testNonExistentPlusRealPlusNonExistentFile() {
		String[] value = new String[4];
		value[0] = "cat";
		value[1] = "Fake";
		value[2] = "testFile";
		value[3] = "Fake2";

		fs1.addFile("~/testFile");
		File f1 = fs1.getFile("~/testFile");
		f1.setContent("File Data");
		System.out.println(c1.execute(value));
		assertEquals(c1.execute(value), "Fake is not a "
				+ "file\nFile Data\n\n\nFake2 is not a file");
	}
	
	// Test for calling cat with a non-existent file sandwiched between two 
	// real ones
	@Test
	public void testRealPlusNonExistantPlusRealFile() {
		String[] value = new String[4];
		value[0] = "cat";
		value[1] = "testFile";
		value[2] = "Fake";
		value[3] = "testFile2";

		fs1.addFile("~/testFile");
		fs1.addFile("~/testFile2");

		File f1 = fs1.getFile("~/testFile");
		File f2 = fs1.getFile("~/testFile2");

		f1.setContent("File Data");
		f2.setContent("File Data2");

		System.out.println(c1.execute(value));
		assertEquals(c1.execute(value), "File Data\n\n\nFake is not a "
				+ "file\nFile Data2");
	}
	
}

package test;

import static org.junit.Assert.*;
import org.junit.*;

import filesystem.FileSystem;
import commands.Echo;

public class EchoTest {
	/**
	 * Performs various tests on the Echo Class
	 * @author Girvan Tse
	 */
	Echo eko;
	FileSystem fs;
	
	/**
	 * Create filesystem to input to echo
	 */
	@Before
	public void setup() {
		fs = new FileSystem();
		eko = new Echo(fs);
	}

	//Empty Echo call
	@Test
	public void testEmpty() {
		String[] input = {"echo"};
		assertEquals(eko.execute(input),"");
	}
	
	//Echo Call
	@Test
	public void testPrintSingle() {
		String[] input = {"echo","hello"};
		assertEquals(eko.execute(input),"hello");	
	}
	
	//Echo Multi Word Call
	@Test
	public void testPrintMulti() {
		String[] input = {"echo","hello","world"};
		assertEquals(eko.execute(input),"hello world");	
	}
	
	//Echo Whitespace Call
	@Test
	public void testPrintWhitespaceSingle() {
		String[] input = {"echo","hello            ",};
		assertEquals(eko.execute(input),"hello");	
	}
		
	//Echo Whitespace Multiword Call
	@Test
	public void testPrintWhitespaceMulti() {
		String[] input = {"echo","hello            ","world                 "};
		assertEquals(eko.execute(input),"hello world");	
	}
	
}
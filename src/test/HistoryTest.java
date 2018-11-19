/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commands.History;
/**
 * Performs various tests on the history class
 * @author Lim Zhi Hua
 *
 */
public class HistoryTest {
	
	History h1;
    ArrayList<String> commandHistory = new ArrayList<String>();

	
	@Before
	public void setup() {
		h1 = new History(commandHistory);
	}
	
	// Reset the list of commands after each test

	
	// Test for displaying all the items when there is only one item in the
	// list of commands
	@Test
	public void testHistoryOneItem() {
		String[] value = new String[1];
		value[0] = "history";
		commandHistory.add("Command 1");
		assertEquals(h1.execute(value), "1. Command 1");
	}
	
	// Test for specifying only one item when there is only one item in the
	// list of commands
	@Test
	public void testHistoryOneItemEnterOne() {
		String[] value = new String[2];
		value[0] = "history";
		value[1] = "1";
		commandHistory.add("Command 1");
		assertEquals(h1.execute(value), "1. Command 1");
	}
	
	// Test for no specifications, with multiple items in the list
	@Test
	public void testHistoryMulipleItems() {
		String[] value = new String[1];
		value[0] = "history";
		commandHistory.add("Command 1");
		commandHistory.add("Command 2");
		commandHistory.add("Command 3");
		assertEquals(h1.execute(value), "1. Command 1\n2. Command 2\n3. Command 3");
	}
	
	// Test for specifying all the values in the list with multiple items in
	// the list
	@Test
	public void testHistoryMulipleItemsSelect() {
		String[] value = new String[2];
		value[0] = "history";
		value[1] = "3";
		commandHistory.add("Command 1");
		commandHistory.add("Command 2");
		commandHistory.add("Command 3");
		assertEquals(h1.execute(value), "1. Command 1\n2. Command 2\n3. Command 3");
	}
	
	// Test for specifying some of the values in the list with multiple items
	// in the list
	@Test
	public void testHistoryMulipleSomeSelect() {
		String[] value = new String[2];
		value[0] = "history";
		value[1] = "2";
		commandHistory.add("Command 1");
		commandHistory.add("Command 2");
		commandHistory.add("Command 3");
		assertEquals(h1.execute(value), "2. Command 2\n3. Command 3");
	}
	
	// Test for specifying 0 values in the list with multiple items
	// in the list
	@Test
	public void testHistoryMultipleZeroSelect() {
		String[] value = new String[2];
		value[0] = "history";
		value[1] = "0";
		commandHistory.add("Command 1");
		commandHistory.add("Command 2");
		commandHistory.add("Command 3");
		assertEquals(h1.execute(value), "");
	}
	
	// Test for specifying too many values in the list with multiple items
	// in the list
	@Test
	public void testHistoryMultipleOverflowSelect() {
		String[] value = new String[2];
		value[0] = "history";
		value[1] = "90000";
		commandHistory.add("Command 1");
		commandHistory.add("Command 2");
		commandHistory.add("Command 3");
		assertEquals(h1.execute(value), "1. Command 1\n2. "
				+ "Command 2\n3. Command 3");
	}
	
	// Test for specifying a negative value in the list with multiple items
	// in the list
	@Test
	public void testHistoryMultipleNegativeSelect() {
		String[] value = new String[2];
		value[0] = "history";
		value[1] = "-1";
		commandHistory.add("Command 1");
		commandHistory.add("Command 2");
		commandHistory.add("Command 3");
		assertEquals(h1.execute(value), "-1 is not a positive integer");
	}
	
	
	// Test for specifying a non-integer value in the list with multiple items
	// in the list
	@Test
	public void testHistoryMultipleNonIntSelect() {
		String[] value = new String[2];
		value[0] = "history";
		value[1] = "potatoes";
		commandHistory.add("Command 1");
		commandHistory.add("Command 2");
		commandHistory.add("Command 3");
		assertEquals(h1.execute(value), "potatoes is not a valid integer value");
	}
	
}

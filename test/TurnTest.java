package edu.unlv.cs.whoseturn.test;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TurnTest {

	private Set<String> turnItems;
	private String testString1;
	private String testString2;
	
	@Before
	public void initialize() {
		turnItems = new HashSet<String>();
		testString1 = "test1";
		testString2 = "test2";
	}

	@Test
	public void testAddStrings() {
		String testString = "test";
		turnItems.add(testString);
		assertNotNull(turnItems);
	}
	
	@Test
	public void testGetNumberOfUsers() {
		turnItems.add(testString1);
		turnItems.add(testString2);
		assertEquals(2, turnItems.size());
	}
	

}

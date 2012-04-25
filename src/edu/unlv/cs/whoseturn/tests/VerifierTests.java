package edu.unlv.cs.whoseturn.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.unlv.cs.whoseturn.shared.EntryVerifier;

public class VerifierTests {

	@Test
	public void noDomain() {
		String testEmail = "a@";
		String result = EntryVerifier.isEmailValid(testEmail);
		assertTrue("E-mail has no domain.", result != "Valid");
	}
	
	@Test
	public void noAtSymbol() {
		String testEmail = "a.com";
		String result = EntryVerifier.isEmailValid(testEmail);
		assertTrue("E-mail has no @ symbol.", result != "Valid");
	}
	
	@Test
	public void noDot() {
		String testEmail = "abc@com";
		String result = EntryVerifier.isEmailValid(testEmail);
		assertTrue("E-mail has no dot.", result != "Valid");
	}
	
	// Doesn't work due to database access
	@Test
	public void ValidEmail() {
		String testEmail = "dabinett@unlv.nevada.edu";
		String result = EntryVerifier.isEmailValid(testEmail);
		assertTrue("E-mail is valid.", result == "Valid");
	}

	@Test
	public void usernameTooShort() {
		String testUsername = "a";
		String result = EntryVerifier.isUsernameValid(testUsername);
		assertTrue("Username is too short.", result != "Valid");
	}
	
	@Test
	public void usernameTooLong() {
		String testUsername = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
		String result = EntryVerifier.isUsernameValid(testUsername);
		assertTrue("Username is too long.", result != "Valid");
	}
	
	@Test
	public void usernameInvalidCharacters() {
		String testUsername = "<script> JS breaking code here </script>";
		String result = EntryVerifier.isUsernameValid(testUsername);
		assertTrue("Username has invalid characters.", result != "Valid");
	}
	
	// Doesn't work due to database access
	@Test
	public void validUsername() {
		String testUsername = "SurPrisE";
		String result = EntryVerifier.isUsernameValid(testUsername);
		assertTrue("Username is valid.", result == "Valid");
	}
	
	

	@Test
	public void categoryTooShort() {
		String testCategory = "ab";
		String result = EntryVerifier.isCategoryValid(testCategory);
		assertTrue("Category is too short.", result != "Valid");
	}
	
	@Test
	public void categoryTooLong() {
		String testCategory = "supercalifragilisticexpealadocioussupercalifragilisticexpealadocious";
		String result = EntryVerifier.isCategoryValid(testCategory);
		assertTrue("Category is too long.", result != "Valid");
	}

	@Test
	public void categoryInvalidCharacters() {
		String testCategory = "fred' select table from users delete; --";
		String result = EntryVerifier.isCategoryValid(testCategory);
		assertTrue("Category has invalid characters.", result != "Valid");
	}
	
	// Doesn't work due to database access
	@Test
	public void validCategory() {
		String testCategory = "Beer Run";
		String result = EntryVerifier.isCategoryValid(testCategory);
		assertTrue("Category is valid.", result == "Valid");
	}
	
	
	@Test
	public void underOneHour() {
		Integer testInteger = -5;
		String result = EntryVerifier.isTimeValid(testInteger);
		assertTrue("Time is under 1 hour.", result != "Valid");
	}
	
	@Test
	public void over24Hours() {
		Integer testInteger = 50;
		String result = EntryVerifier.isTimeValid(testInteger);
		assertTrue("Time is over 24 hours.", result != "Valid");
	}

	@Test
	public void validTime() {
		Integer testInteger = 12;
		String result = EntryVerifier.isTimeValid(testInteger);
		assertTrue("Time is valid.", result == "Valid");
	}
}

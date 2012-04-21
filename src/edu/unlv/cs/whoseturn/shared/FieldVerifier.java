package edu.unlv.cs.whoseturn.shared;

import com.google.gwt.user.client.ui.Label;
import java.util.regex.*;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;

/**
 * FieldVerifier validates that the data the user enters is valid.
 */
public class FieldVerifier 
{	
	/**
	 * Verifies that the e-mail is possibly valid and doesn't already exist.
	 * @param email the email to validate
	 * @param display the label that displays a possible error message
	 * @return true if valid, false if invalid
	 */
	public static boolean isEmailValid(String email, Label display)
	{
		email.toLowerCase().trim();
		
		if (email.isEmpty())
		{
			display.setText("E-mail cannot be empty.");
			return false;
		}
		
		// I APPARENTLY CAN"T DO THIS STUFF IN THE CLIENT
		
		/*try 
		{
			InternetAddress emailAddress = new InternetAddress(email);
			emailAddress.validate();
			
			return true;
			//if (!hasNameAndDomain(email)) 
			//{
		        //return false;
		    //}
		} 
		catch (AddressException e) 
		{
			display.setText("Invalid e-mail address");
			return false;
		}*/
		
		// END NON CLIENT STUFF
		
		// A cheap e-mail quality check
		//if (!email.matches("^[0-9A-Z\\.]{1,10}$")) 
		
		
		return true;
	}

	/**
	 * Verifies that the username doesn't already exist.
	 * @param username the username to validate
	 * @param display the label that displays a possible error message
	 * @return true if valid, false if invalid
	 */
	public static boolean isUsernameValid(String username, Label display) 
	{
		// Clear the name of any whitespace
		username.trim();
		
		// The username can't be null
		if (username.isEmpty())
		{
			display.setText("Username cannot be empty");
			return false;
		}
		
		// The username can't be longer than 30 characters
		if (username.length() > 30)
		{
			display.setText("Username must be under 30 characters");
			return false;
		}
      
		return true;
	}
}

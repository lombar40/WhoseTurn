package edu.unlv.cs.whoseturn.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("users")
public interface UsersService extends RemoteService {

	/**
	 * Used to add a guest to the database.
	 * 
	 * @param username Username of the guest.
	 * @return Result as a string.
	 * @throws IllegalArgumentException
	 */
	String addGuest(String username) throws IllegalArgumentException;

	/**
	 * Add a new user to the database.
	 * 
	 * @param username The user to add.
	 * @param email The user's email address. If they are a guest, this is blank.
	 * @param admin Boolean of if they are an admin or not. True for admin status.
	 * @return Returns the user's key string (their id) or an error code.
	 * @throws IllegalArgumentException
	 */
	String addNewUser(String username, String email, Boolean admin) throws IllegalArgumentException;

	/**
	 * Get a list of all guests.
	 * 
	 * @return A list of all the users as a string array.
	 * @throws IllegalArgumentException
	 */
	List<String> findAllGuests() throws IllegalArgumentException;

	/**
	 * Get a list of my guests.
	 * 
	 * @return A list of all the users as a string array.
	 * @throws IllegalArgumentException
	 */
	List<String> findMyGuests() throws IllegalArgumentException;

	/**
	 * Get a list of all users.
	 * 
	 * @return A list of all the users as a string array.
	 * @throws IllegalArgumentException
	 */
	List<String[]> findUsers() throws IllegalArgumentException;

	/**
	 * Get the login url.
	 * 
	 * @param providerName Name of provider, e.g. gmail.
	 * @param location The location to use.
	 * @return The login url as a string.
	 * @throws IllegalArgumentException
	 */
	String getLoginURL(String providerName, String location) throws IllegalArgumentException;
	
	/**
	 * Get the logout url.
	 * 
	 * @param location The location to use.
	 * @return The logout url as a string.
	 * @throws IllegalArgumentException
	 */
	String getLogoutURL(String location) throws IllegalArgumentException;
	
	/**
	 * Get the users name.
	 * 
	 * @return User's name as a string.
	 * @throws IllegalArgumentException
	 */
	String getUsername() throws IllegalArgumentException;

	/**
	 * Used to initialize the database with some information to be used.
	 * 
	 * @throws IllegalArgumentException
	 */
	void initializeServer() throws IllegalArgumentException;

	/**
	 * Checks to see if user is logged in.
	 * 
	 * @return boolean value, true if they are logged in.
	 * @throws IllegalArgumentException
	 */
	Boolean isLoggedIn() throws IllegalArgumentException;
	
}
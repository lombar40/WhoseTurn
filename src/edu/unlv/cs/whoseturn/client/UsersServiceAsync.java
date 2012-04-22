package edu.unlv.cs.whoseturn.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UsersServiceAsync {

	/**
	 * Used to add a guest to the database. Async version.
	 * 
	 * @param username Username of the guest.
	 * @return Result as a string.
	 * @throws IllegalArgumentException
	 */
	void addGuest(String username, AsyncCallback<String> callback) throws IllegalArgumentException;

	/**
	 * Add a new user to the database. Async version.
	 * 
	 * @param username The user to add.
	 * @param email The user's email address. If they are a guest, this is blank.
	 * @param admin Boolean of if they are an admin or not. True for admin status.
	 * @return Returns the user's key string (their id) or an error code.
	 * @throws IllegalArgumentException
	 */
	void addNewUser(String username, String email, Boolean admin, AsyncCallback<String> callback) throws IllegalArgumentException;

	/**
	 * Get a list of all guests. Async version.
	 * 
	 * @return A list of all the users as a string array.
	 * @throws IllegalArgumentException
	 */
	void findAllGuests(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	/**
	 * Get a list of my guests. Async version.
	 * 
	 * @return A list of all the users as a string array.
	 * @throws IllegalArgumentException
	 */
	void findMyGuests(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	/**
	 * Get a list of all users. Async version.
	 * 
	 * @return A list of all the users as a string array.
	 * @throws IllegalArgumentException
	 */
	void findUsers(AsyncCallback<List<String[]>> callback) throws IllegalArgumentException;

	/**
	 * Get the login url. Async version.
	 * 
	 * @param providerName
	 *            Name of provider, e.g. gmail.
	 * @param location
	 *            The location to use.
	 * @return The login url as a string.
	 * @throws IllegalArgumentException
	 */
	void getLoginURL(String providerName, String location, AsyncCallback<String> callback) throws IllegalArgumentException;

	/**
	 * Get the logout url. Async version.
	 * 
	 * @param location The location to use.
	 * @return The logout url as a string.
	 * @throws IllegalArgumentException
	 */
	void getLogoutURL(String location, AsyncCallback<String> callback) throws IllegalArgumentException;

	/**
	 * Get the users name. Async version.
	 * 
	 * @return User's name as a string.
	 * @throws IllegalArgumentException
	 */
	void getUsername(AsyncCallback<String> callback) throws IllegalArgumentException;

	/**
	 * Used to initialize the database with some information to be used. Async version.
	 * 
	 * @throws IllegalArgumentException
	 */
	void initializeServer(AsyncCallback<Void> callback) throws IllegalArgumentException;

	/**
	 * Checks to see if user is logged in. Async version.
	 * 
	 * @return boolean value, true if they are logged in.
	 * @throws IllegalArgumentException
	 */
	void isLoggedIn(AsyncCallback<Boolean> callback) throws IllegalArgumentException;
	
}
package edu.unlv.cs.whoseturn.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface UsersServiceAsync {	
	void isLoggedIn(AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	
	void getLoginURL(String providerName, String location, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void getUsername(AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void getLogoutURL(String location, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void addNewUser(String username, String email, Boolean admin, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void findUsers(AsyncCallback<List<String[]>> callback)
			throws IllegalArgumentException;
	
	void addGuest(String username, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void findAllGuests(AsyncCallback<List<String>> callback)
			throws IllegalArgumentException;
	
	void findMyGuests(AsyncCallback<List<String>> callback)
			throws IllegalArgumentException;
	
	void initializeServer(AsyncCallback<Void> callback)
			throws IllegalArgumentException;
}
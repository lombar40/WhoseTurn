package edu.unlv.cs.whoseturn.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("users")
public interface UsersService extends RemoteService {
	Boolean isLoggedIn() throws IllegalArgumentException;
	String getUsername() throws IllegalArgumentException;
	String getLoginURL(String providerName, String location) throws IllegalArgumentException;
	String getLogoutURL(String location) throws IllegalArgumentException;
	String addNewUser(String username, String email, Boolean admin) throws IllegalArgumentException;
	List<String[]> findUsers() throws IllegalArgumentException;
	String addGuest(String usrename) throws IllegalArgumentException;
}
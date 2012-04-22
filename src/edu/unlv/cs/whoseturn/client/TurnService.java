package edu.unlv.cs.whoseturn.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.unlv.cs.whoseturn.domain.Category;
import edu.unlv.cs.whoseturn.domain.User;


@RemoteServiceRelativePath("turn")
public interface TurnService extends RemoteService {
	String findDriver(List<String> usernames, String category) throws IllegalArgumentException;
	User lowestRatio(List<User> users, Category category) throws IllegalArgumentException;
	User leastRecentlyGone(List<User> users, Category category) throws IllegalArgumentException;
	User chooseRandomUser(List<User> users) throws IllegalArgumentException;
}

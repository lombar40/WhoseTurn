package edu.unlv.cs.whoseturn.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.unlv.cs.whoseturn.domain.Category;
import edu.unlv.cs.whoseturn.domain.User;

public interface TurnServiceAsync {
	void findDriver(List<String> usernames, String categoryName, AsyncCallback<String> callback) throws IllegalArgumentException;
	void lowestRatio(List<User> users, Category category, AsyncCallback<User> callback) throws IllegalArgumentException;
	void leastRecentlyGone(List<User> users, Category category, AsyncCallback<User> callback) throws IllegalArgumentException;
	void chooseRandomUser(List<User> users, AsyncCallback<User> callback) throws IllegalArgumentException;
}

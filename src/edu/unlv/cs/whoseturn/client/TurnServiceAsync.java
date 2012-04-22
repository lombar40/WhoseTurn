package edu.unlv.cs.whoseturn.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TurnServiceAsync {
	void findDriver(List<String> usernames, String categoryName, AsyncCallback<String> callback) throws IllegalArgumentException;
}

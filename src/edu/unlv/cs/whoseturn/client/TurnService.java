package edu.unlv.cs.whoseturn.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("turn")
public interface TurnService extends RemoteService {
	String findDriver(List<String> usernames, String category) throws IllegalArgumentException;
}

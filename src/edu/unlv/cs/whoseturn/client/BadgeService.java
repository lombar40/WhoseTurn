package edu.unlv.cs.whoseturn.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


import edu.unlv.cs.whoseturn.domain.Turn;
import edu.unlv.cs.whoseturn.domain.User;

@RemoteServiceRelativePath("badge")
public interface BadgeService extends RemoteService {
	/**
	 * Used to calculate badges. This method initiates the calculation of all badges.
	 * 
	 * @param turnKeyString The turn's keystring
	 */
	public void calculateBadges(String turnKeyString) throws IllegalArgumentException;
	
}
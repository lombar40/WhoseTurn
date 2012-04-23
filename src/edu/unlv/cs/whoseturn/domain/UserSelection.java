package edu.unlv.cs.whoseturn.domain;

import java.security.DomainCombiner;
import java.util.LinkedList;
import java.util.List;


/***
 * This class is for modeling users and an ephemeral selection state for use with the mobile web application
 */
public class UserSelection {
	public UserSelection(edu.unlv.cs.whoseturn.domain.Fuser user,
			boolean selected) {
		this.user = user;
		this.selected = selected;
	}
	edu.unlv.cs.whoseturn.domain.Fuser user;
	boolean selected;
	
	public String getKeyString() {
		return user.getKeyString();
	}
	
	public String getUsername() {
		return user.getUsername();
	}
	
	public boolean getSelected() {
		return selected;
	}
}

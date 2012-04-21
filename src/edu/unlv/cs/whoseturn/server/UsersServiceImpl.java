package edu.unlv.cs.whoseturn.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.domain.Badge;
import edu.unlv.cs.whoseturn.domain.BadgeAwarded;
import edu.unlv.cs.whoseturn.domain.PMF;

@SuppressWarnings("serial")
public class UsersServiceImpl extends RemoteServiceServlet implements UsersService {

	private static final Map<String, String> openIdProviders;
	static {
		openIdProviders = new HashMap<String, String>();
		openIdProviders.put("google", "www.google.com/accounts/o8/id");
		openIdProviders.put("yahoo", "yahoo.com");
		openIdProviders.put("myspace", "myspace.com");
		openIdProviders.put("aol", "aol.com");
		openIdProviders.put("myopenid", "myopenid.com");
		openIdProviders.put("facebook", "facebook.com");
	}

	public Boolean isLoggedIn() {
		UserService userService = UserServiceFactory.getUserService();

		return userService.isUserLoggedIn();
	}

	public String getUsername() {
		/**
		 * The user auth service.
		 */
		UserService userService = UserServiceFactory.getUserService();
		
		/**
		 * The logged in user.
		 */
		User user = userService.getCurrentUser(); 

		// Ensure the user is logged in
		if (user == null){
			return "UserNotLoggedIn";
		}

		/**
		 * The persistance manager.
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		/**
		 * Create a query to find the user based off the email.
		 */
		Query query = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class, "email == emailParam"); // 
		query.declareParameters("String emailParam"); // Declare the parameter
														// for search

		// Execute the query with the email paramter
		@SuppressWarnings("unchecked")
		List<edu.unlv.cs.whoseturn.domain.User> results = (List<edu.unlv.cs.whoseturn.domain.User>) query.execute(user.getEmail());

		// Check to make sure only one user was found and return the username
		if (results.size() == 1) {
			return results.get(0).getUsername();
		} else if (results.size() == 0) {
			return "UserNotFound";
		} else {
			return "ErrorFindingUser";
		}
	}

	public String getLoginURL(String providerName, String location) {
		UserService userService = UserServiceFactory.getUserService();
		String providerUrl = openIdProviders.get(providerName);
		return userService.createLoginURL(location, null, providerUrl, null);
	}

	public String getLogoutURL(String location) {
		UserService userService = UserServiceFactory.getUserService();

		return userService.createLogoutURL(location, userService.getCurrentUser().getAuthDomain());
	}

	@SuppressWarnings("unchecked")
	public String addNewUser(String username, String email, Boolean admin) {
		/**
		 * Get the persistance manager.
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		/**
		 * Creates a new user object to add
		 */
		edu.unlv.cs.whoseturn.domain.User user = new edu.unlv.cs.whoseturn.domain.User(); 

		// Set properties of the user
		user.setAdmin(admin);
		user.setAvatarBlob(null);
		user.setDeleted(false);
		user.setEmail(email);
		user.setUsername(username);
		user.setBadges(new HashSet<String>());

		/**
		 * Creation of the user's default badges
		 * Query the database for all badge types
		 */
		Query query = pm.newQuery(Badge.class);
		/**
		 * Prepare a results list
		 */
		List<Badge> results;
		/**
		 * Prepare a temporary badgeAwarded to be used for the user
		 */
		BadgeAwarded tempBadgeAwarded;
		
		// Execute the query and set the results
		results = (List<Badge>) query.execute();

		// Make sure badges were found
		if (!results.isEmpty()) {
			// Loop through all the badge types and create a BadgeAwarded for
			// this user with count set to 0
			for (Badge e : results) {
				tempBadgeAwarded = new BadgeAwarded();
				tempBadgeAwarded.setBadgeId(e.getBadgeId());
				tempBadgeAwarded.setCount(0);
				tempBadgeAwarded.setDeleted(false);
				pm.makePersistent(tempBadgeAwarded);
				user.addBadge(tempBadgeAwarded);
			}
		}

		// Persist the new user
		try {
			pm.makePersistent(user);
		} finally {
			query.closeAll();
			pm.close();
		}

		return user.getKeyString();
	}

	public String addGuest(String username) {
		return addNewUser(username, null, false);
	}

	@SuppressWarnings("unchecked")
	public List<String[]> findUsers() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class);

		List<String[]> resultStringList = new ArrayList<String[]>();
		List<edu.unlv.cs.whoseturn.domain.User> results;

		try {
			results = (List<edu.unlv.cs.whoseturn.domain.User>) query.execute();
			if (!results.isEmpty()) {
				for (edu.unlv.cs.whoseturn.domain.User e : results) {
					resultStringList.add(new String[] { e.getUsername(), e.getEmail(), e.getAdmin().toString() });
				}
			} else {
				return null;
			}
		} finally {
			query.closeAll();
			pm.close();
		}
		return resultStringList;
	}
}

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
import edu.unlv.cs.whoseturn.shared.FieldVerifier;

@SuppressWarnings("serial")
public class UsersServiceImpl extends RemoteServiceServlet implements
		UsersService {

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
	
	public Boolean isLoggedIn()
	{
		UserService userService = UserServiceFactory.getUserService();

		return userService.isUserLoggedIn();
	}
	
	public String getUsername()
	{
		UserService userService = UserServiceFactory.getUserService();	// Get the user auth service
        User user = userService.getCurrentUser();						// Get the logged in user
        
        // Ensure the user is logged in
        if(user == null)
        {
        	return "UserNotLoggedIn";
        }

        PersistenceManager pm = PMF.get().getPersistenceManager();	// Get the persistance manager
        Query query = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class, "email == emailParam");	// Create a query to find the user based off the email
		query.declareParameters("String emailParam");	// Declare the parameter for search
		
		// Execute the query with the email parameter
		@SuppressWarnings("unchecked")
		List<edu.unlv.cs.whoseturn.domain.User> results = (List<edu.unlv.cs.whoseturn.domain.User>) query.execute(user.getEmail());
		
		// Check to make sure only one user was found and return the username
        if (results.size() == 1)
        {
        	return results.get(0).getUsername();
        }
        if (results.size() == 0)
        {
        	return "UserNotFound";
        }

        // Something went wrong if we're down here
        return "ErrorFindingUser";
	}
	
	public String getLoginURL(String providerName, String location)
	{
		UserService userService = UserServiceFactory.getUserService();
		String providerUrl = openIdProviders.get(providerName);
        return userService.createLoginURL(location, null, providerUrl, null);
	}
	
	public String getLogoutURL(String location)
	{
		UserService userService = UserServiceFactory.getUserService();
		
		return userService.createLogoutURL(location, userService.getCurrentUser().getAuthDomain());
	}
	/**
	 * Verifies 
	 */
	@SuppressWarnings("unchecked")
	public String addNewUser(String username, String email, Boolean admin)
	{
		// Get rid of any leading and trailing whitespace in the username and email address
		username.trim();
		email.trim();
		
		String errorMessage; // The error message to be displayed when the username or email is invalid
		
		// A valid email will return "Valid"
		// An invalid email will return "Invalid e-mail address"
		// A duplicate email will return "E-mail address already exists."
		errorMessage = FieldVerifier.isEmailValid(email);
		
		// If the email address isn't "Valid", there was an error so return
		if (errorMessage != "Valid")
		{
			return errorMessage;
		}
		
		// A Valid username will return "Valid"
		// An invalid username will return "Invalid username"
		// A duplicate username will return "Username already exists"
		errorMessage = FieldVerifier.isUsernameValid(username);
		
		// If the username isn't "Valid", there was an error so return
		if (errorMessage != "Valid")
		{
			return errorMessage;
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();	// Get the persistance manager
        edu.unlv.cs.whoseturn.domain.User user = new edu.unlv.cs.whoseturn.domain.User();	// Creates a new user object to add
        
        // Set properties of the user
        user.setAdmin(admin);
        user.setAvatarBlob(null);
        user.setDeleted(false);
        user.setEmail(email);
        user.setUsername(username);
        user.setBadges(new HashSet<String>());
        
        // Creation of the user's default badges
		Query query = pm.newQuery(Badge.class);	// Query the database for all badge types
	    List<Badge> results;	// Prepare a results list
	    BadgeAwarded tempBadgeAwarded;	// Prepare a temporary badgeAwarded to be used for the user

	    // Execute the query and set the results
        results = (List<Badge>) query.execute();
        
        // Make sure badges were found
        if (!results.isEmpty()) 
        {
        	// Loop through all the badge types and create a BadgeAwarded for this user with count set to 0
            for (Badge e : results) 
            {
            	tempBadgeAwarded = new BadgeAwarded();
            	tempBadgeAwarded.setBadgeId(e.getBadgeId());
            	tempBadgeAwarded.setCount(0);
            	tempBadgeAwarded.setDeleted(false);
            	pm.makePersistent(tempBadgeAwarded);
                user.addBadge(tempBadgeAwarded);
            }
        }
        
        // Persist the new user
        try 
        {
            pm.makePersistent(user);
        } 
        finally 
        {
        	query.closeAll();
            pm.close();
        }
        
        return user.getKeyString();
	}
	
	public String addGuest(String username)
	{
        return addNewUser(username, null, false);
	}
	
	@SuppressWarnings("unchecked")
	public List<String[]> findUsers()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class);

	    List<String[]> resultStringList = new ArrayList<String[]>();
	    List<edu.unlv.cs.whoseturn.domain.User> results;
	    
	    try {
	        results = (List<edu.unlv.cs.whoseturn.domain.User>) query.execute();
	        if (!results.isEmpty()) {
	            for (edu.unlv.cs.whoseturn.domain.User e : results) {
	                resultStringList.add(new String[] {e.getUsername(), e.getEmail(), e.getAdmin().toString()});
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

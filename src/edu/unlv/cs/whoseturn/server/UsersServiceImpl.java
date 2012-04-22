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
import edu.unlv.cs.whoseturn.domain.Category;
import edu.unlv.cs.whoseturn.domain.PMF;
import edu.unlv.cs.whoseturn.domain.Strategy;
import edu.unlv.cs.whoseturn.domain.Turn;
import edu.unlv.cs.whoseturn.domain.TurnItem;
import edu.unlv.cs.whoseturn.shared.EntryVerifier;

/**
 * User service that allows the client to CRUD information about users.
 */
public class UsersServiceImpl extends RemoteServiceServlet implements UsersService {

	/**
	 * Allows objects to be serialized.
	 */
	private static final long serialVersionUID = -1794808620527741935L;

	/**
	 * Used to keep track of open id providers.
	 */
	private static final Map<String, String> openIdProviders;

	/**
	 * Static block, used to populate our open id provider list.
	 */
	static {
		openIdProviders = new HashMap<String, String>();
		openIdProviders.put("google", "www.google.com/accounts/o8/id");
		openIdProviders.put("yahoo", "yahoo.com");
		openIdProviders.put("myspace", "myspace.com");
		openIdProviders.put("aol", "aol.com");
		openIdProviders.put("myopenid", "myopenid.com");
	}

	@Override
	public Boolean isLoggedIn() {
		UserService userService = UserServiceFactory.getUserService();

		return userService.isUserLoggedIn();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getUsername() {
		/**
		 * User auth service.
		 */
		UserService userService = UserServiceFactory.getUserService();
		/**
		 * Logged in user.
		 */
		User user = userService.getCurrentUser();

		// Ensure the user is logged in
		if (user == null) {
			return "UserNotLoggedIn";
		}

		/**
		 * Persistence manager, used for CRUD.
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * Query to find the user based off the email
		 */
		Query query = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class, "email == emailParam");

		/**
		 * Parameter for search.
		 */
		query.declareParameters("String emailParam");

		/**
		 * Execute the query with the email parameter
		 */
		List<edu.unlv.cs.whoseturn.domain.User> results = (List<edu.unlv.cs.whoseturn.domain.User>) query.execute(user.getEmail());

		// Check to make sure only one user was found and return the username
		if (results.size() == 1) {
			return results.get(0).getUsername();
		}
		if (results.size() == 0) {
			return "UserNotFound";
		}

		// TODO, should we change this to throw illegal states instead?
		// Something went wrong if we're down here
		return "ErrorFindingUser";
	}

	@Override
	public String getLoginURL(String providerName, String location) {
		UserService userService = UserServiceFactory.getUserService();
		String providerUrl = openIdProviders.get(providerName);
		return userService.createLoginURL(location, null, providerUrl, null);
	}

	@Override
	public String getLogoutURL(String location) {
		UserService userService = UserServiceFactory.getUserService();

		return userService.createLogoutURL(location, userService.getCurrentUser().getAuthDomain());
	}

	/**
	 * Verifies
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String addNewUser(String username, String email, Boolean admin) {
		// Get rid of any leading and trailing whitespace in the username and
		// email address
		username.trim();
		email.trim();

		/**
		 * The error message to be displayed when the username or email is
		 * invalid.
		 */
		String errorMessage;

		// A Valid username will return "Valid"
		// An invalid username will return "Invalid username"
		// A duplicate username will return "Username already exists"
		errorMessage = EntryVerifier.isUsernameValid(username);

		// If the username isn't "Valid", there was an error so return
		if (errorMessage != "Valid") {
			return errorMessage;
		}

		// A valid email will return "Valid"
		// An invalid email will return "Invalid e-mail address"
		// A duplicate email will return "E-mail address already exists."
		errorMessage = EntryVerifier.isEmailValid(email);

		// If the email address isn't "Valid", there was an error so return
		if (errorMessage != "Valid") {
			return errorMessage;
		}

		/**
		 * The persistence manager.
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * New user object to add.
		 */
		edu.unlv.cs.whoseturn.domain.User user = new edu.unlv.cs.whoseturn.domain.User();

		// Set properties of the user
		user.setAdmin(admin);
		user.setAvatarBlob(null);
		user.setDeleted(false);
		user.setEmail(email);
		user.setUsername(username);
		user.setOwnerKeyString("");
		user.setPenaltyCount(0);
		user.setBadges(new HashSet<String>());

		// Creation of the user's default badges
		/**
		 * Query the database for all badge types
		 */
		Query query = pm.newQuery(Badge.class);

		/**
		 * A results list.
		 */
		List<Badge> results;

		/**
		 * A temporary badgeAwarded to be used for the user.
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

		return "Success";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String addGuest(String username) {
		// Get rid of any leading and trailing whitespace in the username and
		// email address
		username.trim();

		/**
		 * The error message to be displayed when the username or email is
		 * invalid.
		 */
		String errorMessage;

		// A Valid username will return "Valid"
		// An invalid username will return "Invalid username"
		// A duplicate username will return "Username already exists"
		errorMessage = EntryVerifier.isUsernameValid(username);

		// If the username isn't "Valid", there was an error so return
		if (errorMessage != "Valid") {
			return errorMessage;
		}

		/**
		 * The persistance manager.
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * A new user object to add.
		 */
		edu.unlv.cs.whoseturn.domain.User user = new edu.unlv.cs.whoseturn.domain.User();

		/**
		 * User auth service.
		 */
		UserService userService = UserServiceFactory.getUserService();

		/**
		 * The logged in user.
		 */
		User loggedUser = userService.getCurrentUser();

		/**
		 * Query the users.
		 */
		Query userQuery = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class, "email == emailParam");
		userQuery.declareParameters("String emailParam");

		/**
		 * Result list.
		 */
		List<edu.unlv.cs.whoseturn.domain.User> userResults;

		userResults = (List<edu.unlv.cs.whoseturn.domain.User>) userQuery.execute(loggedUser.getEmail());

		edu.unlv.cs.whoseturn.domain.User ownerObject = userResults.get(0);

		userQuery.closeAll();

		// Set properties of the user
		user.setAdmin(false);
		user.setAvatarBlob(null);
		user.setDeleted(false);
		user.setEmail("");
		user.setPenaltyCount(0);
		user.setUsername(username);
		user.setBadges(new HashSet<String>());
		user.setOwnerKeyString(ownerObject.getKeyString());

		// Creation of the user's default badges
		/**
		 * Query the database for all badge types.
		 */
		Query badgeQuery = pm.newQuery(Badge.class);

		/**
		 * A results list.
		 */
		List<Badge> badgeResults;

		/**
		 * Prepare a temporary badgeAwarded to be used for the user.
		 */
		BadgeAwarded tempBadgeAwarded;

		/**
		 * Execute the query and set the results.
		 */
		badgeResults = (List<Badge>) badgeQuery.execute();

		// Make sure badges were found
		if (!badgeResults.isEmpty()) {
			// Loop through all the badge types and create a BadgeAwarded for
			// this user with count set to 0
			for (Badge e : badgeResults) {
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
			badgeQuery.closeAll();
			pm.close();
		}

		return "Success";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findUsers() {
		/**
		 * Persistence manager for CRUD.
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * Query for the users.
		 */
		Query query = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class);

		/**
		 * Result string list.
		 */
		List<String[]> resultStringList = new ArrayList<String[]>();

		/**
		 * Result List.
		 */
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

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllGuests() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class, "ownerKeyString != ''");

		List<String> resultStringList = new ArrayList<String>();
		List<edu.unlv.cs.whoseturn.domain.User> results;

		try {
			results = (List<edu.unlv.cs.whoseturn.domain.User>) query.execute();
			if (!results.isEmpty()) {
				for (edu.unlv.cs.whoseturn.domain.User e : results) {
					resultStringList.add(e.getUsername());
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

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findMyGuests() {
		/**
		 * The persistence manager.
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * The user auth service.
		 */
		UserService userService = UserServiceFactory.getUserService();

		/**
		 * The logged in user.
		 */
		User loggedUser = userService.getCurrentUser();

		/**
		 * Create a query to find users whose emails match the param.
		 */
		Query userQuery = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class, "email == emailParam");

		// Declare parameters for the query
		userQuery.declareParameters("String emailParam");

		/**
		 * Create a list to store the results
		 */
		List<edu.unlv.cs.whoseturn.domain.User> userResults;

		// Get the list of users who match the query.
		userResults = (List<edu.unlv.cs.whoseturn.domain.User>) userQuery.execute(loggedUser.getEmail());

		edu.unlv.cs.whoseturn.domain.User ownerObject = userResults.get(0);
		String ownerKeyString = ownerObject.getKeyString();

		Query guestQuery = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class, "ownerKeyString == ownerKeyStringParam");
		guestQuery.declareParameters("String ownerKeyStringParam");

		List<String> resultStringList = new ArrayList<String>();
		List<edu.unlv.cs.whoseturn.domain.User> results;

		try {
			results = (List<edu.unlv.cs.whoseturn.domain.User>) guestQuery.execute(ownerKeyString);
			if (!results.isEmpty()) {
				for (edu.unlv.cs.whoseturn.domain.User e : results) {
					resultStringList.add(e.getUsername());
				}
			} else {
				return null;
			}
		} finally {
			guestQuery.closeAll();
			pm.close();
		}
		return resultStringList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initializeServer() {
		
		// Get the persistence manager
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Wipe the database
		Query wipeQuery = pm.newQuery(Badge.class);
		List<Badge> wipeBadgeResults = (List<Badge>) wipeQuery.execute();
		pm.deletePersistentAll(wipeBadgeResults);
		
		wipeQuery = pm.newQuery(BadgeAwarded.class);
		List<BadgeAwarded> wipeBadgeAwardedResults = (List<BadgeAwarded>) wipeQuery.execute();
		pm.deletePersistentAll(wipeBadgeAwardedResults);
		
		wipeQuery = pm.newQuery(Category.class);
		List<Category> wipeCategoryResults = (List<Category>) wipeQuery.execute();
		pm.deletePersistentAll(wipeCategoryResults);
		
		wipeQuery = pm.newQuery(Strategy.class);
		List<Strategy> wipeStrategyResults = (List<Strategy>) wipeQuery.execute();
		pm.deletePersistentAll(wipeStrategyResults);
		
		wipeQuery = pm.newQuery(Turn.class);
		List<Turn> wipeTurnResults = (List<Turn>) wipeQuery.execute();
		pm.deletePersistentAll(wipeTurnResults);
		
		wipeQuery = pm.newQuery(TurnItem.class);
		List<TurnItem> wipeTurnItemResults = (List<TurnItem>) wipeQuery.execute();
		pm.deletePersistentAll(wipeTurnItemResults);
		
		wipeQuery = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class);
		List<edu.unlv.cs.whoseturn.domain.User> wipeUserResults = (List<edu.unlv.cs.whoseturn.domain.User>) wipeQuery.execute();
		pm.deletePersistentAll(wipeUserResults);

		
		// Create badges
		List<Badge> badgeList = new ArrayList<Badge>();
		Badge badge = new Badge();
		badge.setBadgeCriteria("Test badge 1");
		badge.setBadgeId(1);
		badge.setBadgeName("Test1");
		badge.setDeleted(false);
		badgeList.add(pm.makePersistent(badge));

		badge = new Badge();
		badge.setBadgeCriteria("Test badge 2");
		badge.setBadgeId(2);
		badge.setBadgeName("Test2");
		badge.setDeleted(false);
		badgeList.add(pm.makePersistent(badge));

		badge = new Badge();
		badge.setBadgeCriteria("Test badge 3");
		badge.setBadgeId(3);
		badge.setBadgeName("Test3");
		badge.setDeleted(false);
		badgeList.add(pm.makePersistent(badge));

		// Create strategies
		List<Strategy> strategyList = new ArrayList<Strategy>();
		Strategy strategy = new Strategy();
		strategy.setDeleted(false);
		strategy.setStrategyName("Least Recently Gone");
		strategy.setStrategyId(1);
		strategyList.add(pm.makePersistent(strategy));

		strategy = new Strategy();
		strategy.setDeleted(false);
		strategy.setStrategyName("Lowest Ratio");
		strategy.setStrategyId(2);
		strategyList.add(pm.makePersistent(strategy));

		strategy = new Strategy();
		strategy.setDeleted(false);
		strategy.setStrategyName("Completely Random");
		strategy.setStrategyId(3);
		strategyList.add(pm.makePersistent(strategy));
		
//		strategy = new Strategy();
//		strategy.setDeleted(false);
//		strategy.setStrategyName("Lowest Ratio With Penalty");
//		strategy.setStrategyId(4);
//		pm.makePersistent(strategy);
		
		// Create test categories
		Category category = new Category();
		category.setDeleted(false);
		category.setName("DriveLRG");
		category.setStrategyKeyString(strategyList.get(0).getKeyString());
		category.setTimeBoundaryInHours(24);
		pm.makePersistent(category);
		
		category = new Category();
		category.setDeleted(false);
		category.setName("IceCreamLR");
		category.setStrategyKeyString(strategyList.get(1).getKeyString());
		category.setTimeBoundaryInHours(24);
		pm.makePersistent(category);
		
		category = new Category();
		category.setDeleted(false);
		category.setName("BeerCR");
		category.setStrategyKeyString(strategyList.get(2).getKeyString());
		category.setTimeBoundaryInHours(24);
		pm.makePersistent(category);

		// Create test users
		
		// Creates a new user object to add
		edu.unlv.cs.whoseturn.domain.User user = new edu.unlv.cs.whoseturn.domain.User();

		// Set properties of the user
		user.setAdmin(true);
		user.setAvatarBlob(null);
		user.setDeleted(false);
		user.setEmail("lombar40@unlv.nevada.edu");
		user.setUsername("RyanLombardo");
		user.setOwnerKeyString("");
		user.setPenaltyCount(0);
		user.setBadges(new HashSet<String>());

		/**
		 * Creation of the user's default badges.
		 */
		
		BadgeAwarded tempBadgeAwarded; // Prepare a temporary badgeAwarded to be
										// used for the user
		// Make sure badges were found
		if (!badgeList.isEmpty()) {
			// Loop through all the badge types and create a BadgeAwarded for
			// this user with count set to 0
			for (Badge e : badgeList) {
				tempBadgeAwarded = new BadgeAwarded();
				tempBadgeAwarded.setBadgeId(e.getBadgeId());
				tempBadgeAwarded.setCount(0);
				tempBadgeAwarded.setDeleted(false);
				pm.makePersistent(tempBadgeAwarded);
				user.addBadge(tempBadgeAwarded);
			}
		}

		pm.makePersistent(user);

		user = new edu.unlv.cs.whoseturn.domain.User();

		// Set properties of the user
		user.setAdmin(true);
		user.setAvatarBlob(null);
		user.setDeleted(false);
		user.setEmail("test@example.com");
		user.setUsername("TestUser");
		user.setOwnerKeyString("");
		user.setPenaltyCount(0);
		user.setBadges(new HashSet<String>());

		// Make sure badges were found
		if (!badgeList.isEmpty()) {
			// Loop through all the badge types and create a BadgeAwarded for
			// this user with count set to 0
			for (Badge e : badgeList) {
				tempBadgeAwarded = new BadgeAwarded();
				tempBadgeAwarded.setBadgeId(e.getBadgeId());
				tempBadgeAwarded.setCount(0);
				tempBadgeAwarded.setDeleted(false);
				pm.makePersistent(tempBadgeAwarded);
				user.addBadge(tempBadgeAwarded);
			}
		}

		pm.makePersistent(user);
		
		pm.close();
	}
}

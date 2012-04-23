package edu.unlv.cs.whoseturn.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.unlv.cs.whoseturn.client.TurnService;
import edu.unlv.cs.whoseturn.domain.Category;
import edu.unlv.cs.whoseturn.domain.PMF;
import edu.unlv.cs.whoseturn.domain.Strategy;
import edu.unlv.cs.whoseturn.domain.Turn;
import edu.unlv.cs.whoseturn.domain.TurnItem;

/**
 * Category Service which allows the client to get information from the server
 * regarding categories.
 */
@SuppressWarnings("serial")
public class TurnServiceImpl extends RemoteServiceServlet implements
		TurnService {

	/**
	 * findDriver is called from another function located in a service call on
	 * the client layer
	 * 
	 * @param a
	 *            list of users
	 * @param category
	 * @return a String which will represent the selected driver of Whose Turn
	 */
	public String findDriver(List<String> usernames, String categoryName) {
		edu.unlv.cs.whoseturn.domain.User driver;

		/**
		 * Persistence manager
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * Add the logged in user's username to the list
		 */
		usernames = addLoggedUser(usernames);
		
		/**
		 * Get the user objects based off the usernames provided
		 */
		List<edu.unlv.cs.whoseturn.domain.User> userObjects = getUserObjects(usernames);
		
		/**
		 * Get the category based off the category name provided
		 */
		Category category = getCategoryObject(categoryName);

		/**
		 * Get the strategy from the category
		 */
		String strategyKeyString = category.getStrategyKeyString();
		Key strategyKey = KeyFactory.stringToKey(strategyKeyString);
		Strategy strategy = (Strategy) pm.getObjectById(Strategy.class,
				strategyKey);

		/**
		 * Close the persistence manager
		 */
		pm.close();

		/**
		 * Depending on the strategy from the category, execute the respective
		 * strategy
		 */
		switch (strategy.getStrategyId()) {
		case 1:
			driver = leastRecentlyGone(userObjects, category);
			break;
		case 2:
			driver = lowestRatio(userObjects, category);
			break;
		case 3:
			driver = chooseRandomUser(userObjects);
			break;
		// case 4:
		// driver = lowestRatioWithPenalty(userList, category);
		default:
			driver = new edu.unlv.cs.whoseturn.domain.User();
			driver.setUsername("UnknownDriver");
		}

		pm = PMF.get().getPersistenceManager();
		
		Turn turn = new Turn();
		TurnItem tempTurnItem;
		List<edu.unlv.cs.whoseturn.domain.User> userList = new ArrayList<edu.unlv.cs.whoseturn.domain.User>();
		edu.unlv.cs.whoseturn.domain.User tempUser;
		String tempUserKeyString;
		Key tempUserKey;
		turn.setCategoryKeyString(category.getKeyString());
		turn.setTurnDateTime(new Date());
		turn.setTurnItems(new HashSet<String>());
		if (usernames.size() == 1)
			turn.setDeleted(true);
		else
			turn.setDeleted(false);
		turn = pm.makePersistent(turn);

		for (int i = 0; i < userObjects.size(); i++) {
			tempTurnItem = new TurnItem();
			tempTurnItem.setCategoryKeyString(category.getKeyString());
			tempTurnItem.setDeleted(false);
			tempTurnItem.setOwnerKeyString(userObjects.get(i).getKeyString());
			if (driver.getUsername().equals(userObjects.get(i).getUsername()))
				tempTurnItem.setSelected(true);
			else
				tempTurnItem.setSelected(false);
			tempTurnItem.setTurnKeyString(turn.getKeyString());
			tempTurnItem.setVote(0);
			
			if (usernames.size() == 1)
				tempTurnItem.setDeleted(true);
			else
				tempTurnItem.setDeleted(false);
			
			tempTurnItem = pm.makePersistent(tempTurnItem);
			turn.addTurnItem(tempTurnItem);
			
			// Add turn item to user
			tempUser = new edu.unlv.cs.whoseturn.domain.User();
			tempUserKeyString = userObjects.get(i).getKeyString();
			tempUserKey = KeyFactory.stringToKey(tempUserKeyString);
			tempUser = pm.getObjectById(edu.unlv.cs.whoseturn.domain.User.class, tempUserKey);
			tempUser.addTurnItem(tempTurnItem);
			userList.add(tempUser);
		}
		
		pm.close();
		
		return turn.getKeyString();
	}

	/**
	 * Algorithm which chooses a user based explicitly on the amount of times
	 * they were selected/turnItems; the user with the least ratio will be
	 * chosen to handle all driving responsibilities
	 * 
	 * @param a
	 *            list of users, as well as a category are passed into the
	 *            lowestRatio method to handle and retrieve the amount of times
	 *            the user participated in such a category.
	 * @param category
	 *            will represent a drive, chips & salsa, or ice cream
	 * @return a User, which then will be used to access the a string
	 *         representing the user name
	 */
	public edu.unlv.cs.whoseturn.domain.User lowestRatio(
			List<edu.unlv.cs.whoseturn.domain.User> users, Category category) {
		List<Double> ratioList = new ArrayList<Double>();
		List<String> turnItemsKeyStrings;
		List<TurnItem> turnItems;
		Double tempTurnCount;
		Double tempSelectedCount;
		String turnItemKeyString;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		for (int i = 0; i < users.size(); i++) {
			turnItems = new ArrayList<TurnItem>();
			tempTurnCount = 0.0;
			tempSelectedCount = 0.0;
			turnItemsKeyStrings = new ArrayList<String>(users.get(i).getTurnItems());

			for (int j = 0; j < turnItemsKeyStrings.size(); j++) {
				turnItemKeyString = turnItemsKeyStrings.get(j);
				turnItems.add(pm.getObjectById(TurnItem.class, KeyFactory
						.stringToKey(turnItemKeyString)));
			}

			for (int k = 0; k < turnItems.size(); k++) {
				if (turnItems.get(k).getCategoryKeyString()
						.equals(category.getKeyString())) {
					tempTurnCount++;
					if (turnItems.get(k).getSelected())
						tempSelectedCount++;
				}
			}
			ratioList.add(tempSelectedCount / tempTurnCount);
		}

		Integer index = 0;
		Double tempCurrentRatio = ratioList.get(0);
		Double tempRatio;

		for (int i = 1; i < ratioList.size(); i++) {
			tempRatio = ratioList.get(i);

			if (tempRatio < tempCurrentRatio) {
				tempCurrentRatio = tempRatio;
				index = i;
			}
		}

		return users.get(index);
	}

	/**
	 * Algorithm which chooses a user based explicitly on turnDateTime from
	 * Turn.java, the user which has the oldest date will be selected to drive.
	 * Elementary comparisons between a default currentTurnDate and a tempTurnDate,
	 * will be used to determine which of the users has the earliest of Dates once
	 * the for loop terminates
	 * 
	 * @param a
	 *            list of users, as well as a category are passed into the
	 *            leastRecentlyGone method to handle and retrieve the amount of
	 *            times the user participated in such a category.
	 * @param category
	 *            will represent a drive, chips & salsa, or ice cream
	 * @return a User, which then will be used to access the a string
	 *         representing the user name
	 */
	public edu.unlv.cs.whoseturn.domain.User leastRecentlyGone(
			List<edu.unlv.cs.whoseturn.domain.User> users, Category category) {
		List<String> turnItemsKeyStrings;
		List<TurnItem> turnItems;
		List<Long> millisecondsList = new ArrayList<Long>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Turn tempTurn;
		Date today;
		Long tempMilliSeconds;

		for (int i = 0; i < users.size(); i++) {
			turnItemsKeyStrings = new ArrayList<String>(users.get(i).getTurnItems());
			turnItems = new ArrayList<TurnItem>();
			for (int j = 0; j < turnItemsKeyStrings.size(); j++) {
				turnItems.add(pm.getObjectById(TurnItem.class, KeyFactory
						.stringToKey(turnItemsKeyStrings.get(j))));
			}
			
			today = new Date();
			tempMilliSeconds = 9223372036854775807L;
			for (int k = 0; k < turnItems.size(); k++) {
				if (turnItems.get(k).getCategoryKeyString().equals(category.getKeyString()))
				{
					tempTurn = pm.getObjectById(Turn.class, KeyFactory
							.stringToKey(turnItems.get(k).getTurnKeyString()));
					if((today.getTime() - tempTurn.getTurnDateTime().getTime()) < tempMilliSeconds)
						tempMilliSeconds = today.getTime() - tempTurn.getTurnDateTime().getTime();
				}
			}
			if(!tempMilliSeconds.equals(9223372036854775807L))
				millisecondsList.add(tempMilliSeconds);
			else
				millisecondsList.add(9223372036854775807L);
		}

		pm.close();

		Integer index = 0;
		Long tempCurrentMilliSeconds = millisecondsList.get(0);
		Integer sameCounter = 0;
		for (int i = 1; i < millisecondsList.size(); i++) {
			tempMilliSeconds = millisecondsList.get(i);

			if (tempMilliSeconds > tempCurrentMilliSeconds) {
				tempCurrentMilliSeconds = tempMilliSeconds;
				index = i;
			}
			
			if (tempMilliSeconds.equals(tempCurrentMilliSeconds))
				sameCounter++;
		}
		
		if (sameCounter.equals(millisecondsList.size()))
			return chooseRandomUser(users);

		return users.get(index);
	}

	/**
	 * Algorithm which chooses a user based explicitly on the predefined random
	 * generator The Random object will use the nextInt() method to generate an
	 * integer value, which given a parameter of the the number of users will
	 * choose a number in such 0 to n-1 range
	 * 
	 * @param a
	 *            list of users
	 * @return a User at the arbitrarily generated index, which then will be
	 *         used to access a string representing the user name
	 */
	public edu.unlv.cs.whoseturn.domain.User chooseRandomUser(
			List<edu.unlv.cs.whoseturn.domain.User> users) {
		Random generator = new Random();
		int randomIndex = generator.nextInt(users.size());

		return users.get(randomIndex);
	}

	@SuppressWarnings("unchecked")
	public List<edu.unlv.cs.whoseturn.domain.User> getUserObjects(
			List<String> usernames) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * Get the user objects of the users in the username list
		 */
		Query userQuery = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class,
				"username == usernameParam");
		userQuery.declareParameters("String usernameParam");

		List<edu.unlv.cs.whoseturn.domain.User> userList = new ArrayList<edu.unlv.cs.whoseturn.domain.User>();
		List<edu.unlv.cs.whoseturn.domain.User> tempUserList = new ArrayList<edu.unlv.cs.whoseturn.domain.User>();

		for (int i = 0; i < usernames.size(); i++) {
			tempUserList = (List<edu.unlv.cs.whoseturn.domain.User>) userQuery
					.execute(usernames.get(i));
			userList.add(tempUserList.get(0));
		}
		userList.size();
		pm.close();
		return userList;
	}

	@SuppressWarnings("unchecked")
	public Category getCategoryObject(String categoryName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * Get the category and the strategy of the category
		 */
		Query categoryQuery = pm.newQuery(Category.class,
				"name == categoryNameParam");
		categoryQuery.declareParameters("String categoryNameParam");

		List<Category> categoryList = (List<Category>) categoryQuery
				.execute(categoryName);
		Category category = categoryList.get(0);

		pm.close();
		return category;
	}

	@SuppressWarnings("unchecked")
	public List<String> addLoggedUser(List<String> usernames) {
		/**
		 * User auth service.
		 */
		UserService userService = UserServiceFactory.getUserService();
		/**
		 * Logged in user.
		 */
		User user = userService.getCurrentUser();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query loggedUserQuery = pm.newQuery(
				edu.unlv.cs.whoseturn.domain.User.class, "email == emailParam");
		loggedUserQuery.declareParameters("String emailParam");
		List<edu.unlv.cs.whoseturn.domain.User> loggedUserList = (List<edu.unlv.cs.whoseturn.domain.User>) loggedUserQuery
				.execute(user.getEmail());
		usernames.add(loggedUserList.get(0).getUsername());
		usernames.size();
		pm.close();
		return usernames;
	}
}

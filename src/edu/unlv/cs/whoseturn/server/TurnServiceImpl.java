package edu.unlv.cs.whoseturn.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	@SuppressWarnings("unchecked")
	public String findDriver(List<String> usernames, String categoryName) {
		edu.unlv.cs.whoseturn.domain.User driver;

		/**
		 * User auth service.
		 */
		UserService userService = UserServiceFactory.getUserService();
		/**
		 * Logged in user.
		 */
		User user = userService.getCurrentUser();

		/**
		 * Persistence manager
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * Add the logged in user's username to the list
		 */

		Query loggedUserQuery = pm.newQuery(
				edu.unlv.cs.whoseturn.domain.User.class, "email = emailParam");
		loggedUserQuery.declareParameters("String emailParam");
		List<edu.unlv.cs.whoseturn.domain.User> loggedUserList = (List<edu.unlv.cs.whoseturn.domain.User>) loggedUserQuery
				.execute(user.getEmail());
		usernames.add(loggedUserList.get(0).getUsername());

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

		userQuery.closeAll();

		/**
		 * Get the category and the strategy of the category
		 */
		Query categoryQuery = pm.newQuery(Category.class,
				"categoryName == categoryNameParam");
		userQuery.declareParameters("String categoryNameParam");

		List<Category> categoryList = (List<Category>) categoryQuery
				.execute(categoryName);
		Category category = categoryList.get(0);
		String strategyKeyString = category.getStrategyKeyString();
		Strategy strategy = (Strategy) pm.getObjectById(strategyKeyString);

		/**
		 * Depending on the strategy from the category, execute the respective
		 * strategy
		 */
		switch (strategy.getStrategyId()) {
		case 0:
			driver = leastRecentlyGone(userList, category);
			break;
		case 1:
			driver = lowestRatio(userList, category);
			break;
		case 2:
			driver = chooseRandomUser(userList);
			break;
//		case 3:
//			driver = lowestRatioWithPenalty(userList, category);
		default:
			driver = new edu.unlv.cs.whoseturn.domain.User();
			driver.setUsername("UnknownDriver");
		}

		return driver.getUsername();
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
		Set<String> turnItemsKeyStrings;
		List<TurnItem> turnItems = new ArrayList<TurnItem>();
		Double tempTurnCount;
		Double tempSelectedCount;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		for (int i = 0; i < users.size(); i++) {
			tempTurnCount = 0.0;
			tempSelectedCount = 0.0;
			turnItemsKeyStrings = users.get(i).getTurnItems();

			for (int j = 0; j < turnItemsKeyStrings.size(); j++) {
				turnItems.add(pm.getObjectById(TurnItem.class, KeyFactory
						.stringToKey(turnItemsKeyStrings.iterator().next())));
			}

			for (int k = 0; k < turnItems.size(); k++) {
				if (turnItems.get(k).getCategoryKeyString()
						.equals(category.getKeyString())) {
					tempTurnCount++;
					if (turnItems.get(k).getSelected())
						tempSelectedCount++;
				}
			}
			ratioList.add((Double) (tempSelectedCount / tempTurnCount));
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
	 * The Date objects will be compared using the predefined compareTo method,
	 * which determines differences in milliseconds. Elementary comparisons
	 * between a default currentTurnDate and a tempTurnDate, will be used to
	 * determine which of the users has the earliest of Dates once the for loop
	 * terminates
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
		Set<String> turnItemsKeyStrings;
		List<TurnItem> turnItems = new ArrayList<TurnItem>();
		List<Date> dateList = new ArrayList<Date>();
		Date tempTurnDate;
		Date currentTurnDate;
		Double tempTurnCount;
		Double tempSelectedCount;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Turn tempTurn;

		for (int i = 0; i < users.size(); i++) {
			tempTurnCount = 0.0;
			tempSelectedCount = 0.0;
			turnItemsKeyStrings = users.get(i).getTurnItems();

			for (int j = 0; j < turnItemsKeyStrings.size(); j++) {
				turnItems.add(pm.getObjectById(TurnItem.class, KeyFactory
						.stringToKey(turnItemsKeyStrings.iterator().next())));
			}
			for (int k = 0; k < turnItems.size(); k++) {
				tempTurn = pm.getObjectById(Turn.class, KeyFactory
						.stringToKey(turnItems.get(k).getTurnKeyString()));
				dateList.add(tempTurn.getTurnDateTime());
			}
			pm.close();

		}

		Integer index = 0;
		Integer differenceOfDates;
		Date tempCurrentDate = dateList.get(0);

		for (int i = 1; i < dateList.size(); i++) {
			tempTurnDate = dateList.get(i);
			differenceOfDates = tempCurrentDate.compareTo(tempTurnDate);

			if (differenceOfDates < 0 || differenceOfDates == 0) {
				tempCurrentDate = tempTurnDate;
				index = i;
			}
		}

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
}

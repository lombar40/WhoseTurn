package edu.unlv.cs.whoseturn.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.unlv.cs.whoseturn.client.CategoryService;
import edu.unlv.cs.whoseturn.domain.Category;
import edu.unlv.cs.whoseturn.domain.PMF;
import edu.unlv.cs.whoseturn.domain.Strategy;
import edu.unlv.cs.whoseturn.shared.EntryVerifier;

/**
 * Category Service which allows the client to get information from the server
 * regarding categories.
 */
public class CategoryServiceImpl extends RemoteServiceServlet implements CategoryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3438866264260075434L;

	@SuppressWarnings("unchecked")
	@Override
	public final String addCategory(final String categoryName, final String strategy, final Integer timeBoundary) {

		/**
		 * Persistence Manager
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * Get the strategy keystring based off the supplied name
		 */
		Query q = pm.newQuery(Strategy.class, "strategyName == strategyNameParam");
		q.declareParameters("String strategyNameParam");

		List<Strategy> strategyObjects = (List<Strategy>) q.execute(strategy);
		/**
		 * The error message to display when an invalid category is submitted
		 * (or success if valid)
		 */

		categoryName.trim(); // Get rid of any whitespace before and after the
								// name
		String message;

		// A Valid categoryName will return "Valid"
		// An invalid categoryName will return "Category must be more (less)
		// than 2 (40) characters long
		// A duplicate categoryName will return "Category already exists"
		message = EntryVerifier.isCategoryValid(categoryName);

		if (message != "Valid") {
			return message;
		}

		// A Valid timeBoundary will return "Valid"
		// An invalid timeBoundary will return "A time boundary must be greater
		// (less) than 1 (48) hour(s)
		message = EntryVerifier.isTimeValid(timeBoundary);

		if (message != "Valid") {
			return message;
		}

		Category category = new Category();
		category.setName(categoryName);
		category.setTimeBoundaryInHours(timeBoundary);
		category.setDeleted(false);
		category.setStrategyKeyString(strategyObjects.get(0).getKeyString());
		try {
			pm.makePersistent(category);
			message = "Success";
		} finally {
			pm.close();
		}

		return message;
	}

	@Override
	public final String removeCategory(final String categoryName) {
		String message = "Failure";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Category.class, "name == n");
		q.declareParameters("java.lang.String n");
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) q.execute(categoryName);
		try {
			for (Category category : categories) {
				if (categoryName.equals(category.getName())) {
					category.setDeleted(true);
					message = "Success";
				}
			}
		} finally {
			pm.close();
		}

		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<String> getAllCategories() {
		List<Category> categories;
		List<String> categoryNames = new ArrayList<String>();
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Category.class);
		categories = (List<Category>) q.execute();
		for (Category category : categories) {
			categoryNames.add(category.getName());
		}

		pm.close();

		return categoryNames;

	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<String> getAllStrategies() {
		List<Strategy> strategies;
		List<String> strategyNames = new ArrayList<String>();
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Strategy.class);
		strategies = (List<Strategy>) q.execute();
		for (Strategy strategy : strategies) {
			strategyNames.add(strategy.getStrategyName());
		}

		pm.close();
		return strategyNames;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> getCategoryInfo(String categoryName) {
		/**
		 * Persistence manager for CRUD.
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * Query for the users.
		 */
		Query query = pm.newQuery(Category.class);

		/**
		 * Result string list.
		 */
		List<String[]> resultStringList = new ArrayList<String[]>();

		/**
		 * Result List.
		 */
		List<Category> results;

		try {
			results = (List<Category>) query.execute();
			if (!results.isEmpty()) {
				for (Category e : results) {
					if (e.getName().equals(categoryName)) {
						resultStringList.add(new String[] { e.getName(), e.getStrategyKeyString(), ((Integer) e.getTimeBoundaryInHours()).toString(), e.getDeleted().toString() });
					}
				}
			} else {
				return null;
			}

			Query strategyQuery = pm.newQuery(Strategy.class, "keyString == keyStringParam");
			strategyQuery.declareParameters("String keyStringParam");
			List<Strategy> strategyList = (List<Strategy>) strategyQuery.execute(resultStringList.get(0)[1]);
			resultStringList.get(0)[1] = strategyList.get(0).getStrategyName();
			
		} finally {
			query.closeAll();
			pm.close();
		}
		return resultStringList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String categoryUpdate(String originalCategoryName, String newCategoryName, String newStrategy, String newTimeBoundary, Boolean deleted) {

		// Get rid of any leading and trailing whitespace in the username and
		// email address
		newCategoryName.trim();
		newTimeBoundary.trim();

		/**
		 * The error message to be displayed when the username or email is
		 * invalid.
		 */
		String errorMessage;

		// A Valid username will return "Valid"
		// An invalid username will return "Invalid username"
		// A duplicate username will return "Username already exists"

		if (originalCategoryName.equals(newCategoryName)) {
			errorMessage = "Valid";
		} else {
			errorMessage = EntryVerifier.isCategoryValid(newCategoryName);
		}

		// If the username isn't "Valid", there was an error so return
		if (errorMessage != "Valid") {
			return errorMessage;
		}

		// A valid email will return "Valid"
		// An invalid email will return "Invalid e-mail address"
		// A duplicate email will return "E-mail address already exists."
		errorMessage = EntryVerifier.isTimeValid(Integer.parseInt(newTimeBoundary));

		// If the email address isn't "Valid", there was an error so return
		if (errorMessage != "Valid") {
			return errorMessage;
		}

		/**
		 * The persistence manager.
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		/**
		 * Query to find the user based off the email
		 */
		Query query = pm.newQuery(Category.class, "name == categoryNameParam");

		/**
		 * Parameter for search.
		 */
		query.declareParameters("String categoryNameParam");

		/**
		 * Execute the query with the email parameter
		 */
		List<Category> resultList = (List<Category>) query.execute(originalCategoryName);

		/**
		 * User object to update.
		 */
		Category category = resultList.get(0);

		// Set properties of the user
		category.setName(newCategoryName);

		// Get the strategykey to save.
		Query strategyQuery = pm.newQuery(Strategy.class, "strategyName == strategyNameParam");

		strategyQuery.declareParameters("String strategyNameParam");
		List<Strategy> strategyList = (List<Strategy>) strategyQuery.execute(newStrategy);		
		category.setStrategyKeyString(strategyList.get(0).getKeyString());
		
		category.setTimeBoundaryInHours(Integer.parseInt(newTimeBoundary));
		category.setDeleted(deleted);

		// Persist the new user
		pm.close();

		return "Success";
	}

}

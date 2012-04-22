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
 * Category Service which allows the client to get information from the server regarding categories. 
 */
public class CategoryServiceImpl extends RemoteServiceServlet implements
		CategoryService {

	/**
	 * Variable used to allow the class to be serialized.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void loadInitialCategories() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Category category = new Category();
		category.setName("Drive");
		pm.makePersistent(category);
	}

	@Override
	public String addCategory(String categoryName, String strategy, Integer timeBoundary) {
		categoryName.trim(); // Get rid of any whitespace before and after the name
		
		/**
		 * The error message to display when an invalid category is submitted (or success if valid)
		 */
		String message;
		String strategyKeyString = "TODO"; // Get keystring refering to the supplied strategy

		//
		//
		//
		message = EntryVerifier.isCategoryValid(categoryName);
		
		if (message != "Valid") {
			return message;
		}

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Category category = new Category();
		category.setName(categoryName);
		category.setTimeBoundaryInHours(timeBoundary);
		category.setStrategyKeyString(strategyKeyString);
		try {
			pm.makePersistent(category);
			message = "Success";
		} finally {
			pm.close();
		}
		return message;
	}

	@Override
	public String removeCategory(String categoryName) {
		String message = "Failure";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Category.class, "name == n");
		q.declareParameters("java.lang.String n");
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) q.execute(categoryName);
		try {
			for (Category category : categories) {
				if (categoryName.equals(category.getName())) {
					pm.deletePersistent(category);
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
	public List<String> getAllCategories() {
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
	public List<String> getAllStrategies() {
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

}

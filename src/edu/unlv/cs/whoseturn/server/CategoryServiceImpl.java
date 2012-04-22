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

@SuppressWarnings("serial")
public class CategoryServiceImpl extends RemoteServiceServlet implements
		CategoryService {

	public void loadInitialCategories() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Category category = new Category();
		category.setName("Drive");
		pm.makePersistent(category);
	}

	public String addCategory(String categoryName, String strategy, Integer timeBoundary) {
		
		String message = "TODO"; //EntryVerifier.isCategoryValid(categoryName);
		String strategyKeyString = "TODO"; // Get keystring refering to the supplied strategy

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

	@SuppressWarnings("unchecked")
	public String removeCategory(String categoryName) {
		String message = "Failure";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Category.class, "name == n");
		q.declareParameters("java.lang.String n");
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

	// Return all category names
	@SuppressWarnings("unchecked")
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

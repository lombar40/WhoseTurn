package edu.unlv.cs.whoseturn.mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.unlv.cs.whoseturn.domain.Category;
import edu.unlv.cs.whoseturn.domain.PMF;
import edu.unlv.cs.whoseturn.domain.Strategy;
import edu.unlv.cs.whoseturn.domain.UserSelection;
import edu.unlv.cs.whoseturn.domain.User;

@SuppressWarnings("serial")
public class MobileCategoryScreenController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher view = request.getRequestDispatcher("categoryscreen.jspx");
		try {
			modelCategory(request, response);
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Models selectable users
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void modelCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Category category = getCategory(request, response);

		PersistenceManager manager = PMF.get().getPersistenceManager();
		request.setAttribute("currentCategory", category);
		
		// Model who is logged in
		UserService userService = UserServiceFactory.getUserService();
        com.google.appengine.api.users.User user = userService.getCurrentUser();
        
        request.setAttribute("currentUser", user);
        
        // Model who was selected
        List<User> selectedUsers = getSelectedUsers(request, manager);
        
        request.setAttribute("selectedPersons", selectedUsers);
		
        // List users

		List<UserSelection> persons = new ArrayList<UserSelection>();
		List<User> users = new ArrayList<User>();
		
		javax.jdo.Query query = manager.newQuery(User.class);
		List<Object> results;
		try {
			results = (List<Object>)query.execute();
			
			for (Object result : results) {
				if (result instanceof User)
				{
					User domainUser = (User)result;
					users.add(domainUser);
					
					boolean selected = selectedUsers.contains(domainUser);
					UserSelection userSelection = new UserSelection(domainUser, selected);
					persons.add(userSelection);
				}
			}
		} finally {
			query.closeAll();
			manager.close();
		}
		
		request.setAttribute("persons", persons);
		
		Strategy strategy = getStrategy(request, response, category);
		request.setAttribute("strategy", strategy);
	}

	@SuppressWarnings("unchecked")
	protected Strategy getStrategy(HttpServletRequest request,
			HttpServletResponse response, Category cateogry) throws IOException {
		{	
			PersistenceManager manager = PMF.get().getPersistenceManager();

			javax.jdo.Query query = manager.newQuery(Strategy.class);
			List<Object> results;
			try {
				results = (List<Object>)query.execute();
				
				for (Object result : results) {
					if (result instanceof Strategy)
					{
						Strategy strategy = (Strategy)result;
						
						return strategy;
					}
				}
			} finally {
				query.closeAll();
				manager.close();
			}
			
			return null;
		}
	}

	protected Category getCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		{	
			PersistenceManager manager = PMF.get().getPersistenceManager();
			
			// Find which category, ensure validity
			String categoryKey = request.getParameter("keyString");
			
			if (categoryKey == null) {
				response.getOutputStream().print("Error: invalid category");
				throw new IOException("Invalid category - null categoryKey");
			}
			
			Object categoryKeyStringObject = manager.getObjectById(Category.class, categoryKey);
			if (!(categoryKeyStringObject instanceof Category)) {
				response.getOutputStream().print("Error: invalid category");
				throw new IOException("Invalid category - bad categoryKey");
			}
			return (Category)categoryKeyStringObject;
		}
	}

	protected List<User> getSelectedUsers(
			HttpServletRequest request, PersistenceManager manager) {
		// Model who is currently selected
        String selectedKeys = request.getParameter("selectedPersons");
        if (selectedKeys == null) {
        	selectedKeys =  "";
        }
        
        // Split comma separated values
        String[] selectedKeyStrings = selectedKeys.split(",\\s*");
        
        // Find the currently selected users, add to model
        List<User> selectedUsers = new LinkedList<User>();
        for (String personKey : selectedKeyStrings) {
        	if ((personKey == null) || (personKey.equals(""))) {
        		continue;
        	}
        	Object personObject;
        	try {
            	personObject = manager.getObjectById(User.class, personKey);
        	}
        	catch (JDOObjectNotFoundException e) {
        		// User manually mucked about with the URL, diregard
        		continue;
        	}
        	
        	if (!(personObject instanceof User)) {
        		continue;
        	}
        	User selectedUser = (User)personObject;
        	selectedUsers.add(selectedUser);
        }
		return selectedUsers;
	}
}

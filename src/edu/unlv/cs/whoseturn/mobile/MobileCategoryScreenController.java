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

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.unlv.cs.whoseturn.domain.Category;
import edu.unlv.cs.whoseturn.domain.PMF;
import edu.unlv.cs.whoseturn.domain.UserSelection;

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
	
	protected void modelCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String dbg = "";
		
		
		Category category = getCategory(request, response);

		PersistenceManager manager = PMF.get().getPersistenceManager();
		request.setAttribute("currentCategory", category);
		
		// Model who is logged in
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        request.setAttribute("currentUser", user);
        
        // Model who was selected
        List<edu.unlv.cs.whoseturn.domain.User> selectedUsers = getSelectedUsers(request, manager);
        
        request.setAttribute("selectedPersons", selectedUsers);
		
        // List users

		List<UserSelection> persons = new ArrayList<UserSelection>();
		List<edu.unlv.cs.whoseturn.domain.User> users = new ArrayList<edu.unlv.cs.whoseturn.domain.User>();
		
		javax.jdo.Query query = manager.newQuery(edu.unlv.cs.whoseturn.domain.User.class);
		List<Object> results;
		try {
			results = (List<Object>)query.execute();
			
			for (Object result : results) {
				if (result instanceof edu.unlv.cs.whoseturn.domain.User)
				{
					edu.unlv.cs.whoseturn.domain.User domainUser = (edu.unlv.cs.whoseturn.domain.User)result;
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

	protected List<edu.unlv.cs.whoseturn.domain.User> getSelectedUsers(
			HttpServletRequest request, PersistenceManager manager) {
		// Model who is currently selected
        String selectedKeys = request.getParameter("selectedPersons");
        if (selectedKeys == null) {
        	selectedKeys =  "";
        }
        
        // Split comma separated values
        String[] selectedKeyStrings = selectedKeys.split(",\\s*");
        
        // Find the currently selected users, add to model
        List<edu.unlv.cs.whoseturn.domain.User> selectedUsers = new LinkedList<edu.unlv.cs.whoseturn.domain.User>();
        for (String personKey : selectedKeyStrings) {
        	if ((personKey == null) || (personKey.equals(""))) {
        		continue;
        	}
        	Object personObject;
        	try {
            	personObject = manager.getObjectById(edu.unlv.cs.whoseturn.domain.User.class, personKey);
        	}
        	catch (JDOObjectNotFoundException e) {
        		// User manually mucked about with the URL, diregard
        		continue;
        	}
        	
        	if (!(personObject instanceof edu.unlv.cs.whoseturn.domain.User)) {
        		continue;
        	}
        	edu.unlv.cs.whoseturn.domain.User selectedUser = (edu.unlv.cs.whoseturn.domain.User)personObject;
        	selectedUsers.add(selectedUser);
        }
		return selectedUsers;
	}
}

package edu.unlv.cs.whoseturn.mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
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
			doStuff(request, response);
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void doStuff(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PersistenceManager manager = PMF.get().getPersistenceManager();
		
		// Find which category
		String key = request.getParameter("keyString");
		
		Object keyStringObject = manager.getObjectById(Category.class, key);
		if (!(keyStringObject instanceof Category)) {
			return;
		}
		Category category = (Category)keyStringObject;
		
		request.setAttribute("currentCategory", category);
		
		// Model who is logged in
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        request.setAttribute("currentUser", user);
		
        // List users
        
		List<edu.unlv.cs.whoseturn.domain.User> users = new ArrayList<edu.unlv.cs.whoseturn.domain.User>();
//		
//		Extent<Category> extent = manager.getExtent(Category.class, true);
//		for (Category category : extent) {
//			categories.add(category);
//		}
		
		javax.jdo.Query query = manager.newQuery(edu.unlv.cs.whoseturn.domain.User.class);
		List<Object> results;
		try {
			results = (List<Object>)query.execute();
			
			for (Object result : results) {
				if (result instanceof edu.unlv.cs.whoseturn.domain.User)
				{
					edu.unlv.cs.whoseturn.domain.User person = (edu.unlv.cs.whoseturn.domain.User)result;
					users.add(person);
				}
			}
		} finally {
			query.closeAll();
			manager.close();
		}
		
		request.setAttribute("persons", users);
	}
}

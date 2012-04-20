package edu.unlv.cs.whoseturn.mobile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.unlv.cs.whoseturn.domain.Category;
import edu.unlv.cs.whoseturn.domain.PMF;

@SuppressWarnings("serial")
public class MobileServlet extends HttpServlet {
	public void testData() {
		PersistenceManager manager = PMF.get().getPersistenceManager();
		
		// HACK: Delete all categories, then add test categories.
		Extent<Category> extent = manager.getExtent(Category.class, false);
		for (Category category : extent) {
			manager.deletePersistent(category);
//			return;
		}
		
		Category category;
		
		category = new Category();
		category.setName("Car pool");
		manager.makePersistent(category);
		
		category = new Category();
		category.setName("Buy crêpes");
		manager.makePersistent(category);
		
		category = new Category();
		category.setName("Roshambo");
		manager.makePersistent(category);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// If the user is not logged in, prompt for login
		UserService userService = UserServiceFactory.getUserService();
		if (!userService.isUserLoggedIn()) {
			String url = userService.createLoginURL("/mobile");
			RequestDispatcher view = request.getRequestDispatcher(url);
			try {
				view.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return;
		}
		
		// Proceed
		RequestDispatcher view = request.getRequestDispatcher("loggedinpage.jspx");
		try {
			testData();
			
			doStuff(request, response);
			
			PersistenceManager manager = PMF.get().getPersistenceManager();
			List<Category> categories = new LinkedList<Category>();
//			manager.retrieveAll(categories, true);
			
			Extent<Category> extent = manager.getExtent(Category.class, false);
			for (Category category : extent) {
				categories.add(category);
			}
			
			request.setAttribute("categories", categories);
			
			request.setAttribute("testatt", "booze");
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void doStuff(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        request.setAttribute("currentUser", user);
	}
}

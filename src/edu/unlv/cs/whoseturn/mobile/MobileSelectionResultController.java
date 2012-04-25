package edu.unlv.cs.whoseturn.mobile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unlv.cs.whoseturn.domain.Category;
import edu.unlv.cs.whoseturn.domain.PMF;
import edu.unlv.cs.whoseturn.domain.User;
import edu.unlv.cs.whoseturn.server.TurnServiceImpl;

public class MobileSelectionResultController extends MobileCategoryScreenController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6242457047514025398L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher view = request.getRequestDispatcher("selectionresult.jspx");
		try {
			doStuff(request, response);
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Models the active and inactive users (users elected and users not elected)
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doStuff(HttpServletRequest request, HttpServletResponse response) throws IOException {
		modelCategory(request, response);
		PersistenceManager manager = PMF.get().getPersistenceManager();
        List<User> selectedUsers = getSelectedUsers(request, manager);
        
        Category category = getCategory(request, response);
        
        TurnServiceImpl turnService = new TurnServiceImpl();
        List<User> activeUsers = turnService.findDriver(selectedUsers, category);
        
        request.setAttribute("activeUsers", activeUsers);
        
        List<User> inactiveUsers = new LinkedList<User>();
        for (User user : selectedUsers) {
        	/*
        	if (activeUsers.contains(user)) {
        		continue;
        	}
        	*/
        	
        	// TODO: Make User implement comparable
        	boolean active = false;
        	for (User activeUser : activeUsers) {
        		if (activeUser.getUsername().equals(user.getUsername())) {
        			active = true;
        		}
        	}
        	
        	if (active) {
        		continue;
        	}
        	inactiveUsers.add(user);
        }
        
        request.setAttribute("inactiveUsers", inactiveUsers);
	}

}

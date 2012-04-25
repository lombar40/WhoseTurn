package edu.unlv.cs.whoseturn.mobile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MobileLogoutController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3930249024436417486L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLogoutURL("/mobile/index");
		RequestDispatcher view = request.getRequestDispatcher(url);
		try {
			doStuff(request, response);
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doStuff(HttpServletRequest request, HttpServletResponse response) {
	}
}

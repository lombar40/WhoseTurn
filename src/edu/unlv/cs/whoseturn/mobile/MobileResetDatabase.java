package edu.unlv.cs.whoseturn.mobile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unlv.cs.whoseturn.server.UsersServiceImpl;

public class MobileResetDatabase extends MobileCategoryScreenController {

	private static final long serialVersionUID = 311450473771254537L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher view = request.getRequestDispatcher("/mobile/index");
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
		UsersServiceImpl service = new UsersServiceImpl();
		service.initializeServer();
	}
}

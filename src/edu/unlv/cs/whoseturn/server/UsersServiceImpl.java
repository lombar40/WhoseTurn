package edu.unlv.cs.whoseturn.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.unlv.cs.whoseturn.client.UsersService;

@SuppressWarnings("serial")
public class UsersServiceImpl extends RemoteServiceServlet implements
		UsersService {

	public String usersServer()
	{
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null) {
            return user.getNickname();
        } else {
            return userService.createLoginURL("");
        }
	}
	
	public Boolean isLoggedIn()
	{
		UserService userService = UserServiceFactory.getUserService();

		return userService.isUserLoggedIn();
	}
	
	public String getUsername()
	{
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if(user != null)
        	return user.getEmail();
        
        return null;
	}
	
	public String getLoginURL(String location)
	{
		UserService userService = UserServiceFactory.getUserService();
        
        return userService.createLoginURL(location);
	}
	
	public String getLogoutURL(String location)
	{
		UserService userService = UserServiceFactory.getUserService();
		
		return userService.createLogoutURL(location);

	}
}

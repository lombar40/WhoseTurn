package edu.unlv.cs.whoseturn.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import edu.unlv.cs.whoseturn.client.views.desktop.Login;
import edu.unlv.cs.whoseturn.client.views.desktop.TurnAdd;
import edu.unlv.cs.whoseturn.client.views.desktop.UserNotFound;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Whoseturn implements EntryPoint {
    /**
     * The user service.
     */
    private final UsersServiceAsync usersService = GWT
            .create(UsersService.class);
    
    /**
     * This is the entry point method.
     */
    public final void onModuleLoad() {
        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        /**
         * The rootPanel of the display, the root panel of our entry point.
         */
        final RootPanel rootPanel = RootPanel.get("overall");
        rootPanel.setSize("1000px", "600px");

        /**
         * Checks to see if the user is logged in through OpenID
         */
        usersService.isLoggedIn(new AsyncCallback<Boolean>() {
            public void onFailure(final Throwable caught) {
            	System.err.println(caught.getStackTrace());
            }

            public void onSuccess(final Boolean result) {
            	/**
            	 * Since the user is logged in, try to retrieve the username
            	 */
                if (result) {
                    usersService.getUsername(new AsyncCallback<String>() {
                        public void onFailure(final Throwable caught) {
                        	System.err.println(caught.getStackTrace());
                        }

                        public void onSuccess(final String result) {
                            /**
                             * The username was not found so present the UserNotFound screen.
                             */
                        	if (result.equals("UserNotFound")) {
                                RootPanel.get("overall").clear();
                                RootPanel.get("overall").add(
                                        (new UserNotFound()).asWidget());
                            } 
                        	/**
                        	 * The username was found so present the TurnAdd screen.
                        	 */
                        	else {
                                RootPanel.get("overall").clear();
                                RootPanel.get("overall").add(
                                        (new TurnAdd()).asWidget());
                            }
                        }

                    });
                  
                } 
                /**
            	 * User isn't logged in, so present the login screen.
            	 */
                else {   
                    RootPanel.get("overall").clear();
                    RootPanel.get("overall").add((new Login()).asWidget());
                }
            }
        });
    }
}

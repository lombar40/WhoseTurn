package edu.unlv.cs.whoseturn.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import edu.unlv.cs.whoseturn.client.views.desktop.Login;
import edu.unlv.cs.whoseturn.client.views.desktop.Main;
import edu.unlv.cs.whoseturn.client.views.desktop.UserEdit;
import edu.unlv.cs.whoseturn.client.views.desktop.UserNotFound;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Whoseturn implements EntryPoint {
	private final UsersServiceAsync usersService = GWT
			.create(UsersService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		final RootPanel rootPanel = RootPanel.get("overall");
		rootPanel.setSize("1000px", "750px");
		
		final AbsolutePanel mainPanel = new AbsolutePanel();
		rootPanel.add(mainPanel);
		mainPanel.setSize("916px", "575px");
		
		final Label lblTestLabel = new Label("Loading...");
		rootPanel.add(lblTestLabel);
		
		final AbsolutePanel navPanel = new AbsolutePanel();
		rootPanel.add(navPanel);
		navPanel.setSize("916px", "35px");
		navPanel.setVisible(false);
		
		Button btnMain = new Button("Main");
		btnMain.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lblTestLabel.setText("sendingToMain");
				mainPanel.clear();
				mainPanel.add((new Main()).asWidget());
			}
		});
		btnMain.setText("Main");
		navPanel.add(btnMain);
		
		Button btnProfileSettings = new Button("Profile Settings");
		btnProfileSettings.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lblTestLabel.setText("sendingToProfileManagement");
				mainPanel.clear();
				mainPanel.add((new UserEdit()).asWidget());
			}
		});
		navPanel.add(btnProfileSettings, 50, 0);
		
		

		usersService.isLoggedIn(new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				lblTestLabel.setText("isLoggedIn");
			}

			public void onSuccess(Boolean result) {
				if (result == true) {
					usersService.getUsername(new AsyncCallback<String>() {
						public void onFailure(Throwable caught) {
							lblTestLabel.setText("getUsername");
						}

						public void onSuccess(String result) {
							if (result.equals("UserNotFound")) {
								mainPanel.clear();
								mainPanel.add((new UserNotFound()).asWidget());
								lblTestLabel.setText("UserNotFound");
								//loginPanel.setVisible(false);
								//userNotFoundPanel.setVisible(true);
							} else
							{
								lblTestLabel.setText("sendingToMain1");
								navPanel.setVisible(true);
								mainPanel.clear();
								mainPanel.add((new Main()).asWidget());
								//RootPanel.get("overall").remove(mainPanel);
								//RootPanel.get("overall").add((new Main()).asWidget(), 10, 10);
							}
						}

					});
				} else {
					lblTestLabel.setText("notLoggedIn");
					mainPanel.clear();
					mainPanel.add((new Login()).asWidget());
				}
			}
		});
	}
}

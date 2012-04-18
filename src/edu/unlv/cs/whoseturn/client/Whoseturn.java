package edu.unlv.cs.whoseturn.client;

import edu.unlv.cs.whoseturn.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.CheckBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Whoseturn implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	private final UsersServiceAsync usersService = GWT.create(UsersService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("nameFieldContainer");
		rootPanel.add(nameField, 557, 175);
		RootPanel.get("sendButtonContainer").add(sendButton, 567, 215);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		
		final Label lblNewLabel = new Label("Loading logged in user....(Current disabled)");
		rootPanel.add(lblNewLabel, 0, 0);
		
		Button btnLogout = new Button("Logout");
		btnLogout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				usersService.getLogoutURL(Window.Location.getPath(),
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								lblNewLabel.setText("FAILURE");
							}

							public void onSuccess(String result) {
								Window.open(result, "_self", "");
							}
						});
			}
		});
		rootPanel.add(btnLogout, 10, 24);
		
		final HTML htmlNewHtml = new HTML("Last added user will be listed here upon query with the keystring.", true);
		rootPanel.add(htmlNewHtml, 34, 242);
		htmlNewHtml.setSize("354px", "221px");

		
		final TextBox txtbxUsername = new TextBox();
		rootPanel.add(txtbxUsername, 96, 70);
		
		final TextBox txtbxEmail = new TextBox();
		rootPanel.add(txtbxEmail, 96, 110);
		
		final CheckBox chckbxAdmin = new CheckBox("");
		rootPanel.add(chckbxAdmin, 96, 150);
		
		final Label lblUsername = new Label("username");
		rootPanel.add(lblUsername, 9, 70);
		
		final Label lblEmail = new Label("email");
		rootPanel.add(lblEmail, 10, 110);
		
		Label lblAdmin = new Label("admin?");
		rootPanel.add(lblAdmin, 9, 150);
		
		Button btnAdduser = new Button("adduser");
		
		btnAdduser.setText("Add User");
		rootPanel.add(btnAdduser, 96, 179);
		
		final Label lblCreated = new Label("Created user with keystring:");
		rootPanel.add(lblCreated, 34, 218);
		lblCreated.setVisible(false);
		
		final Label lblKeystring = new Label("");
		rootPanel.add(lblKeystring, 202, 218);
		lblKeystring.setSize("53px", "18px");
		nameField.selectAll();
		
		btnAdduser.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				usersService.addTestUser(txtbxUsername.getText(), txtbxEmail.getText(), chckbxAdmin.getValue(),
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								lblCreated.setText("FAILURE");
							}

							public void onSuccess(String result) {
								lblCreated.setVisible(true);
								lblKeystring.setText(result);
							}
						});
			}
		});
		
		Button btnNewButton = new Button("Query User");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				usersService.findUserByKey(lblKeystring.getText(),
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								lblNewLabel.setText("FAILURE");
							}

							public void onSuccess(String result) {
								htmlNewHtml.setHTML(result);
							}
						});
			}
		});
		btnNewButton.setText("Query User");
		rootPanel.add(btnNewButton, 34, 467);
		/*usersService.isLoggedIn(
				new AsyncCallback<Boolean>() {
					public void onFailure(Throwable caught) {
						lblNewLabel.setText("FAILURE");
					}

					public void onSuccess(Boolean result) {
						if(result == true)
						{
							usersService.getUsername(
									new AsyncCallback<String>() {
										public void onFailure(Throwable caught) {
											lblNewLabel.setText("FAILURE");
										}
	
										public void onSuccess(String result) {
											lblNewLabel.setText("Welcome "+result);
										}
									});
						}
						else
						{
							usersService.getLoginURL(Window.Location.getPath(),
									new AsyncCallback<String>() {
										public void onFailure(Throwable caught) {
											lblNewLabel.setText("FAILURE");
										}

										public void onSuccess(String result) {
											Window.open(result, "_self", "");
										}
									});
						}
						
					}
				});*/
		

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}
}

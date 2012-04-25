package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.View;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;

/**
 * View used to allow users to login. We extend the view interface because we do
 * not want the navigation bar to appear until a user is logged in.
 */
public class Login implements View {

	/**
	 * The user service.
	 */
	private final UsersServiceAsync usersService = GWT.create(UsersService.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public final Widget asWidget() {
		String url = GWT.getHostPageBaseURL();
		if (!GWT.isProdMode()) {
			url += "?gwt.codesvr=127.0.0.1:9997";
		}
		final String finalURL = url;
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element

		final AbsolutePanel loginPanel = new AbsolutePanel();
		loginPanel.setSize("1000px", "300px");

		AbsolutePanel loginAbsolutePanel = new AbsolutePanel();
		loginPanel.add(loginAbsolutePanel, 304, 10);
		loginAbsolutePanel.setSize("405px", "218px");

		final Label lblStatus = new Label("");
		loginPanel.add(lblStatus, 565, 251);
		lblStatus.setSize("60px", "18px");

		Button btnInitalizeDatabase = new Button("Initalize Database");
		btnInitalizeDatabase.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				usersService.initializeServer(new AsyncCallback<Void>() {
					public void onFailure(final Throwable caught) {
						System.err.println(caught.getStackTrace());
					}

					public void onSuccess(final Void result) {
						lblStatus.setText("Initialized");
					}
				});
			}
		});
		loginPanel.add(btnInitalizeDatabase, 440, 245);

		final Label lblTitle = new Label("Please login by choosing one of the following authentication options.");
		loginAbsolutePanel.add(lblTitle);

		Image googleImage = new Image("images/googleW.png");
		loginAbsolutePanel.add(googleImage, 53, 24);
		googleImage.setStyleName("ImageLink");

		Image yahooImage = new Image("images/yahooW.png");
		loginAbsolutePanel.add(yahooImage, 113, 24);
		yahooImage.setStyleName("ImageLink");

		Image aolImage = new Image("images/aolW.png");
		loginAbsolutePanel.add(aolImage, 173, 24);
		aolImage.setStyleName("ImageLink");

		Image myspaceImage = new Image("images/myspaceW.png");
		loginAbsolutePanel.add(myspaceImage, 233, 24);
		myspaceImage.setStyleName("ImageLink");

		Image openIDImage = new Image("images/myopenidW.png");
		loginAbsolutePanel.add(openIDImage, 293, 24);
		openIDImage.setStyleName("ImageLink");

		final TextBox txtbxUsername = new TextBox();
		txtbxUsername.setMaxLength(100);
		loginAbsolutePanel.add(txtbxUsername, 160, 47);

		final TextBox txtbxEmail = new TextBox();
		txtbxEmail.setMaxLength(100);
		loginAbsolutePanel.add(txtbxEmail, 160, 87);

		final Label lblEmail = new Label("Email:");
		loginAbsolutePanel.add(lblEmail, 111, 95);
		lblEmail.setSize("41px", "18px");

		final Label lblUsername = new Label("Username:");
		loginAbsolutePanel.add(lblUsername, 82, 55);
		lblUsername.setSize("70px", "18px");

		Label lblAdmin = new Label("Admin:");
		loginAbsolutePanel.add(lblAdmin, 111, 123);

		final CheckBox chckbxAdmin = new CheckBox("");
		loginAbsolutePanel.add(chckbxAdmin, 160, 123);

		Button btnAdduser = new Button("adduser");
		loginAbsolutePanel.add(btnAdduser, 160, 149);
		btnAdduser.setText("Add User");


		btnAdduser.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				// Checks if the e-mail has a chance to be valid and if it
				// already exists.
				/*
				 * if (!FieldVerifier.isEmailValid(txtbxEmail.getText(),
				 * lblKeystring)) { return; } // Checks to see if the user name
				 * already exists if
				 * (!FieldVerifier.isUsernameValid(txtbxUsername.getText(),
				 * lblKeystring)) { return; }
				 */
				usersService.addNewUser(txtbxUsername.getText(), txtbxEmail.getText(), chckbxAdmin.getValue(), new AsyncCallback<String>() {
					public void onFailure(final Throwable caught) {
						System.err.println(caught.getStackTrace());
					}

					public void onSuccess(final String result) {
						// Result is an error message or the key if
						// successful
						lblStatus.setText(result);
					}
				});
			}
		});

		openIDImage.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				usersService.getLoginURL("myopenid", finalURL, new AsyncCallback<String>() {
					public void onFailure(final Throwable caught) {
						lblTitle.setText("FAILURE");
						System.err.println(caught.getStackTrace());
					}

					public void onSuccess(final String result) {
						Window.open(result, "_self", "");
					}
				});
			}
		});

		myspaceImage.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				usersService.getLoginURL("myspace", finalURL, new AsyncCallback<String>() {
					public void onFailure(final Throwable caught) {
						lblTitle.setText("FAILURE");
						System.err.println(caught.getStackTrace());
					}

					public void onSuccess(final String result) {
						Window.open(result, "_self", "");
					}
				});
			}
		});

		aolImage.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				usersService.getLoginURL("aol", finalURL, new AsyncCallback<String>() {
					public void onFailure(final Throwable caught) {
						lblTitle.setText("FAILURE");
						System.err.println(caught.getStackTrace());
					}

					public void onSuccess(final String result) {
						Window.open(result, "_self", "");
					}
				});
			}
		});

		yahooImage.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				usersService.getLoginURL("yahoo", finalURL, new AsyncCallback<String>() {
					public void onFailure(final Throwable caught) {
						lblTitle.setText("FAILURE");
						System.err.println(caught.getStackTrace());
					}

					public void onSuccess(final String result) {
						Window.open(result, "_self", "");
					}
				});
			}
		});
		googleImage.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(final MouseOverEvent event) {

			}
		});

		googleImage.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				usersService.getLoginURL("google", finalURL, new AsyncCallback<String>() {
					public void onFailure(final Throwable caught) {
						lblTitle.setText("FAILURE");
						System.err.println(caught.getStackTrace());
					}

					public void onSuccess(final String result) {
						Window.open(result, "_self", "");
					}
				});
			}
		});

		return loginPanel;
	}
}

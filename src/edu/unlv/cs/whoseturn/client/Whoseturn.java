package edu.unlv.cs.whoseturn.client;

import java.util.List;

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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Whoseturn implements EntryPoint {
	private final UsersServiceAsync usersService = GWT.create(UsersService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("overall");
		
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
		

		final TextArea textArea = new TextArea();
		textArea.setVisibleLines(10);
		rootPanel.add(textArea, 34, 242);
		textArea.setSize("336px", "205px");
		Button btnNewButton = new Button("Query Users");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				usersService.findUsers(
						new AsyncCallback<List<String[]>>() {
							public void onFailure(Throwable caught) {
								lblNewLabel.setText("FAILURE");
							}

							public void onSuccess(List<String[]> result) {
								textArea.setText("");
								for (String[] row : result)
								{
									textArea.setText(textArea.getText()+"Username: "+row[0]+"\nEmail: "+row[1]+"\nAdmin: "+row[2]+"\n\n");
								}
								
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
	}
}

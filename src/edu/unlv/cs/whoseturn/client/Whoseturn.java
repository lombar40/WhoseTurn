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
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DecoratedTabBar;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

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
		
		final Label lblKeystring = new Label("");
		rootPanel.add(lblKeystring, 202, 218);
		lblKeystring.setSize("53px", "18px");
		
		DecoratedTabPanel decoratedTabPanel = new DecoratedTabPanel();
		rootPanel.add(decoratedTabPanel, 0, 0);
		decoratedTabPanel.setSize("657px", "578px");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		decoratedTabPanel.add(absolutePanel, "Login", false);
		absolutePanel.setSize("650px", "544px");
		
		final Label lblNewLabel = new Label("Loading logged in user....(Current disabled)");
		absolutePanel.add(lblNewLabel, 0, 0);
		
		Button btnLogout = new Button("Logout");
		absolutePanel.add(btnLogout, 0, 22);
		
		final Label lblUsername = new Label("username");
		absolutePanel.add(lblUsername, 0, 66);
		
				
				final TextBox txtbxUsername = new TextBox();
				absolutePanel.add(txtbxUsername, 73, 61);
				
				final Label lblEmail = new Label("email");
				absolutePanel.add(lblEmail, 22, 117);
				
				final TextBox txtbxEmail = new TextBox();
				absolutePanel.add(txtbxEmail, 73, 101);
				
				Label lblAdmin = new Label("admin?");
				absolutePanel.add(lblAdmin, 13, 153);
				
				final CheckBox chckbxAdmin = new CheckBox("");
				absolutePanel.add(chckbxAdmin, 73, 150);
				
				Button btnAdduser = new Button("adduser");
				absolutePanel.add(btnAdduser, 73, 179);
				
				btnAdduser.setText("Add User");
				
				final Label lblCreated = new Label("Created user with keystring:");
				absolutePanel.add(lblCreated, 13, 225);
				

				final TextArea textArea = new TextArea();
				absolutePanel.add(textArea, 13, 247);
				textArea.setVisibleLines(10);
				textArea.setSize("336px", "205px");
				Button btnNewButton = new Button("Query Users");
				absolutePanel.add(btnNewButton, 13, 468);
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
				
				AbsolutePanel absolutePanel_1 = new AbsolutePanel();
				decoratedTabPanel.add(absolutePanel_1, "Main", false);
				absolutePanel_1.setSize("650px", "544px");
				
				AbsolutePanel absolutePanel_2 = new AbsolutePanel();
				decoratedTabPanel.add(absolutePanel_2, "AdminSettings", false);
				absolutePanel_2.setSize("649px", "540px");
				
				AbsolutePanel absolutePanel_3 = new AbsolutePanel();
				decoratedTabPanel.add(absolutePanel_3, "UserSettings", false);
				absolutePanel_3.setSize("652px", "543px");
				
				Label lblListUsers = new Label("List Users");
				absolutePanel_3.add(lblListUsers, 46, 25);
				
				Button btnAddUser = new Button("Add User");
				absolutePanel_3.add(btnAddUser, 26, 138);
				
				Button btnAddGuest = new Button("Add Guest");
				absolutePanel_3.add(btnAddGuest, 116, 138);
				
				AbsolutePanel absolutePanel_4 = new AbsolutePanel();
				decoratedTabPanel.add(absolutePanel_4, "User Profile", false);
				absolutePanel_4.setSize("651px", "543px");
				
				AbsolutePanel absolutePanel_5 = new AbsolutePanel();
				decoratedTabPanel.add(absolutePanel_5, "Turn History", false);
				absolutePanel_5.setSize("650px", "544px");
				lblCreated.setVisible(false);
				
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

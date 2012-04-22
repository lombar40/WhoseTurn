package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Displays a User's profile.
 */
public class AddGuest extends AbstractNavigationView implements NavigationView {

	private final UsersServiceAsync usersService = GWT
			.create(UsersService.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		AbsolutePanel addGuestPanel = new AbsolutePanel();
		addGuestPanel.setSize("1000px", "500px");

		Label lblTitle = new Label();
		lblTitle.setStyleName("SectionHeader");
		lblTitle.setText("Add Guest");
		addGuestPanel.add(lblTitle);

		Label lblUsername = new Label("Username:");
		addGuestPanel.add(lblUsername, 10, 56);

		final TextBox txtbxUsername = new TextBox();
		addGuestPanel.add(txtbxUsername, 75, 47);

		Button btnAddGuest = new Button("Add Guest");
		addGuestPanel.add(btnAddGuest, 75, 87);

		final Label lblErrorLabel = new Label("");
		lblErrorLabel.setStyleName("serverResponseLabelError");
		addGuestPanel.add(lblErrorLabel, 159, 91);

		final Label lblSuccessfullyAddedGuest = new Label(
				"Successfully added guest");
		addGuestPanel.add(lblSuccessfullyAddedGuest, 159, 91);
		lblSuccessfullyAddedGuest.setVisible(false);

		btnAddGuest.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lblErrorLabel.setText("");
				lblSuccessfullyAddedGuest.setVisible(false);
				usersService.addGuest(txtbxUsername.getText(),
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// TODO
							}

							public void onSuccess(String result) {
								if (!result.equals("Success"))
									lblErrorLabel.setText(result);
								else
									lblSuccessfullyAddedGuest.setVisible(true);
							}
						});
			}
		});

		return addGuestPanel;
	}
}

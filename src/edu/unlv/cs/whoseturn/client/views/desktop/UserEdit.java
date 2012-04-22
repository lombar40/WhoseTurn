package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

import com.google.gwt.user.client.ui.TextBox;

/**
 * POC that changes to view2 when the button is clicked.
 */
public class UserEdit extends AbstractNavigationView implements NavigationView {

	private final UsersServiceAsync usersService = GWT
			.create(UsersService.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		String url = GWT.getHostPageBaseURL();
		if (!GWT.isProdMode())
			url += "?gwt.codesvr=127.0.0.1:9997";
		final String finalURL = url;

		final AbsolutePanel profileManagementPanel = new AbsolutePanel();
		profileManagementPanel.setSize("1000px", "500px");

		Button btnLogout = new Button("Logout");
		profileManagementPanel.add(btnLogout, 10, 75);

		Label lblUsername = new Label("Username:");
		profileManagementPanel.add(lblUsername, 10, 10);

		final TextBox txtbxUsername = new TextBox();
		profileManagementPanel.add(txtbxUsername, 79, 10);

		Label lblNewLabel = new Label("New label");
		profileManagementPanel.add(lblNewLabel, 384, 272);

		btnLogout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				usersService.getLogoutURL(finalURL,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// lblNewLabel.setText("FAILURE");
							}

							public void onSuccess(String result) {
								Window.open(result, "_self", "");
							}
						});
			}
		});

		usersService.getUsername(new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// lblNewLabel.setText("FAILURE");
			}

			public void onSuccess(String result) {
				txtbxUsername.setText(result);
			}
		});

		return profileManagementPanel;
	}
}

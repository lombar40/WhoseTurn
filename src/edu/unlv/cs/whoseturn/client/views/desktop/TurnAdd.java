package edu.unlv.cs.whoseturn.client.views.desktop;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.BadgeService;
import edu.unlv.cs.whoseturn.client.BadgeServiceAsync;
import edu.unlv.cs.whoseturn.client.CategoryService;
import edu.unlv.cs.whoseturn.client.CategoryServiceAsync;
import edu.unlv.cs.whoseturn.client.TurnService;
import edu.unlv.cs.whoseturn.client.TurnServiceAsync;
import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Allows a user to add a Turn instance to the program.
 */
public class TurnAdd extends AbstractNavigationView implements NavigationView {

	private final UsersServiceAsync usersService = GWT.create(UsersService.class);

	private final CategoryServiceAsync categoryService = GWT.create(CategoryService.class);

	private final TurnServiceAsync turnService = GWT.create(TurnService.class);

	private final BadgeServiceAsync badgeService = GWT.create(BadgeService.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {

		// TODO
		/**
		 * We need: list of users as toggle buttons for who is part of the turn
		 * a spinner widget to select the number of people to be picked
		 * 
		 * how are we handling confirmation messages? perhaps another panel at
		 * the bottom? status panel?
		 */

		// The body of the view.
		AbsolutePanel turnAddPanel = new AbsolutePanel();
		turnAddPanel.setSize("1000px", "500px");

		Label lblTitle = new Label();
		lblTitle.setStyleName("SectionHeader");
		lblTitle.setText("Turn Add");
		turnAddPanel.add(lblTitle);

		final ListBox cmbbxStrategy = new ListBox();
		turnAddPanel.add(cmbbxStrategy, 10, 73);
		cmbbxStrategy.setSize("122px", "22px");

		final Label lblNoCategoriesFound = new Label("No categories found");
		turnAddPanel.add(lblNoCategoriesFound, 10, 73);
		lblNoCategoriesFound.setVisible(false);

		final Button btnSubmit = new Button("Select Unlucky Soul");
		turnAddPanel.add(btnSubmit, 10, 446);

		Label lblChooseCategory = new Label("Choose Category");
		turnAddPanel.add(lblChooseCategory, 10, 51);

		final Label lblDriver = new Label("");
		lblDriver.setStyleName("Header");
		turnAddPanel.add(lblDriver, 159, 446);

		ScrollPanel userScrollPanel = new ScrollPanel();
		userScrollPanel.setVerticalScrollPosition(1);
		turnAddPanel.add(userScrollPanel, 0, 111);
		userScrollPanel.setSize("1000px", "300px");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		userScrollPanel.add(horizontalPanel);
		horizontalPanel.setSize("967px", "300px");

		final VerticalPanel verticalPanel = new VerticalPanel();
		horizontalPanel.add(verticalPanel);
		verticalPanel.setSize("250px", "");

		final VerticalPanel verticalPanel_1 = new VerticalPanel();
		horizontalPanel.add(verticalPanel_1);
		verticalPanel_1.setSize("250px", "");

		final VerticalPanel verticalPanel_2 = new VerticalPanel();
		horizontalPanel.add(verticalPanel_2);
		verticalPanel_2.setSize("250px", "0");

		/**
		 * A list that we will be able to iterate through to see which buttons
		 * are toggle and which are not.
		 */
		final List<ToggleButton> toggleButtonList = new ArrayList<ToggleButton>();

		final List<String> usernameList = new ArrayList<String>();

		usersService.getUsername(new AsyncCallback<String>() {
			public void onFailure(final Throwable caught) {
				System.err.println(caught.getStackTrace());
			}

			public void onSuccess(final String username) {
				usersService.findUsers(new AsyncCallback<List<String[]>>() {
					public void onFailure(Throwable caught) {
						System.err.println(caught.getStackTrace());
					}

					public void onSuccess(List<String[]> usersList) {
						ToggleButton tempToggle;
						Integer horizontalCounter = 1;
						if (usersList != null) {
							for (String[] userListItem : usersList) {
								if (!username.equals(userListItem[0])) {
									tempToggle = new ToggleButton(userListItem[0]);
									tempToggle.setSize("250px", "26px");
									switch (horizontalCounter) {
									case 1:
										verticalPanel.add(tempToggle);
										break;
									case 2:
										verticalPanel_1.add(tempToggle);
										break;
									case 3:
										verticalPanel_2.add(tempToggle);
										break;
									default:
										throw new IllegalStateException();
									}
									toggleButtonList.add(tempToggle);
									horizontalCounter++;
									if (horizontalCounter >= 4)
										horizontalCounter = 1;
								}
							}
						}
					}
				});
			}

		});

		categoryService.getAllCategories(new AsyncCallback<List<String>>() {
			public void onFailure(Throwable caught) {
				System.err.println(caught.getStackTrace());
			}

			public void onSuccess(List<String> results) {
				if (results.size() >= 1) {
					for (int i = 0; i < results.size(); i++)
						cmbbxStrategy.addItem(results.get(i));
				} else {
					cmbbxStrategy.setVisible(false);
					btnSubmit.setEnabled(false);
					lblNoCategoriesFound.setVisible(true);
				}
			}
		});

		btnSubmit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.print("The following people are selected: ");
				int count = 0;
				for (ToggleButton toggle : toggleButtonList) {
					if (toggle.isDown()) {
						usernameList.add(toggle.getText());
						System.out.print(toggle.getText() + " ");
						count++;
					}
				}

				System.out.print("\nTotal count: ");
				System.out.println(count + 1);

				turnService.findUnluckySoul(usernameList, cmbbxStrategy.getValue(cmbbxStrategy.getSelectedIndex()), new AsyncCallback<List<String>>() {
					public void onFailure(Throwable caught) {
						System.err.println(caught.getStackTrace());
					}

					public void onSuccess(List<String> result) {
						lblDriver.setText(result.get(0));
						usernameList.clear();

						badgeService.calculateBadges(result.get(1), new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								System.err.println(caught.getStackTrace());
							}

							public void onSuccess(Void result) {
								System.out.println("Badges Added");
							}
						});
					}
				});
			}
		});

		return turnAddPanel;
	}
}

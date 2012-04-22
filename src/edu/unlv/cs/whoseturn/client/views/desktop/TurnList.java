package edu.unlv.cs.whoseturn.client.views.desktop;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Lists all turns.
 */
public class TurnList extends AbstractNavigationView implements NavigationView {

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		// The body of the view.

		AbsolutePanel panel = new AbsolutePanel();
		panel.setSize("1000px", "500px");

		Label labelPlaceHolder = new Label();
		panel.add(labelPlaceHolder);
		labelPlaceHolder.setText("Turn List");

		final ToggleButton toggleButton = new ToggleButton("Toggle Button");
		toggleButton.setHTML("Single Toggle Button Example");
		panel.add(toggleButton, 40, 26);

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		panel.add(horizontalPanel, 105, 70);
		horizontalPanel.setSize("695px", "339px");

		VerticalPanel verticalPanel = new VerticalPanel();
		horizontalPanel.add(verticalPanel);
		verticalPanel.setSize("227px", "226px");

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		horizontalPanel.add(verticalPanel_1);
		verticalPanel_1.setSize("230px", "226px");

		ToggleButton toggleButton_1 = new ToggleButton("James Oravec");
		verticalPanel_1.add(toggleButton_1);

		ToggleButton toggleButton_2 = new ToggleButton("Ryan Lombardo");
		verticalPanel_1.add(toggleButton_2);

		ToggleButton toggleButton_3 = new ToggleButton("Shane Dieckmann");
		verticalPanel_1.add(toggleButton_3);

		VerticalPanel verticalPanel_2 = new VerticalPanel();
		horizontalPanel.add(verticalPanel_2);
		verticalPanel_2.setSize("229px", "226px");

		ToggleButton toggleButton_4 = new ToggleButton("James Oravec");
		verticalPanel_2.add(toggleButton_4);

		ToggleButton toggleButton_5 = new ToggleButton("Ryan Lombardo");
		verticalPanel_2.add(toggleButton_5);

		ToggleButton toggleButton_6 = new ToggleButton("Shane Dieckmann");
		verticalPanel_2.add(toggleButton_6);

		/**
		 * A list that we will be able to iterate through to see which buttons
		 * are toggle and which are not.
		 */
		final List<ToggleButton> toggleButtonList = new ArrayList<ToggleButton>();

		// Populate the list and vertical pane of toggle buttons.
		// This portion should be replaced by a query to the db. I'm hardcoding
		// 3 examples.
		ToggleButton tempToggle = new ToggleButton("James Oravec");
		verticalPanel.add(tempToggle);
		toggleButtonList.add(tempToggle);

		tempToggle = new ToggleButton("Ryan Lombardo");
		verticalPanel.add(tempToggle);
		toggleButtonList.add(tempToggle);

		tempToggle = new ToggleButton("Shane Dieckmann");
		verticalPanel.add(tempToggle);
		toggleButtonList.add(tempToggle);

		Button submitButton = new Button("Submit");
		panel.add(submitButton, 10, 421);
		submitButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				System.out.print("The following people are selected: ");
				int count = 0;
				for (ToggleButton toggle : toggleButtonList) {
					if (toggle.isDown()) {
						System.out.print(toggle.getText() + " ");
						count++;
					}
				}

				System.out.print("\nTotal count: ");
				System.out.println(count);

			}
		});

		toggleButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (toggleButton.isDown()) {
					System.out.println("Button is down yo.");
				} else {
					System.out.println("Up yo button.");
				}

			}
		});

		return panel;
	}
}

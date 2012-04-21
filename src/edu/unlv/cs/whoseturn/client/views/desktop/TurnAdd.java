package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import edu.unlv.cs.whoseturn.client.views.View2;

/**
 * Allows a user to add a Turn instance to the program.
 */
public class TurnAdd extends AbstractNavigationView implements NavigationView {

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		
		// TODO
		/**
		 * We need:
		 * 		list of users as toggle buttons for who is part of the turn
		 * 		a spinner widget to select the number of people to be picked
		 * 
		 * 		how are we handling confirmation messages?
		 * 			perhaps another panel at the bottom? status panel?
		 */
		
		// The body of the view.
		FlowPanel panel = new FlowPanel();
		Button button1 = new Button();
		button1.setText("Place holder.");
		button1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("overall").clear();
				RootPanel.get("overall").add((new View2()).asWidget());
			}
		});
		panel.add(button1);
		return panel;
	}

}

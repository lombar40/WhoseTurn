package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

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
		panel.setSize("1000px", "500px");
		
		Label labelPlaceHolder = new Label();
		labelPlaceHolder.setText("Turn Add");
		panel.add(labelPlaceHolder);
		
		return panel;
	}

}

package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

/**
 * Adds a user to the database.
 */
public class UserAdd extends AbstractNavigationView implements NavigationView {

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		// The body of the view.
		FlowPanel panel = new FlowPanel();
		
		Label labelPlaceHolder = new Label();
		labelPlaceHolder.setText("User Add");
		panel.add(labelPlaceHolder);
		
		return panel;
	}

}

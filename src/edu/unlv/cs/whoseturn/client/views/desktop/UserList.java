package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

/**
 * Displays a list of all users in the database.
 */
public class UserList extends AbstractNavigationView implements NavigationView {

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		// The body of the view.
		FlowPanel panel = new FlowPanel();
		panel.setSize("1000px", "500px");
		
		Label labelPlaceHolder = new Label();
		labelPlaceHolder.setText("User List");
		panel.add(labelPlaceHolder);
		
		return panel;
	}

}

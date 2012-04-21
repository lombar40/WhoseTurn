package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

/**
 * Displays a User's profile.
 */
public class UserDisplay extends AbstractNavigationView implements
		NavigationView {
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		FlowPanel panel = new FlowPanel();
		
		Label labelPlaceHolder = new Label();
		labelPlaceHolder.setText("User Display");
		panel.add(labelPlaceHolder);
		
		return panel;
	}
}

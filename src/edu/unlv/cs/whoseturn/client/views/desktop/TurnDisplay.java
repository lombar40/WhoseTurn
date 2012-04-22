package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

/**
 * Displays the details of a turn.
 */
public class TurnDisplay extends AbstractNavigationView implements
		NavigationView {

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		// The body of the view.
		FlowPanel panel = new FlowPanel();
		panel.setSize("1000px", "500px");
		
		Label labelPlaceHolder = new Label();
		labelPlaceHolder.setText("Turn Display");
		panel.add(labelPlaceHolder);
		
		return panel;
	}

}

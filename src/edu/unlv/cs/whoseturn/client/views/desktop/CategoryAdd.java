package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

/**
 * Used to allow an admin to add categories to the database.
 */
public class CategoryAdd extends AbstractNavigationView implements
		NavigationView {

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		// The body of the view.
		FlowPanel panel = new FlowPanel();
		
		Label labelPlaceHolder = new Label();
		labelPlaceHolder.setText("Category Add");
		panel.add(labelPlaceHolder);
		
		return panel;
	}

}

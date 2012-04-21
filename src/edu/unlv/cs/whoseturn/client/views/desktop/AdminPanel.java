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
 * Used to display stuff related to the administration of the program.
 */
public class AdminPanel extends AbstractNavigationView implements
		NavigationView {
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		FlowPanel panel = new FlowPanel();
		Button button1 = new Button();
		button1.setText("View1");
		button1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.println("view1.button1 clicked handled");
				RootPanel.get("overall").clear();
				RootPanel.get("overall").add((new View2()).asWidget());
			}
		});

		panel.add(button1);

		return panel;
	}
}

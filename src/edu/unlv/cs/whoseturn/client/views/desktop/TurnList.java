package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

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
		panel.add(toggleButton, 65, 51);
		toggleButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(toggleButton.isDown()){
					System.out.println("Button is down yo.");
				} else {
					System.out.println("Up yo button.");
				}
				
			}
		});
		
		
		return panel;
	}

}

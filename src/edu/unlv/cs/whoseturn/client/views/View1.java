package edu.unlv.cs.whoseturn.client.views;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class View1 {
	public Widget asWidget(){
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

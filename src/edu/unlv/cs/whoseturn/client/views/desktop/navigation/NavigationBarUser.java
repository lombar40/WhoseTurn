package edu.unlv.cs.whoseturn.client.views.desktop.navigation;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.View;
import edu.unlv.cs.whoseturn.client.views.ViewImpl2;
import edu.unlv.cs.whoseturn.client.views.desktop.TurnList;
import edu.unlv.cs.whoseturn.client.views.desktop.UserList;

/**
 * This is a navigation bar that will be seen by regular users.
 */
public class NavigationBarUser implements View {
    
    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public final Widget asWidget() {

        /**
         * A panel used for navigating between various views of our program.
         */
        FlowPanel navigationBar = new FlowPanel();
        navigationBar.setSize("1000px", "200");

        Button listTurnNavItem = new Button();
        listTurnNavItem.setText("Turns");
        listTurnNavItem.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                RootPanel.get("overall").clear();
                RootPanel.get("overall").add((new TurnList()).asWidget());
            }
        });
        
        navigationBar.add(listTurnNavItem);
        Button listUserNavItem = new Button();
        listUserNavItem.setText("Users");
        listUserNavItem.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                RootPanel.get("overall").clear();
                RootPanel.get("overall").add((new UserList()).asWidget());
            }
        });
        navigationBar.add(listUserNavItem);

        return navigationBar;
    }
}

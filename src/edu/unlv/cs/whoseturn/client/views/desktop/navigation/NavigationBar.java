package edu.unlv.cs.whoseturn.client.views.desktop.navigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlowPanel;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.View;
import edu.unlv.cs.whoseturn.client.views.desktop.CategoryList;
import edu.unlv.cs.whoseturn.client.views.desktop.TurnAdd;
import edu.unlv.cs.whoseturn.client.views.desktop.UserList;

/**
 * This is a navigation bar that will be seen by admin users.
 */
public class NavigationBar implements View {

	/**
     * The user service.
     */
    private final UsersServiceAsync usersService = GWT
            .create(UsersService.class);
    
    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public final Widget asWidget() {

        /**
         * A panel used for navigating between various views of our program.
         */
        final FlowPanel navigationBar = new FlowPanel();

        /**
         * Links to list categories.
         */
        /**
         * The navigation portion of the view.
         */
        usersService.isAdmin(new AsyncCallback<Boolean>() {
            public void onFailure(final Throwable caught) {
                System.err.println(caught.getStackTrace());
            }

            public void onSuccess(final Boolean isAdmin) {
                if(isAdmin) {
                	Button listCategory = new Button();
                    listCategory.setText("Categories");
                    listCategory.addClickHandler(new ClickHandler() {
                        public void onClick(final ClickEvent event) {
                            RootPanel.get("overall").clear();
                            RootPanel.get("overall").add((new CategoryList()).asWidget());
                        }
                    });
                    
                    navigationBar.add(listCategory);
                }
            }
        });
        
        Button listTurnNavItem = new Button();
        listTurnNavItem.setText("Turn");
        listTurnNavItem.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                RootPanel.get("overall").clear();
                RootPanel.get("overall").add((new TurnAdd()).asWidget());
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

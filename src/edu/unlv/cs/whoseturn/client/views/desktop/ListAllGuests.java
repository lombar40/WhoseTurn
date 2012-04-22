package edu.unlv.cs.whoseturn.client.views.desktop;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

/**
 * Lists all categories in the database.
 */
public class ListAllGuests extends AbstractNavigationView implements
        NavigationView {

    /**
     * The user service.
     */
    private final UsersServiceAsync usersService = GWT
            .create(UsersService.class);

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public final Widget bodyAsWidget() {
        // The body of the view.
        AbsolutePanel allGuestsList = new AbsolutePanel();
        allGuestsList.setSize("1000px", "500px");

        Label labelTitle = new Label();
        labelTitle.setStyleName("SectionHeader");
        labelTitle.setText("All Guests");
        allGuestsList.add(labelTitle);

        final ListBox guestListBox = new ListBox();
        allGuestsList.add(guestListBox, 10, 40);
        guestListBox.setSize("310px", "450px");
        guestListBox.setVisibleItemCount(5);

        usersService.findAllGuests(new AsyncCallback<List<String>>() {
            public void onFailure(final Throwable caught) {
                // TODO
            }

            public void onSuccess(final List<String> results) {
                if (results != null) {
                    for (int i = 0; i < results.size(); i++) {
                        guestListBox.addItem(results.get(i));
                    }
                }
            }
        });

        return allGuestsList;
    }

}

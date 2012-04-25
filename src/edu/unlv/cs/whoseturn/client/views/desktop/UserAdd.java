package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Adds a user to the database.
 */
public class UserAdd extends AbstractNavigationView implements NavigationView {

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
        AbsolutePanel userAddPanel = new AbsolutePanel();
        userAddPanel.setSize("1000px", "500px");

        Label lblLbltitle = new Label("User Add");
        lblLbltitle.setStyleName("SectionHeader");
        userAddPanel.add(lblLbltitle);

        final TextBox txtbxUsername = new TextBox();
        userAddPanel.add(txtbxUsername, 98, 42);
        txtbxUsername.setSize("161px", "16px");

        Label lblUsername = new Label("UserName:");
        userAddPanel.add(lblUsername, 10, 42);

        Label lblEmail = new Label("Email:");
        userAddPanel.add(lblEmail, 10, 80);
        
                final TextBox txtbxEmail = new TextBox();
                userAddPanel.add(txtbxEmail, 98, 80);
                txtbxEmail.setSize("161px", "20px");

        final CheckBox chckbxAdmin = new CheckBox("");
        userAddPanel.add(chckbxAdmin, 98, 118);

        final Label lblError = new Label("");
        lblError.setStyleName("serverResponseLabelError");
        userAddPanel.add(lblError, 141, 148);

        Button btnAdd = new Button("Add");
        userAddPanel.add(btnAdd, 98, 143);

        Label lblAdmin = new Label("Admin:");
        userAddPanel.add(lblAdmin, 10, 118);

        final Label lblSuccess = new Label("Successfully added user.");
        userAddPanel.add(lblSuccess, 141, 148);
        lblSuccess.setVisible(false);

        btnAdd.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                lblError.setText("");
                lblSuccess.setVisible(false);

                usersService.addNewUser(txtbxUsername.getText(),
                        txtbxEmail.getText(), chckbxAdmin.getValue(),
                        new AsyncCallback<String>() {
                            public void onFailure(final Throwable caught) {
                                lblError.setText("There was an unknown error.");
                                System.err.println(caught.getStackTrace());
                            }

                            public void onSuccess(final String result) {
                                if (!result.equals("Success")) {
                                    lblError.setText(result);
                                } else {
                                    lblSuccess.setVisible(true);
                                }
                            }
                        });
            }
        });

        return userAddPanel;
    }
}

package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

import com.google.gwt.user.client.ui.TextBox;

/**
 * POC that changes to view2 when the button is clicked.
 */
public class UserEdit extends AbstractNavigationView implements NavigationView {

    /**
     * The user service.
     */
    private final UsersServiceAsync usersService = GWT
            .create(UsersService.class);
    
    /**
     * The user name of a user we'll be editing.
     */
    private String userName;

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public final Widget bodyAsWidget() {
//        String url = GWT.getHostPageBaseURL();
//        if (!GWT.isProdMode()) {
//            url += "?gwt.codesvr=127.0.0.1:9997";
//        }
//        final String finalURL = url;
//
//        final AbsolutePanel profileManagementPanel = new AbsolutePanel();
//        profileManagementPanel.setSize("1000px", "500px");
//
//        Button btnLogout = new Button("Logout");
//        profileManagementPanel.add(btnLogout, 10, 75);
//
//        Label lblUsername = new Label("Username:");
//        profileManagementPanel.add(lblUsername, 10, 10);
//
//        final TextBox txtbxUsername = new TextBox();
//        profileManagementPanel.add(txtbxUsername, 79, 10);
//
//        Label lblNewLabel = new Label("New label");
//        profileManagementPanel.add(lblNewLabel, 384, 272);
//
//        btnLogout.addClickHandler(new ClickHandler() {
//            public void onClick(final ClickEvent event) {
//                usersService.getLogoutURL(finalURL,
//                        new AsyncCallback<String>() {
//                            public void onFailure(final Throwable caught) {
//                                // lblNewLabel.setText("FAILURE");
//                            }
//
//                            public void onSuccess(final String result) {
//                                Window.open(result, "_self", "");
//                            }
//                        });
//            }
//        });
//
//        usersService.getUsername(new AsyncCallback<String>() {
//            public void onFailure(final Throwable caught) {
//                // lblNewLabel.setText("FAILURE");
//            }
//
//            public void onSuccess(final String result) {
//                txtbxUsername.setText(result);
//            }
//        });
//
//        return profileManagementPanel;
        
        // The body of the view.
        AbsolutePanel panel = new AbsolutePanel();
        panel.setSize("1000px", "500px");

        Label lblLbltitle = new Label("User Edit");
        lblLbltitle.setStyleName("SectionHeader");
        panel.add(lblLbltitle);

        final TextBox txtbxUsername = new TextBox();
        panel.add(txtbxUsername, 98, 42);
        txtbxUsername.setSize("161px", "16px");

        Label lblUsername = new Label("UserName:");
        panel.add(lblUsername, 10, 42);

        Label lblEmail = new Label("Email:");
        panel.add(lblEmail, 10, 80);

        final CheckBox chckbxAdmin = new CheckBox("");
        panel.add(chckbxAdmin, 98, 118);

        final Label lblError = new Label("");
        lblError.setStyleName("serverResponseLabelError");
        panel.add(lblError, 141, 148);

        final TextBox txtbxEmail = new TextBox();
        panel.add(txtbxEmail, 98, 80);
        txtbxEmail.setSize("161px", "20px");

        Button btnSave = new Button("Save");
        panel.add(btnSave, 98, 187);

        Label lblAdmin = new Label("Admin:");
        panel.add(lblAdmin, 10, 118);

        final Label lblSuccess = new Label("Successfully added user.");
        panel.add(lblSuccess, 141, 192);
        
        Label lblDeleted = new Label("Deleted:");
        panel.add(lblDeleted, 10, 143);
        lblDeleted.setSize("41px", "16px");
        
        CheckBox chkDeleted = new CheckBox("");
        panel.add(chkDeleted, 98, 143);
        chkDeleted.setSize("20px", "19px");
        lblSuccess.setVisible(false);

        btnSave.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                lblError.setText("");
                lblSuccess.setVisible(false);

                usersService.addNewUser(txtbxUsername.getText(),
                        txtbxEmail.getText(), chckbxAdmin.getValue(),
                        new AsyncCallback<String>() {
                            public void onFailure(final Throwable caught) {
                                lblError.setText("There was an unknown error.");
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

        return panel;
    }

    /**
     * Set the user name, so we know which record to edit.
     * 
     * @param userName The username.
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }
}

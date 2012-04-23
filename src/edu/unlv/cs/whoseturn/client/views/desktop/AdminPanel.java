package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;

/**
 * Used to display stuff related to the administration of the program.
 */
public class AdminPanel extends AbstractNavigationView implements
        NavigationView {

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public final Widget bodyAsWidget() {
        FlowPanel panel = new FlowPanel();
        panel.setSize("1000px", "500px");
        Label labelPlaceHolder = new Label();
        labelPlaceHolder.setText("Admin Panel");
        panel.add(labelPlaceHolder);

        return panel;
    }
}

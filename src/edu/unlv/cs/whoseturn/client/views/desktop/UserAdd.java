package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;

/**
 * Adds a user to the database.
 */
public class UserAdd extends AbstractNavigationView implements NavigationView {

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		// The body of the view.
		AbsolutePanel panel = new AbsolutePanel();
		
		Label labelPlaceHolder = new Label();
		labelPlaceHolder.setText("User Add");
		panel.add(labelPlaceHolder, 10, 10);
		
		TextBox textBox = new TextBox();
		panel.add(textBox, 134, 82);
		textBox.setSize("161px", "16px");
		
		Label lblNewLabel = new Label("keyString:");
		panel.add(lblNewLabel, 46, 49);
		
		Label lblUsername = new Label("UserName:");
		panel.add(lblUsername, 46, 82);
		lblUsername.setSize("64px", "16px");
		
		Label lblEmail = new Label("Email:");
		panel.add(lblEmail, 72, 130);
		lblEmail.setSize("56px", "16px");
		
		Label lblPenaltycount = new Label("PenaltyCount");
		panel.add(lblPenaltycount, 30, 158);
		lblPenaltycount.setSize("85px", "16px");
		
		CheckBox chckbxDeleted = new CheckBox("Deleted");
		panel.add(chckbxDeleted, 134, 232);
		
		CheckBox chckbxAdmin = new CheckBox("Admin");
		panel.add(chckbxAdmin, 134, 207);
		chckbxAdmin.setSize("70px", "19px");
		
		Label label_4 = new Label("111222333");
		panel.add(label_4, 134, 49);
		label_4.setSize("56px", "16px");
		
		TextBox textBox_1 = new TextBox();
		panel.add(textBox_1, 134, 120);
		textBox_1.setSize("165px", "20px");
		
		TextBox textBox_2 = new TextBox();
		panel.add(textBox_2, 134, 158);
		textBox_2.setSize("165px", "20px");
		
		Button btnAdd = new Button("Add");
		panel.add(btnAdd, 134, 257);
		
		return panel;
	}
}

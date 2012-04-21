package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Used to allow an admin to add categories to the database.
 */
public class CategoryAdd extends AbstractNavigationView implements
		NavigationView {

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget bodyAsWidget() {
		// The body of the view.
		AbsolutePanel panel = new AbsolutePanel();		
		
		Label lblKeystring = new Label("Name:");
		panel.add(lblKeystring, 105, 72);
		
		CheckBox chckbxNewCheckBox = new CheckBox("Admin");
		panel.add(chckbxNewCheckBox, 155, 164);
		
		CheckBox chckbxNewCheckBox_1 = new CheckBox("Deleted");
		panel.add(chckbxNewCheckBox_1, 155, 186);
		
		Label labelPlaceHolder = new Label();
		panel.add(labelPlaceHolder);
		labelPlaceHolder.setText("Category Add");
		
		Label lblEmail = new Label("Strategy:");
		panel.add(lblEmail, 85, 109);
		lblEmail.setSize("59px", "16px");
		
		TextBox textBox_1 = new TextBox();
		panel.add(textBox_1, 160, 72);
		textBox_1.setSize("165px", "15px");
		
		Label label_1 = new Label("11112222333");
		panel.add(label_1, 160, 50);
		label_1.setSize("59px", "16px");
		
		Label label_2 = new Label("keyString: ");
		panel.add(label_2, 84, 46);
		label_2.setSize("59px", "16px");
		
		Button btnAdd = new Button("Add");
		panel.add(btnAdd, 155, 211);
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("Least Recently Gone");
		comboBox.addItem("Completely Random");
		comboBox.addItem("Always Casey");
		panel.add(comboBox, 160, 105);
		comboBox.setSize("171px", "20px");
		
		Label lblTimeBoundaryIn = new Label("Time Boundary In Hours:");
		panel.add(lblTimeBoundaryIn, 0, 131);
		lblTimeBoundaryIn.setSize("149px", "16px");
		
		TextBox textBox = new TextBox();
		panel.add(textBox, 160, 131);
		textBox.setSize("165px", "15px");
		
		return panel;
	}
}

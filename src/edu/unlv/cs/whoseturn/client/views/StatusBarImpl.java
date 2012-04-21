package edu.unlv.cs.whoseturn.client.views;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Status bar give feedback to the admin/user as they use the program.
 */
public class StatusBarImpl implements StatusBar {

	/**
	 * String that contains the status to display in the status view.
	 */
	private String status = "";
	
	/**
	 * Default Constructor.
	 */
	public StatusBarImpl() {
		//status = "";
		status = "Status bar test";
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Widget asWidget(){
		
		// The body of the view.
		FlowPanel panel = new FlowPanel();
		Label statusLabel = new Label();
		statusLabel.setText(status);
		panel.add(statusLabel);
		return panel;
	}
	
	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	
}

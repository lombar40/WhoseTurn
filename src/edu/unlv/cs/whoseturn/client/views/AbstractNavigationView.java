package edu.unlv.cs.whoseturn.client.views;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.desktop.navigation.NavigationBar;

/**
 * An abstract class which hasw the asWidget() method implemented. This allows
 * for views of the navigationView type to automatically have the navigation bar
 * at the top.
 * 
 * By including the navigation bar as part of the view, we are able to make sure
 * that if a user logs out that the navigation bar will no longer display at the
 * top of the screen.
 */
public abstract class AbstractNavigationView implements NavigationView {

	/**
	 * The status view portion of the navigation view.
	 */
	private StatusBar statusBar;

	/**
	 * Default constructor.
	 */
	public AbstractNavigationView() {
		statusBar = new StatusBarImpl();
	}

	@Override
	public final Widget asWidget() {
		/**
		 * The main panel for the view.
		 */
		final FlowPanel mainPanel = new FlowPanel();

		/**
		 * The navigation portion of the view.
		 */
		NavigationBar navPanel = new NavigationBar();
		mainPanel.add(navPanel.asWidget());

		// The body of the view.
		mainPanel.add(bodyAsWidget());

		// Add status bar to the bottom of the navigation view.
		mainPanel.add(statusBar.asWidget());

		return mainPanel;
	}

	/**
	 * Sets the status of the navigation bar.
	 * 
	 * @param status
	 *            as a string.
	 */
	public final void setStatus(final String status) {
		statusBar.setStatus(status);
	}

}

package edu.unlv.cs.whoseturn.client.views.desktop;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.CategoryService;
import edu.unlv.cs.whoseturn.client.CategoryServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Lists all categories in the database.
 */
public class CategoryList extends AbstractNavigationView implements NavigationView {

	/**
	 * The category service.
	 */
	private final CategoryServiceAsync categoryService = GWT.create(CategoryService.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public final Widget bodyAsWidget() {
		// The body of the view.
		AbsolutePanel categoryListPanel = new AbsolutePanel();
		categoryListPanel.setSize("1000px", "500px");

		Label lblTitle = new Label();
		lblTitle.setStyleName("SectionHeader");
		lblTitle.setText("Category List");
		categoryListPanel.add(lblTitle);

		final ListBox categoryListBox = new ListBox();
		categoryListPanel.add(categoryListBox, 10, 40);
		categoryListBox.setSize("222px", "450px");
		categoryListBox.setVisibleItemCount(5);

		categoryService.getAllCategories(new AsyncCallback<List<String>>() {
			public void onFailure(final Throwable caught) {
				System.err.println(caught.getStackTrace());
			}

			public void onSuccess(final List<String> results) {
				if (results != null) {
					for (int i = 0; i < results.size(); i++) {
						categoryListBox.addItem(results.get(i));
					}
				}
			}
		});

		// Add User
		final Button btnAddCategory = new Button("Add Category");
		btnAddCategory.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				RootPanel.get("overall").clear();
				RootPanel.get("overall").add((new CategoryAdd()).asWidget());
			}
		});

		categoryListPanel.add(btnAddCategory, 256, 106);
		btnAddCategory.setSize("162px", "28px");

		// Edit User
		final Button btnEditCategory = new Button("Edit Category");
		btnEditCategory.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				RootPanel.get("overall").clear();
				CategoryEdit categoryEdit = new CategoryEdit();

				// Check to make sure a record is selected before trying to
				// redirect.
				if (categoryListBox.getSelectedIndex() > -1) {
					categoryEdit.categoryName(categoryListBox.getItemText(categoryListBox.getSelectedIndex()));
					RootPanel.get("overall").add(categoryEdit.asWidget());
				}
			}
		});

		categoryListPanel.add(btnEditCategory, 256, 149);
		btnEditCategory.setSize("162px", "28px");

		categoryListBox.setSelectedIndex(0);
		
		return categoryListPanel;
	}
}

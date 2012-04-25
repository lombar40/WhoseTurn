package edu.unlv.cs.whoseturn.client.views.desktop;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.CategoryService;
import edu.unlv.cs.whoseturn.client.CategoryServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Allows an admin to edit categories in the database.
 */
public class CategoryEdit extends AbstractNavigationView implements
        NavigationView {

    /**
     * The category service.
     */
    private final CategoryServiceAsync categoryService = GWT
            .create(CategoryService.class);
    
    /**
	 * The category name of a category we'll be editing.
	 */
	private String categoryName;

	/**
	 * Contains the information about the category to populate the form with.
	 * 
	 * A list of zero or one that has a string[] with the information.
	 * {name, strategy, timeboundary, deleted}
	 */
	private List<String[]> categoryInfo;
	
    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public final Widget bodyAsWidget() {
    	// The body of the view.
    			final AbsolutePanel categoryEditPanel = new AbsolutePanel();
    			categoryEditPanel.setSize("1000px", "500px");

    			Label lblLbltitle = new Label("Category Edit");
    			lblLbltitle.setStyleName("SectionHeader");
    			categoryEditPanel.add(lblLbltitle);

    			Label lblCategoryName = new Label("Category Name:");
    			categoryEditPanel.add(lblCategoryName, 66, 52);
    			final TextBox txtbxCategoryName = new TextBox();
    			categoryEditPanel.add(txtbxCategoryName, 167, 46);
    			txtbxCategoryName.setSize("161px", "16px");

    			Label lblStrategy = new Label("Strategy:");
    			categoryEditPanel.add(lblStrategy, 107, 82);
    			
    			final ListBox cmbbxStrategy = new ListBox();
    			categoryEditPanel.add(cmbbxStrategy, 167, 79);
    			cmbbxStrategy.setSize("167px", "22px");
    			final TextBox txtbxTimeBoundary = new TextBox();
    			categoryEditPanel.add(txtbxTimeBoundary, 167, 105);
    			txtbxTimeBoundary.setSize("161px", "20px");

    			Label lblTimeBoundary = new Label("Time boundary in hours:");
    			categoryEditPanel.add(lblTimeBoundary, 20, 113);

    			Label lblDeleted = new Label("Deleted:");
    			categoryEditPanel.add(lblDeleted, 112, 144);
    			final CheckBox chkDeleted = new CheckBox("");
    			categoryEditPanel.add(chkDeleted, 167, 143);
    			chkDeleted.setSize("20px", "19px");

    			final Label lblError = new Label("");
    			lblError.setStyleName("serverResponseLabelError");
    			categoryEditPanel.add(lblError, 161, 192);

    			Button btnSave = new Button("Save");
    			categoryEditPanel.add(btnSave, 108, 192);

    			final Label lblSuccess = new Label("Successfully updated category.");
    			categoryEditPanel.add(lblSuccess, 171, 197);
    			lblSuccess.setVisible(false);

    			categoryService.getAllStrategies(new AsyncCallback<List<String>>() {
    				public void onFailure(final Throwable caught) {
    					System.err.println(caught.getStackTrace());
    				}

    				public void onSuccess(final List<String> results) {
    					if (results != null) {
    						for (int i = 0; i < results.size(); i++) {
    							cmbbxStrategy.addItem(results.get(i));
    						}
    					}
    					
    					// Get the user info and populate it to the form.
    	    			categoryService.getCategoryInfo(categoryName, new AsyncCallback<List<String[]>>() {

    	    				@Override
    	    				public void onFailure(Throwable caught) {
    	    					System.err.println(caught.getStackTrace());
    	    				}

    	    				@Override
    	    				public void onSuccess(List<String[]> result) {
    	    					categoryInfo = result;

    	    					// Set category name.
    	    					txtbxCategoryName.setText(categoryInfo.get(0)[0]);
    	    					
    	    					// Set the strategy
    	    					String tempStrategy;
    	    					for(int i=0; i < cmbbxStrategy.getItemCount(); i++) {
    	    						tempStrategy = cmbbxStrategy.getItemText(i);
    	    						if (tempStrategy.equals(categoryInfo.get(0)[1]))
    	    							cmbbxStrategy.setSelectedIndex(i);
    	    					}    	    					

    	    					// Set time boundary
    	    					txtbxTimeBoundary.setText(categoryInfo.get(0)[2]);

    	    					// Set deleted check box.
    	    					if (categoryInfo.get(0)[3].toLowerCase().equals("true")) {
    	    						chkDeleted.setValue(true);
    	    					} else {
    	    						chkDeleted.setValue(false);
    	    					}
    	    				}
    	    			});
    				}
    			});
    			
    			

    			btnSave.addClickHandler(new ClickHandler() {
    				public void onClick(final ClickEvent event) {
    					lblError.setText("");
    					lblSuccess.setVisible(false);
    					
    					categoryService.categoryUpdate(categoryName, txtbxCategoryName.getText(),
						cmbbxStrategy.getItemText(cmbbxStrategy.getSelectedIndex()), txtbxTimeBoundary.getValue(),
						chkDeleted.getValue(), new AsyncCallback<String>() {
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
    			
    			return categoryEditPanel;
    }
    
    /**
	 * Set the category name, so we know which record to edit.
	 * 
	 * @param categoryName
	 *            The categoryname.
	 */
	public void categoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}

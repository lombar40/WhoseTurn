package edu.unlv.cs.whoseturn.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CategoryServiceAsync {
	public void addCategory(String category, String strategy, Integer timeBoundary, AsyncCallback<String> async);
	public void removeCategory(String category, AsyncCallback<String> async);
	public void getAllCategories(AsyncCallback<List<String>> async);
	public void loadInitialCategories(AsyncCallback<Void> async);
	public void getAllStrategies(AsyncCallback<List<String>> async);
}

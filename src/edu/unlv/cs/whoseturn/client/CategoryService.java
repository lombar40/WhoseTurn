package edu.unlv.cs.whoseturn.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("categories")
public interface CategoryService extends RemoteService {
	public String addCategory(String category, String strategy, Integer timeBoundary);
	public String removeCategory(String category);
	public List<String> getAllCategories();
	public void loadInitialCategories();
	public List<String> getAllStrategies();
}

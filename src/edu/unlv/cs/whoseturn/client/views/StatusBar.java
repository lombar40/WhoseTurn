package edu.unlv.cs.whoseturn.client.views;

public interface StatusBar extends View {
	/**
	 * Status getter.
	 * 
	 * @return status as a string.
	 */
	public String getStatus();

	/**
	 * Status setter.
	 * 
	 * @param status as a string.
	 */
	public void setStatus(String status);

}

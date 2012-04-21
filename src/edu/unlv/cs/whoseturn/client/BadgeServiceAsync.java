package edu.unlv.cs.whoseturn.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.unlv.cs.whoseturn.domain.Turn;
import edu.unlv.cs.whoseturn.domain.User;

public interface BadgeServiceAsync {
	
	void Jackass(Turn turn, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void TeamCheater(Turn turn, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void CornerStone(Turn turn, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void HumanSacrifice(Turn turn, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void SixMinuteAbs(Turn turn, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void CrappedOut(Turn turn, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void SnowWhite(Turn turn, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void FML(Turn turn, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void Saint(User user, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void Socialite(Turn turn, AsyncCallback<Void> callback)
			throws IllegalArgumentException;	
	/*void SOLBadge(User user, AsyncCallback<Void> callback)
			throws IllegalArgumentException;*/
	void StormShadow(User user, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void MythBusters(User user, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

}

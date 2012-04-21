package edu.unlv.cs.whoseturn.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.unlv.cs.whoseturn.domain.Turn;
import edu.unlv.cs.whoseturn.domain.User;

@RemoteServiceRelativePath("badge")
public interface BadgeService extends RemoteService {
	void Jackass(Turn turn) throws IllegalArgumentException;
	void TeamCheater(Turn turn) throws IllegalArgumentException;
	void CornerStone(Turn turn) throws IllegalArgumentException;
	void HumanSacrifice(Turn turn) throws IllegalArgumentException;
	void SixMinuteAbs(Turn turn) throws IllegalArgumentException;
	void CrappedOut(Turn turn) throws IllegalArgumentException;
	void SnowWhite(Turn turn) throws IllegalArgumentException;
	void FML(Turn turn) throws IllegalArgumentException;
	void Saint(User user) throws IllegalArgumentException;
	void Socialite(Turn turn) throws IllegalArgumentException;	
	//void SOLBadge(User user) throws IllegalArgumentException;
	void StormShadow(User user) throws IllegalArgumentException;
	void MythBusters(User user) throws IllegalArgumentException;
}

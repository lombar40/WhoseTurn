package edu.unlv.cs.whoseturn.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


import edu.unlv.cs.whoseturn.domain.Turn;
import edu.unlv.cs.whoseturn.domain.User;

@RemoteServiceRelativePath("badge")
public interface BadgeService extends RemoteService {
	/**
	 * Used to award the Jackass badge. Checks to see if the user submitted a turn with only himself. Awards the
	 * Jackass badge to the submitter.
	 * 
	 * @param turn The turn to analyze to determine if someone gets the badge.
	 * @throws IllegalArgumentException
	 */
	void Jackass(Turn turn) throws IllegalArgumentException;

	/**
	 * Used to award the TeamCheater badge. Checks to see if everybody in the turn was selected. Awards the Team
	 * Cheater badge to the submitting user.
	 * 
	 * @param turn The turn to analyze to determine if someone gets the badge.
	 * @throws IllegalArgumentException
	 */
	void TeamCheater(Turn turn) throws IllegalArgumentException;

	/**
	 * Used to award the CornerStone badge. Checks to see if the user was selected or not out of a group of 4. Awards
	 * the Corner Stone badge if selected. Awards the Don't Cross The Streams
	 * badge if not selected.
	 * 
	 * @param turn The turn to analyze to determine if someone gets the badge.
	 * @throws IllegalArgumentException
	 */
	void CornerStone(Turn turn) throws IllegalArgumentException;

	/**
	 * Used to award the HumanSacrifice badge. Checks to see if the user was selected or not out of a group of 5. Awards
	 * the Human Sacrifice badge if selected. Awards the Not The Thumb badge if
	 * not selected.
	 * 
	 * @param turn The turn to analyze to determine if someone gets the badge.
	 * @throws IllegalArgumentException
	 */
	void HumanSacrifice(Turn turn) throws IllegalArgumentException;

	/**
	 * Used to award the SixMinuteAbs badge. Checks to see if the user was selected or not out of a group of 6. Awards
	 * the Six Minute Abs badge if selected. Awards the Pick Up Sticks badge if
	 * not selected.
	 * 
	 * @param turn The turn to analyze to determine if someone gets the badge.
	 * @throws IllegalArgumentException
	 */
	void SixMinuteAbs(Turn turn) throws IllegalArgumentException;

	/**
	 * Used to award the CrappedOut badge. Checks to see if the user was selected or not out of a group of 7. Awards
	 * the Crapped Out badge if selected. Awards the Lucky No. 7 badge if not
	 * selected.
	 * 
	 * @param turn The turn to analyze to determine if someone gets the badge.
	 * @throws IllegalArgumentException
	 */
	void CrappedOut(Turn turn) throws IllegalArgumentException;

	/**
	 * Used to award the SnowWhite badge. Checks to see if the user was selected or not out of a group of 8. Awards
	 * the Snow White badge if selected. Awards the Dwarf badge if not selected.
	 * 
	 * @param turn The turn to analyze to determine if someone gets the badge.
	 * @throws IllegalArgumentException
	 */
	void SnowWhite(Turn turn) throws IllegalArgumentException;

	/**
	 * Used to award the FML badge. Checks to see if the user was selected or not out of a group of more than
	 * 8 people. Awards the FML badge if selected. Awards the Statistically
	 * Speaking badge if not selected.
	 * 
	 * @param turn The turn to analyze to determine if someone gets the badge.
	 * @throws IllegalArgumentException
	 */
	void FML(Turn turn) throws IllegalArgumentException;

	/**
	 * Used to award the Saint badge. Checks to see if the user has never lied for 50 turns.
	 * 
	 * @param user The user to check to see if they get the Saint badge.
	 * @throws IllegalArgumentException
	 */
	void Saint(User user) throws IllegalArgumentException;
	
	/**
	 * Used to award the Socialite badge. Checks to see if the users have participated in a turn with more than 10
	 * people.
	 * 
	 * @param turn The turn to analyze to determine if someone gets the badge.
	 * @throws IllegalArgumentException
	 */
	void Socialite(Turn turn) throws IllegalArgumentException;	
	
	/**
	 * Used to award the Rookie badge. Checks to see if the user has participated in 10 turns.
	 * 
	 * @param user The user to check to see if they get the Rookie.
	 * @throws IllegalArgumentException
	 */
	void Rookie(User user) throws IllegalArgumentException;
	
	/**
	 * Used to award the Veteran badge. Checks to see if the user has participated in 100 turns.
	 * 
	 * @param user The user to check to see if they get the Veteran badge.
	 * @throws IllegalArgumentException
	 */
	void Veteran(User user) throws IllegalArgumentException;
	
	/**
	 * Used to award the Elite badge. Checks to see if the user has participated in 250 turns.
	 * 
	 * @param user The user to check to see if they get the Elite badge.
	 * @throws IllegalArgumentException
	 */
	void Elite(User user) throws IllegalArgumentException;
	
	/**
	 * Used to award the WhoseTurnMaster badge.  Checks to see if the user has received every
	 * badge (excludes the special StormShadow and MythBusters badges).
	 * 
	 * @param user The user to check to see if they have every badge (excluding StormShadow and MythBusters badges).
	 * @throws IllegalArgumentException
	 */
	void WhoseTurnMaster(User user) throws IllegalArgumentException;

	/**
	 * Used to award the StormShadow badge.Checks to see if the user is Chris Jones. Awards the StormShadow badge if
	 * true.
	 * 
	 * @param user The user to check to see if they get the StormShadow badge.
	 * @throws IllegalArgumentException
	 */
	void StormShadow(User user) throws IllegalArgumentException;

	/**
	 * Used to award the MythBusters badge. Checks to see if the user is Matthew Sowders. Awards the MythBusters
	 * badge if true.
	 * 
	 * @param user The user to check to see if they get the MythBusters badge.
	 * @throws IllegalArgumentException
	 */
	void MythBusters(User user) throws IllegalArgumentException;
	
	/**
	 * Used to calculate badges. This method initiates the calculation of all badges.
	 * 
	 * @param turn The turn to analyze.
	 */
	public void calculateBadges(Turn turn);
	
}
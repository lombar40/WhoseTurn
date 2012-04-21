package edu.unlv.cs.whoseturn.domain;

import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Badge {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String keyString;
	
	@Persistent
	private Integer badgeId;
	
	@Persistent
    private String badgeName;

	@Persistent
    private String badgeCriteria;

	@Persistent
    private Boolean deleted;
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public String getBadgeName() {
		return badgeName;
	}

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

	public String getBadgeCriteria() {
		return badgeCriteria;
	}

	public void setBadgeCriteria(String badgeCriteria) {
		this.badgeCriteria = badgeCriteria;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Integer badgeId) {
		this.badgeId = badgeId;
	}
	
	// *BADGES* //
	/**
	 * Checks to see if the user submitted a turn with only one person.
	 * Awards the Jackass badge to the submitter.
	 * @param turn The turn to check.
	 */
	public void Jackass(Turn turn){
		Set<String> turn_items = turn.getTurnItems();
		
		// get the keys of the turn items 
		for (String turn_key : turn_items){
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			
			BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeId);
			
			// Badge Check
			if (turn.getNumberOfUsers() == 1){
				// Jackass badge
				if (badge.getCount() == 0){
					user.addBadge(badge);
				}
				Integer badgeCount = badge.getCount();
				badgeCount++;
				badge.setCount(badgeCount);			
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if everybody in the turn was selected.
	 * Awards the Team Cheater badge to the submitting user.
	 * @param turn The turn to check.
	 */
	public void TeamCheater(Turn turn){
		;
	}
	
	/**
	 * Checks to see if the user was selected or not out of a group of 4.
	 * Awards the Corner Stone badge if selected.
	 * Awards the Don't Cross The Streams badge if not selected.
	 * @param turn The turn to check.
	 */
	public void CornerStone(Turn turn){
		Set<String> turn_items = turn.getTurnItems();
		
		// get the keys of the turn items 
		for (String turn_key : turn_items){
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			
			BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeId);
			
			// Badge Check
			if (turn.getNumberOfUsers() == 4){
				if (turn_item.getSelected()){
					// Corner Stone badge
					if (badge.getCount() == 0){
						user.addBadge(badge);
					}
					Integer badgeCount = badge.getCount();
					badgeCount++;
					badge.setCount(badgeCount);
				}
				if (!turn_item.getSelected())
					// Don't Cross The Streams badge
					;				
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the user was selected or not out of a group of 5.
	 * Awards the Human Sacrifice badge if selected.
	 * Awards the Not The Thumb badge if not selected.
	 * @param turn The turn to check.
	 */
	public void HumanSacrifice(Turn turn){
		Set<String> turn_items = turn.getTurnItems();
		
		// get the keys of the turn items 
		for (String turn_key : turn_items){
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			
			BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeId);
			
			// Badge Check
			if (turn.getNumberOfUsers() == 5){
				if (turn_item.getSelected()){
					// Human Sacrifice badge
					if (badge.getCount() == 0){
						user.addBadge(badge);
					}
					Integer badgeCount = badge.getCount();
					badgeCount++;
					badge.setCount(badgeCount);
				}
				if (!turn_item.getSelected())
					// Not The Thumb badge
					;				
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the user was selected or not out of a group of 6.
	 * Awards the Six Minute Abs badge if selected.
	 * Awards the Pick Up Sticks badge if not selected.
	 * @param turn The turn to check.
	 */
	public void SixMinuteAbs(Turn turn){
		Set<String> turn_items = turn.getTurnItems();
		
		// get the keys of the turn items 
		for (String turn_key : turn_items){
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			
			BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeId);
			
			// Badge Check
			if (turn.getNumberOfUsers() == 6){
				if (turn_item.getSelected()){
					// Six Minute Abs badge
					if (badge.getCount() == 0){
						user.addBadge(badge);
					}
					Integer badgeCount = badge.getCount();
					badgeCount++;
					badge.setCount(badgeCount);
				}
				if (!turn_item.getSelected())
					// Pick Up Sticks
					;				
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the user was selected or not out of a group of 7.
	 * Awards the Crapped Out badge if selected.
	 * Awards the Lucky No. 7 badge if not selected.
	 * @param turn The turn to check.
	 */
	public void CrappedOut(Turn turn){
		Set<String> turn_items = turn.getTurnItems();
		
		// get the keys of the turn items 
		for (String turn_key : turn_items){
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			
			BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeId);
			
			// Badge Check
			if (turn.getNumberOfUsers() == 7){
				if (turn_item.getSelected()){
					// Crapped Out badge
					if (badge.getCount() == 0){
						user.addBadge(badge);
					}
					Integer badgeCount = badge.getCount();
					badgeCount++;
					badge.setCount(badgeCount);
				}
				if (!turn_item.getSelected())
					// Lucky No. 7 badge
					;				
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the user was selected or not out of a group of 8.
	 * Awards the Snow White badge if selected.
	 * Awards the Dwarf badge if not selected.
	 * @param turn The turn to check.
	 */
	public void SnowWhite(Turn turn){
		Set<String> turn_items = turn.getTurnItems();
		
		// get the keys of the turn items 
		for (String turn_key : turn_items){
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			
			BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeId);
			
			// Badge Check
			if (turn.getNumberOfUsers() == 8){
				if (turn_item.getSelected()){
					// Snow White badge
					if (badge.getCount() == 0){
						user.addBadge(badge);
					}
					Integer badgeCount = badge.getCount();
					badgeCount++;
					badge.setCount(badgeCount);
				}
				if (!turn_item.getSelected())
					// Dwarf badge
					;				
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the user was selected or not out of a group of more than 8 people.
	 * Awards the FML badge if selected.
	 * Awards the Statistically Speaking badge if not selected.
	 * @param turn The turn to check.
	 */
	public void FML(Turn turn){
		Set<String> turn_items = turn.getTurnItems();
		
		// get the keys of the turn items 
		for (String turn_key : turn_items){
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			
			BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeId);
			
			// Badge Check
			if (turn.getNumberOfUsers() > 8){
				if (turn_item.getSelected()){
					// FML badge
					if (badge.getCount() == 0){
						user.addBadge(badge);
					}
					Integer badgeCount = badge.getCount();
					badgeCount++;
					badge.setCount(badgeCount);
				}
				if (!turn_item.getSelected())
					// Statistically Speaking badge
					;				
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the user is Chris Jones.
	 * Awards the StormShadow badge if true.
	 * @param user The user to check.
	 */
	public void StormShadow(User user){
		if (user.getUsername().equals("Chris Jones")){
			BadgeAwarded badge = new BadgeAwarded();
			user.addBadge(badge);
		}
	}
	
	/**
	 * Checks to see if the user is Matthew Sowders.
	 * Awards the MythBusters badge if true.
	 * @param user The user to check.
	 */
	public void MythBusters(User user){
		if (user.getUsername().equals("Matthew Sowders")){
			BadgeAwarded badge = new BadgeAwarded();
			user.addBadge(badge);
		}
	}
	
	/**
	 * Checks to see if the user has never lied for 50 turns.
	 * Awards the Saint badge.
	 * @param user The user to check.
	 */
	public void Saint(User user){
		Integer countTurns = user.getTurnItems().size();
		boolean willAward = true;
		
		if (countTurns == 50){
			for (String badgeKeyString : user.getBadges()){
				// get key for the BadgeAwarded and retrieve the object
				Key badgeKey = KeyFactory.stringToKey(badgeKeyString);
				BadgeAwarded userBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);
				
				// check if user has a liar badge
				// if true, don't award Saint badge
				//*** Not sure how to check what type of badge it is from BadgeAwarded ***///
				if (userBadge.getBadgeId().equals(badgeId) /*<-- place holder for a liar badge*/){
					willAward = false;
					break;
				}
			}
			
			if (willAward){
				BadgeAwarded SaintBadge = new BadgeAwarded();	// place holder
				user.addBadge(SaintBadge);
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the user has been selected for 5 turns in a row.
	 * Awards the SOL badge.
	 * @param user
	 */
	public void SOLBadge(User user){
		int consecutive_count = 0;
		boolean consecutive = false;
		for (String turnItemKeyString : user.getTurnItems()){
			// get key for the TurnItem and retrieve the object
			Key turnItemKey = KeyFactory.stringToKey(turnItemKeyString);	
			TurnItem turnItem = pm.getObjectById(TurnItem.class, turnItemKey);

			if (turnItem.getSelected()){
				consecutive = true;
				consecutive_count++;
			}
			if (!turnItem.getSelected()){
				consecutive = false;
				consecutive_count = 0;
			}
			
			// Badge condition check
			if (consecutive && consecutive_count >= 5){
				BadgeAwarded SOLBadge = new BadgeAwarded();	// place holder
				user.addBadge(SOLBadge);
				break;
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the users have participated in a turn with more than 10 people.
	 * Awards the Socialite badge.
	 * @param turn The turn to check.
	 */
	public void Socialite(Turn turn){
		Integer number_of_users = turn.getNumberOfUsers();
		
		if (number_of_users > 10){
			for (String turnItemKeyString : turn.getTurnItems()){
				// get key for the TurnItem and retrieve the object
				Key turnItemKey = KeyFactory.stringToKey(turnItemKeyString);	
				TurnItem turnItem = pm.getObjectById(TurnItem.class, turnItemKey);
				// get key for user of the turn item, and then get the user
				String ownerKeyString = turnItem.getOwnerKeyString();
				Key ownerKey = KeyFactory.stringToKey(ownerKeyString);
				User user = pm.getObjectById(User.class, ownerKey);
				
				BadgeAwarded SocialiteBadge = new BadgeAwarded();	// place holder
				user.addBadge(SocialiteBadge);
			}
		}
	}
}

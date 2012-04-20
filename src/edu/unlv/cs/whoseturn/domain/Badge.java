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
	 * Awards the relevant badge to the user.
	 * @param turn The turn to check.
	 */
	public void Jackass(Turn turn){
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
	 * Awards the relevant badges to the users.
	 * @param turn The turn to check.
	 */
	public void TeamCheater(Turn turn){
		;
	}
	
	/**
	 * Checks to see if the user was selected or not out of a group of 4.
	 * Awards the relevant badges to the users.
	 * @param turn The turn to check.
	 */
	public void CornerStone(Turn turn){
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
					// Don't Cross the Streams badge
					;				
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the user was selected or not out of a group of 5.
	 * Awards the relevant badges to the users.
	 * @param turn The turn to check.
	 */
	public void HumanSacrifice(Turn turn){
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
					// Not the Thumb badge
					;				
			}
		}
		pm.close();
	}
	
	/**
	 * Checks to see if the user was selected or not out of a group of 6.
	 * Awards the relevant badges to the users.
	 * @param turn The turn to check.
	 */
	public void SixMinuteAbs(Turn turn){
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
	 * Awards the relevant badges to the users.
	 * @param turn The turn to check.
	 */
	public void CrappedOut(Turn turn){
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
	 * Awards the relevant badges to the users.
	 * @param turn The turn to check.
	 */
	public void SnowWhite(Turn turn){
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
			if (turn.getNumberOfUsers() == 4){
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
	 * Awards the relevant badges to the users.
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
	 * Checks to see if the user is Chris Jones and awards the StormShadow badge if true.
	 * @param user
	 */
	public void StormShadow(User user){
		if (user.getUsername().equals("Chris Jones")){
			BadgeAwarded badge = new BadgeAwarded();
			user.addBadge(badge);
		}
	}
	
	/**
	 * Checks to see if the user is Matthew Sowders and awards the MythBusters badge if true.
	 * @param user
	 */
	public void MythBusters(User user){
		if (user.getUsername().equals("Matthew Sowders")){
			BadgeAwarded badge = new BadgeAwarded();
			user.addBadge(badge);
		}
	}
}

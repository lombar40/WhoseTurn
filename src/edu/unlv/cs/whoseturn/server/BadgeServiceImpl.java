package edu.unlv.cs.whoseturn.server;

import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.unlv.cs.whoseturn.client.BadgeService;
import edu.unlv.cs.whoseturn.domain.BadgeAwarded;
import edu.unlv.cs.whoseturn.domain.PMF;
import edu.unlv.cs.whoseturn.domain.Turn;
import edu.unlv.cs.whoseturn.domain.TurnItem;
import edu.unlv.cs.whoseturn.domain.User;

/**
 * The badge service, used to go through the badge checks and reward users.
 */
public class BadgeServiceImpl extends RemoteServiceServlet implements BadgeService {

	/**
	 * Allows the class to be serialized.
	 */
	private static final long serialVersionUID = 3341571143301810951L;
	
	/**
	 * Persistence Manager, used for CRUD with the db.
	 */
	PersistenceManager pm = PMF.get().getPersistenceManager();

	@Override
	public void Jackass(Turn turn) {
		Set<String> turn_items = turn.getTurnItems();

		// get the keys of the turn items
		for (String turn_key : turn_items) {
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the
			// user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			Set<String> badgeSet = user.getBadges();

			// Jackass badge check
			if (turn.getNumberOfUsers() == 1) {
				for (int i = 0; i < badgeSet.size(); i++) {
					// get key for the BadgeAwarded entity and retrieve the
					// object
					Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
					BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

					if (badge.getBadgeId().equals(1000)) {
						badge.increaseBadgeCount();
						break;
					}
				}
			}
		}

		pm.close();
	}

	@Override
	public void TeamCheater(Turn turn) {
		Set<String> turn_items = turn.getTurnItems();
		Integer user_count = turn.getNumberOfUsers();
		Integer selected_count = 0;
		
		// get the keys of the turn items
		for (String turn_key : turn_items) {
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			
			if (turn_item.getSelected()){
				selected_count++;
			}
		}
			
		// TeamCheater badge check: see if everyone in the turn was selected
		if (selected_count == user_count){
			// award the badge to everyone in the turn
			for (String turn_key : turn_items) {
				Key turnItemKey = KeyFactory.stringToKey(turn_key);
				TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
				
				// get the key of the user who owns this turn item and then get the user
				Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
				User user = pm.getObjectById(User.class, ownerKey);
				Set<String> badgeSet = user.getBadges();
				
				for (int i = 0; i < badgeSet.size(); i++) {
					// get key for the BadgeAwarded entity and retrieve the object
					Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
					BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

					if (badge.getBadgeId().equals(1019)) {
						badge.increaseBadgeCount();
						break;
					}
				}
			}
		}
		
		pm.close();			
	}
	
	@Override
	public void CornerStone(Turn turn) {
		Set<String> turn_items = turn.getTurnItems();

		// get the keys of the turn items
		for (String turn_key : turn_items) {
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the
			// user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			Set<String> badgeSet = user.getBadges();

			// Badge Check
			if (turn.getNumberOfUsers() == 4) {
				// Corner Stone badge
				if (turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the
						// object
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1001)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
				// Don't Cross The Streams badge
				if (!turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the object.
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1002)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
			}
		}

		pm.close();
	}

	@Override
	public void HumanSacrifice(Turn turn) {
		Set<String> turn_items = turn.getTurnItems();

		// get the keys of the turn items
		for (String turn_key : turn_items) {
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			
			// Get the key of the user who owns this turn item and then get the user.
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			Set<String> badgeSet = user.getBadges();

			// Badge Check
			if (turn.getNumberOfUsers() == 5) {
				// Human Sacrifice badge
				if (turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the
						// object
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1003)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
				// Not The Thumb badge
				if (!turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the
						// object
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1004)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
			}
		}

		pm.close();
	}

	@Override
	public void SixMinuteAbs(Turn turn) {
		Set<String> turn_items = turn.getTurnItems();

		// get the keys of the turn items
		for (String turn_key : turn_items) {
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the
			// user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			Set<String> badgeSet = user.getBadges();

			// Badge Check
			if (turn.getNumberOfUsers() == 6) {
				// Six Minute Abs badge
				if (turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the object.
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1005)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
				// Pick Up Sticks badge
				if (!turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the object.
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1006)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
			}
		}

		pm.close();
	}

	@Override
	public void CrappedOut(Turn turn) {
		Set<String> turn_items = turn.getTurnItems();

		// get the keys of the turn items
		for (String turn_key : turn_items) {
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the
			// user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			Set<String> badgeSet = user.getBadges();

			// Badge Check
			if (turn.getNumberOfUsers() == 7) {
				// Crapped Out badge
				if (turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the object.
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1007)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
				// Lucky No. 7 badge
				if (!turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the
						// object
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1008)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
			}
		}

		pm.close();
	}

	@Override
	public void SnowWhite(Turn turn) {
		Set<String> turn_items = turn.getTurnItems();

		// get the keys of the turn items
		for (String turn_key : turn_items) {
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item and then get the
			// user
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			Set<String> badgeSet = user.getBadges();

			// Badge Check
			if (turn.getNumberOfUsers() == 8) {
				// Snow White badge
				if (turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the
						// object
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1009)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
				// Dwarf badge
				if (!turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the
						// object
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1010)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
			}
		}

		pm.close();
	}

	@Override
	public void FML(Turn turn) {
		Set<String> turn_items = turn.getTurnItems();

		// get the keys of the turn items
		for (String turn_key : turn_items) {
			Key turnItemKey = KeyFactory.stringToKey(turn_key);
			TurnItem turn_item = pm.getObjectById(TurnItem.class, turnItemKey);
			// get the key of the user who owns this turn item
			Key ownerKey = KeyFactory.stringToKey(turn_item.getOwnerKeyString());
			User user = pm.getObjectById(User.class, ownerKey);
			Set<String> badgeSet = user.getBadges();

			// Badge Check
			if (turn.getNumberOfUsers() > 8) {
				// FML badge
				if (turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the
						// object
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1011)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
				// Statistically Speaking badge
				if (!turn_item.getSelected()) {
					for (int i = 0; i < badgeSet.size(); i++) {
						// get key for the BadgeAwarded entity and retrieve the
						// object
						Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
						BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

						if (badge.getBadgeId().equals(1012)) {
							badge.increaseBadgeCount();
							break;
						}
					}
				}
			}
		}

		pm.close();
	}

	@Override
	public void Saint(User user) {
		Integer countTurns = user.getTurnItems().size();
		Set<String> badgeSet = user.getBadges();
		BadgeAwarded userSaintBadge = null;
		boolean noLies = true;

		if (countTurns == 50) {
			for (int i = 0; i < badgeSet.size(); i++) {
				// get key for the BadgeAwarded entity and retrieve the object
				Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
				BadgeAwarded userBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);
				
				if (userBadge.getBadgeId().equals(1013)){
					userSaintBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);
				}
				
				// check if user does not have any liar badges (Jackass or TeamCheater)
				if (userBadge.getBadgeId().equals(1000) || userBadge.getBadgeId().equals(1019)) {
					if (!userBadge.getCount().equals(0)){
						noLies = false;
					}
				}
			}
		}
		
		if (noLies && userSaintBadge != null && userSaintBadge.getCount() == 0){
			userSaintBadge.increaseBadgeCount();
		}
		
		pm.close();
	}

	@Override
	public void Socialite(Turn turn) {
		Integer number_of_users = turn.getNumberOfUsers();

		if (number_of_users > 10) {
			for (String turnItemKeyString : turn.getTurnItems()) {
				// get key for the TurnItem and retrieve the object
				Key turnItemKey = KeyFactory.stringToKey(turnItemKeyString);
				TurnItem turnItem = pm.getObjectById(TurnItem.class, turnItemKey);
				// get key for user of the turn item, and then get the user
				String ownerKeyString = turnItem.getOwnerKeyString();
				Key ownerKey = KeyFactory.stringToKey(ownerKeyString);
				User user = pm.getObjectById(User.class, ownerKey);
				Set<String> badgeSet = user.getBadges();

				for (int i = 0; i < badgeSet.size(); i++) {
					// get key for the BadgeAwarded entity and retrieve the object
					Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
					BadgeAwarded badge = pm.getObjectById(BadgeAwarded.class, badgeKey);

					if (badge.getBadgeId().equals(1014) && badge.getCount() == 0) {
						badge.increaseBadgeCount();
						break;
					}
				}
			}
		}
		
		pm.close();
	}

	@Override
	public void Rookie(User user){
		Integer number_of_turns = user.getTurnItems().size();
		
		if (number_of_turns == 10){
			Set<String> badgeSet = user.getBadges();
			
			for (int i = 0; i < badgeSet.size(); i++){
				// get key for the BadgeAwarded entity and retrieve the object
				Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
				BadgeAwarded userBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);
				
				if (userBadge.getBadgeId().equals(1015) && userBadge.getCount() == 0){
					userBadge.increaseBadgeCount();
					break;
				}
			}	
		}
		
		pm.close();
	}
	
	@Override
	public void Veteran(User user){
		Integer number_of_turns = user.getTurnItems().size();
		
		if (number_of_turns == 100){
			Set<String> badgeSet = user.getBadges();
			
			for (int i = 0; i < badgeSet.size(); i++){
				// get key for the BadgeAwarded entity and retrieve the object
				Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
				BadgeAwarded userBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);
				
				if (userBadge.getBadgeId().equals(1016) && userBadge.getCount() == 0){
					userBadge.increaseBadgeCount();
					break;
				}
			}	
		}
		
		pm.close();
	}
	
	@Override
	public void Elite(User user){
		Integer number_of_turns = user.getTurnItems().size();
		
		if (number_of_turns == 250){
			Set<String> badgeSet = user.getBadges();
			
			for (int i = 0; i < badgeSet.size(); i++){
				// get key for the BadgeAwarded entity and retrieve the object
				Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
				BadgeAwarded userBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);
				
				if (userBadge.getBadgeId().equals(1017) && userBadge.getCount() == 0){
					userBadge.increaseBadgeCount();
					break;
				}
			}	
		}
		
		pm.close();
	}
	
	@Override
	public void WhoseTurnMaster(User user){
		Set<String> badgeSet = user.getBadges();
		BadgeAwarded masterBadge = null;
		boolean hasEveryBadge = true;
		
		for (int i = 0; i < badgeSet.size(); i++){
			// get key for the BadgeAwarded entity and retrieve the object
			Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
			BadgeAwarded userBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);
			
			if (userBadge.getBadgeId().equals(1018)){
				masterBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);
			}
			
			if (userBadge.getCount() == 0 
					&& !(userBadge.getBadgeId().equals(1018) 
							|| userBadge.getBadgeId().equals(1020) 
							|| userBadge.getBadgeId().equals(1021))){
				hasEveryBadge = false;
			}
		}
		
		if (hasEveryBadge && masterBadge != null && masterBadge.getCount() == 0){
			masterBadge.increaseBadgeCount();
		}
		
		pm.close();
	}

	@Override
	public void StormShadow(User user) {
		Set<String> badgeSet = user.getBadges();

		if (user.getUsername().equals("ChrisJones")) {
			for (int i = 0; i < badgeSet.size(); i++) {
				// get key for the BadgeAwarded entity and retrieve the object.
				Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
				BadgeAwarded userBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);

				if (userBadge.getBadgeId().equals(1020) && userBadge.getCount() == 0) {
					userBadge.increaseBadgeCount();
					break;
				}
			}
		}
		
		pm.close();
	}

	@Override
	public void MythBusters(User user) {
		Set<String> badgeSet = user.getBadges();
		if (user.getUsername().equals("MatthewSowders")) {
			for (int i = 0; i < badgeSet.size(); i++) {
				// get key for the BadgeAwarded entity and retrieve the object
				Key badgeKey = KeyFactory.stringToKey(badgeSet.iterator().next());
				BadgeAwarded userBadge = pm.getObjectById(BadgeAwarded.class, badgeKey);

				if (userBadge.getBadgeId().equals(1021) && userBadge.getCount() == 0) {
					userBadge.increaseBadgeCount();
					break;
				}
			}
		}
		
		pm.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void calculateBadges(Turn turn) {
		// Retrieve a list of all users in the database for badge calculation.
		Query query = pm.newQuery(edu.unlv.cs.whoseturn.domain.User.class);
		List<edu.unlv.cs.whoseturn.domain.User> userList;

		try {
			userList = (List<edu.unlv.cs.whoseturn.domain.User>) query.execute();
		} finally {
			query.closeAll();
			pm.close();
		}

		// Initiate badge calculation based on the turn submitted.
		Jackass(turn);
		TeamCheater(turn);
		CornerStone(turn);
		HumanSacrifice(turn);
		SixMinuteAbs(turn);
		CrappedOut(turn);
		SnowWhite(turn);
		FML(turn);
		Socialite(turn);

		// Initiate badge calculation for the users.
		for (User user : userList) {
			Saint(user);
			Rookie(user);
			Veteran(user);
			Elite(user);
			WhoseTurnMaster(user);
			StormShadow(user);
			MythBusters(user);
		}
	}

}
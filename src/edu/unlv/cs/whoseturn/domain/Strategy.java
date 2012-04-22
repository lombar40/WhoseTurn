package edu.unlv.cs.whoseturn.domain;

import java.util.Date;
import java.util.Random;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class Strategy {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String keyString;
	
	@Persistent
    private String strategyName;

	@Persistent
    private Boolean deleted;

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * findDriver is called from another function located in a service call on the client layer
	 * @param a list of users
	 * @param category 
	 * @return a String which will represent the selected driver of Whose Turn
	 */
	public void findDriver(List<User> users, Category category){
		
		User driver;
		String strategy;
		strategy = category.getStrategyKeyString();
		
		switch (strategy){
			case 'leastRecentlyGone':
				driver = leastRecentlyGone(users, category);
				break;
			case 'lowestRatio':
				driver = lowestRatio(users, category);
				break;
			default:
				driver = chooseRandomUser(users);
				break;
		}
	return driver;
	}
	/**
	 * Algorithm which chooses a user based explicitly on
	 * the amount of times they were selected/turnItems; the user with the least ratio
	 * will be chosen to handle all driving responsibilities
	 * 
	 * @param a list of users, as well as a category are passed into the lowestRatio
	 *        method to handle and retrieve the amount of times the user participated in
	 *        such a category.
	 * @param category will represent a drive, chips & salsa, or ice cream
	 * @return a User, which then will be used to access the a string representing the user name
	 */
	public User lowestRatio(users, category){
		List<Double> ratioList;
		List<String> turnItemsKeyStrings;
		List<TurnItems> turnItems;
		Integer tempTurnCount;
		Integer tempSelectedCount;
		
		for (int i = 0; i < user.size(); i++){
			tempTurnCount = 0;
			tempSelectedCount = 0;
			turnItemsKeyStrings = user.index(i).getTurnItems;
			
			for (int j = 0; j < turnItemsKeyStrings.size(); j++){
				PersistenceManage pm = PMF.get().getPersistenceManager();
				turnItems.add(pm.getObjectsById(TurnItem.class,
						   KeyFactory.stringToKey(turnItemsKeyStrings.index(j))));
				}
		for (int k = 0; k < turnItems.size(); k++){
			if (turnItems.index(k).getCategoryKeyString.equals(category.getKeyString)){
				tempTurnCount++;
				if (turnItems.index(k).getSelected()){
					tempSelectedCount++;
					}
				}
			}
		ratioList.add((Double)(tempSelectedCount/tempTurnCount));
		pm.close();
		}
		
		Integer index = 0;
		Double tempCurrentRatio = ratioList.index(0);
		Double tempRatio;
		
		for (int i = 1; i < ratioList.size(); i++){
			tempRatio = ratioList.index(i);
			
			if (tempRatio < tempCurrentRatio){
				tempCurrentRatio = tempRatio;
				index = i;
			}
		}
		
		return users.index(i);
		}
	
	/**
	 * Algorithm which chooses a user based explicitly on turnDateTime from Turn.java, the user which
	 * has the oldest date will be selected to drive. The Date objects will be compared
	 * using the predefined compareTo method, which determines differences in milliseconds.
	 * Elementary comparisons between a default currentTurnDate and a tempTurnDate, will be used
	 * to determine which of the users has the earliest of Dates once the for loop terminates
	 * 
	 * @param a list of users, as well as a category are passed into the leastRecentlyGone
	 *        method to handle and retrieve the amount of times the user participated in
	 *        such a category.
	 * @param category will represent a drive, chips & salsa, or ice cream
	 * @return a User, which then will be used to access the a string representing the user name
	 */
	public User leastRecentlyGone(users, category){
		List<String> turnItemsKeyStrings;
		List<TurnItems> turnItems;
		List<Date> dateList;
		Date tempTurnDate;
		Date currentTurnDate;
		
		for (int i = 0; i < user.size(); i++){
			tempTurnCount = 0;
			tempSelectedCount = 0;
			turnItemsKeyStrings = user.index(i).getTurnItems;
			
			for (int j = 0; j < turnItemsKeyStrings.size(); j++){
				PersistenceManage pm = PMF.get().getPersistenceManager();
				turnItems.add(pm.getObjectsById(TurnItem.class,
						   KeyFactory.stringToKey(turnItemsKeyStrings.index(j))));
				}
			for (int k = 0; k < turnItems.size(); k++){
				dateList.add(turnItems.index(k).getTurnDate);
				}
			pm.close();
				
		}
		
		Integer index = 0;
		Integer differenceOfDates;
		Date tempCurrentDate = dateList.index(0);
		Date tempTurnDate;
		
		for (int i = 1; i < dateList.size(); i++){
			tempTurnDate = dateList.index(i);
			differenceOfDates = tempCurrentDate.compareTo(tempTurnDate);
			
			if (differenceOfDates < 0 || differencesOfDates == 0){
				tempCurrentDate = tempTurnDate;
				index = i;
			}
		}
		
		return users.index(i);
		}
	
	/**
	 * Algorithm which chooses a user based explicitly on the predefined random generator
	 * The Random object will use the nextInt() method to generate an integer value, which
	 * given a parameter of the the number of users will choose a number in such 0 to n-1 range
	 * 
	 * @param a list of users	
	 * @return a User at the arbitrarily generated index, which then will be used to access a string
	 * representing the user name
	 */
	public User chooseRandomUser(users){
		Random generator = new Random();
		int randomIndex = generator.nextInt(user.size());
		
		return users.index(randomIndex);
		}
}
	
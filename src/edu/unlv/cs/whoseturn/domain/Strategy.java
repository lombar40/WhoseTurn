package edu.unlv.cs.whoseturn.domain;

import java.util.Date;

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

	public void findDriver(List<User> users, Category category){
		
		User driver;
		String strategy;
		
		switch (strategy){
			case 'lowestRatio':
				driver = lowestRatio(users, category);
			case 'leastRecentlyGone':
				driver = driverRatioWithLies(users, category);
			default:
					driver = chooseRandomUser(users, category);
		}
	return driver;
	}
	/**
	 * Algorithm which chooses the user to drive, based explicitly on
	 * the amount of times they drove/gone; the user with the least ratio
	 * will be chosen to handle the driving responsibilities
	 * 
	 *
	 * @param a list of users, as well as a category are passed into the driveRatioOnly
	 *        method to handle and retrieve the amount of times the user participated in
	 *        such a category.
	 * @param category will represent a driving algorithm, based on ratios and/or penalties,
	 * 		  chips & salsa, based on ratios and/penalties, and an ice cream algorithm, based
	 * 		  based on ratios and/or algorithms as well.
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
	
	public User leastRecentlyGone(users, category){
		List<String> turnItemsKeyStrings;
		List<TurnItems> turnItems;
		Integer tempTurnCount;
		Integer tempSelectedCount;
		Date tempTurnDate;
		//Date getTurnDateTime()
		
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
		
		
		
}
	
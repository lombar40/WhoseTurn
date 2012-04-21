package edu.unlv.cs.whoseturn.domain;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.Arrays;
import java.util.Random;

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
	
	public void driveRatioOnly() {
		double[] totalArray;
		double lowestRatio;
		String userToDrive;
		totalArray = new double[numUsersParticipating];
		double dgRatio = User.getTimesDrove() / User.getTimesGone();
		
		for (int i = 0; i < numUsersParticipating; i++){
			totalArray[i] = User.dgRatio;
		}
		Arrays.sort(totalArray);
		lowestRatio = totalArray[0];
		userToDrive = lowestRatio.TurnItem.getOwnerKeyString();
		TurnItem.getSelected() = true;
	}
	
	public void driveRatioWithLies() {
		double[] totalArray;
		double lowestRatio;
		String userToDrive;
		totalArray = new double[numUsersParticipating];
		double dlgRatio = User.getTimesDrove() - User.getLies() / User.getTimesGone();
		
		for (int i = 0; i < numUsersParticipating; i++){
			totalArray[i] = User.dgRatio;
		}
		Arrays.sort(totalArray);
		lowestRatio = totalArray[0];
		userToDrive = lowestRatio.TurnItem.getOwnerKeyString();
		TurnItem.getSelected() = true;
	}
	
	public void chipsSalsaRatioOnly() {
		double[] totalArray;
		
		double lowestRatio;
		String userToDrive;
		totalArray = new double[numUsersParticipating];
		double csgRatio = User.getChipsPurchased() / User.getTimesGone();
		
		for (int i = 0; i < numUsersParticipating; i++){
			totalArray[i] = User.csgRatio;
		}
		Arrays.sort(totalArray);
		lowestRatio = totalArray[0];
		userToDrive = lowestRatio.TurnItem.getOwnerKeyString();
		TurnItem.getSelected() = true;
	}
	
	public void chipsSalsaRatioWithLies() {
		double[] totalArray;
		double lowestRatio;
		String userToDrive;
		totalArray = new double[numUsersParticipating];
		double cslgRatio = User.getChipsPurchased() - User.getLies() / User.getTimesGone();
		
		for (int i = 0; i < numUsersParticipating; i++){
			totalArray[i] = User.cslgRatio;
		}
		Arrays.sort(totalArray);
		lowestRatio = totalArray[0];
		userToDrive = lowestRatio.TurnItem.getOwnerKeyString();
		TurnItem.getSelected() = true;
	}
	
	public void IceCreamRatioOnly() {
		double[] totalArray;
		double lowestRatio;
		String userToDrive;
		totalArray = new double[numUsersParticipating];
		double icgRatio = User.getIceCreamPurchased() / User.getTimesGone();
		
		for (int i = 0; i < numUsersParticipating; i++){
			totalArray[i] = User.icgRatio;
		}
		Arrays.sort(totalArray);
		lowestRatio = totalArray[0];
		userToDrive = lowestRatio.TurnItem.getOwnerKeyString();
		TurnItem.getSelected() = true;
	}
	
	public void IceCreamRatioWithLies() {
		double[] totalArray;
		double lowestRatio;
		String userToDrive;
		totalArray = new double[numUsersParticipating];
		double iclgRatio = User.getIceCreamPurchased() - User.GetLies() / User.getTimesGone();
		
		for (int i = 0; i < numUsersParticipating; i++){
			totalArray[i] = User.iclgRatio;
		}
		Arrays.sort(totalArray);
		lowestRatio = totalArray[0];
		userToDrive = lowestRatio.TurnItem.getOwnerKeyString();
		TurnItem.getSelected() = true;
	}
	
	public void randomUser() {
		String[] totalArray;
		double lowestRatio;
		String userToDrive;
		totalArray = new String[numUsersParticipating];
		
		Random generator = new Random();
		int randomIndex = generator.nextInt(numUsersParticipating);
		
		for (int i = 0; i < numUsersParticipating; i++){
			totalArray[i] = User.iclgRatio;
		}
		
		
		
		Arrays.sort(totalArray);
		lowestRatio = totalArray[0];
		userToDrive = lowestRatio.TurnItem.getOwnerKeyString();
		TurnItem.getSelected() = true;
	}
	
	

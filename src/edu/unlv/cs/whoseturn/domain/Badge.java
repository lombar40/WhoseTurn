package edu.unlv.cs.whoseturn.domain;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// ** List of Badges ** ///////////////////////////////////////
////	BadgeID		Badge Name					Badge Criterion									////		
////	1000		Jackass						User submitted a turn with only himself.		////
////	1001		Corner Stone				Selected out of group of 4						////
////	1002		Don't Cross The Streams		Not selected out of group of 4					////
////	1003		Human Sacrifice				Selected out of group of 5						////
////	1004		Not The Thumb				Not selected out of group of 5					////
////	1005		Six Minute Abs				Selected out of group of 6						////
////	1006		Pick Up Sticks				Not selected out of group of 6					////
////	1007		Crapped Out					Selected out of group of 7						////
////	1008		Lucky No. 7					Not selected out of group of 7					////
////	1009		Snow White					Selected out of group of 8						////
////	1010		Dwarf						Not selected out of group of 8					////
////	1011		FML							Selected out of group of more than 8			////
////	1012		Statistically Speaking		Not selected out of group of more than 8		////
////	1013		Saint						User has no lies for 50 turns.					////
////	1014		Socialite					User is part of a turn with more than 10 people	////
////	1020		StormShadow					User is Chris Jones								////
////	1021		MythBusters					User is Matthew Sowders							////
////																							////
////////////////////////////////////////////////////////////////////////////////////////////////////

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
}

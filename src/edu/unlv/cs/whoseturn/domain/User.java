package edu.unlv.cs.whoseturn.domain;

import java.util.Set;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String keyString;
	
	@Persistent
    private String username;

	@Persistent
    private String email;

	@Persistent
    private Boolean admin;
	
	@Persistent
    private Boolean deleted;

	@Persistent
    private byte[] avatarBlob;
	
	@Persistent
	private Integer penaltyCount;
	
	@Persistent
    private Set<String> turnItems;
	
	@Persistent
	private Set<String> badges;

	public String getKeyString() {
		return keyString;
	}

	// Getters and Setters.
	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public byte[] getAvatarBlob() {
		return avatarBlob;
	}

	public void setAvatarBlob(byte[] avatarBlob) {
		this.avatarBlob = avatarBlob;
	}
	
	public Set<String> getTurnItems() {
		return turnItems;
	}

	public Set<String> getBadges() {
		return badges;
	}

	public void setBadges(Set<String> badges) {
		this.badges = badges;
	}
	
	public void addBadge(BadgeAwarded badge) {
		badges.add(badge.getKeyString());
	}
	
	public void addTurnItem(TurnItem turnItem) {
		turnItems.add(turnItem.getKeyString());
	}
	
	public void setTurnItems(Set<String> turnItems) {
		this.turnItems = turnItems;
	}

	public Integer getPenaltyCount() {
		return penaltyCount;
	}

	public void setPenaltyCount(Integer penaltyCount) {
		this.penaltyCount = penaltyCount;
	}
	
	public void increasePenaltyCount() {
		this.penaltyCount++;
		
	}
}

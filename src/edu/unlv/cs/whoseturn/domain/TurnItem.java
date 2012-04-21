package edu.unlv.cs.whoseturn.domain;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class TurnItem {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String keyString;
	
	@Persistent
    private String turnKeyString;

	@Persistent
    private String ownerKeyString;
	
	@Persistent
	private String categoryKeyString;

	@Persistent
    private Boolean selected;

	@Persistent
    private Integer vote;

	@Persistent
    private Boolean deleted;

	
	// Getters and Setters.
	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public String getTurnKeyString() {
		return turnKeyString;
	}

	public void setTurnKeyString(String turnKeyString) {
		this.turnKeyString = turnKeyString;
	}

	public String getOwnerKeyString() {
		return ownerKeyString;
	}

	public void setOwnerKeyString(String ownerKeyString) {
		this.ownerKeyString = ownerKeyString;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getCategoryKeyString() {
		return categoryKeyString;
	}

	public void setCategoryKeyString(String categoryKeyString) {
		this.categoryKeyString = categoryKeyString;
	}
}

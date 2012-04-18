package edu.unlv.cs.whoseturn.domain;

import java.util.Date;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Turn {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
    private Date turnDateTime;

	@Persistent
    private Key category;
    
	@Persistent
    private Set<Key> turnItems;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Date getTurnDateTime() {
		return turnDateTime;
	}

	public void setTurnDateTime(Date turnDateTime) {
		this.turnDateTime = turnDateTime;
	}

	public Key getCategory() {
		return category;
	}

	public void setCategory(Key category) {
		this.category = category;
	}

	public Set<Key> getTurnItems() {
		return turnItems;
	}

	public void setTurnItems(Set<Key> turnItems) {
		this.turnItems = turnItems;
	}
	
	public void addTurnItem(TurnItem turnItem)
	{
		turnItems.add(turnItem.getKey());
	}
}

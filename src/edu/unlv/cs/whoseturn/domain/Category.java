package edu.unlv.cs.whoseturn.domain;

import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Category {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
    private String name;

	@Persistent
    private Key strategy;

	@Persistent
    private int timeBoundryInHours;

	@Persistent
    private Boolean deleted;
    
	@Persistent
    private Set<Key> turns;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Key getStrategy() {
		return strategy;
	}

	public void setStrategy(Key strategy) {
		this.strategy = strategy;
	}

	public int getTimeBoundryInHours() {
		return timeBoundryInHours;
	}

	public void setTimeBoundryInHours(int timeBoundryInHours) {
		this.timeBoundryInHours = timeBoundryInHours;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Set<Key> getTurns() {
		return turns;
	}

	public void setTurns(Set<Key> turns) {
		this.turns = turns;
	}
	
	public void addTurn(Turn turn)
	{
		turns.add(turn.getKey());
	}
}

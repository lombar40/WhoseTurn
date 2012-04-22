package edu.unlv.cs.whoseturn.domain;

import java.util.Set;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Categories of Whose Turn. E.g. Driving, coffee, ice cream, etc. 
 */
@PersistenceCapable
public class Category {

	/**
	 * The primary key for category.
	 */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String keyString;

	/**
	 * Name of the category.
	 */
	@Persistent
    private String name;

	/**
	 * The keyString to the strategy we are using for the category.
	 */
	@Persistent
    private String strategyKeyString;

	/**
	 * An integer for the number of hours between turns that this can occur. Used to avoid duplicate submissions.
	 */
	@Persistent
    private int timeBoundaryInHours;

	/**
	 * Soft delete status. true for deleted.
	 */
	@Persistent
    private Boolean deleted;
    
	 //TODO JAO What is the purpose of this? Need to add java doc.  
	@Persistent
    private Set<String> turns;

	
	// Getters and Setters.
	/**
	 * Gets the key string.
	 * 
	 * @return The key string.
	 */
	public String getKeyString() {
		return keyString;
	}

	/**
	 * Sets the keystring.
	 * 
	 * @param keyString The key string.
	 */
	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	/**
	 * Gets the name of the category.
	 * 
	 * @return The category name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the category.
	 * 
	 * @param name The category name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the strategy's key string.
	 * 
	 * @return The key string of the strategry.
	 */
	public String getStrategyKeyString() {
		return strategyKeyString;
	}

	/**
	 * Sets teh strategy keyString that is used by this category.
	 * 
	 * @param strategyKeyString The keyString value.
	 */
	public void setStrategyKeyString(String strategyKeyString) {
		this.strategyKeyString = strategyKeyString;
	}

	/**
	 * Gets the time boundary.
	 * 
	 * @return The time boundary.
	 */
	public int getTimeBoundaryInHours() {
		return timeBoundaryInHours;
	}

	/**
	 * Sets the time boundary.
	 * 
	 * @param timeBoundaryInHours The time boundary.
	 */
	public void setTimeBoundaryInHours(int timeBoundaryInHours) {
		this.timeBoundaryInHours = timeBoundaryInHours;
	}

	/**
	 * Gets the deleted state.
	 *  
	 * @return true for deleted.
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * Sets the deleted state.
	 * 
	 * @param deleted true for deleted.
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * Gets the turns.
	 * 
	 * @return The turns.
	 */
	public Set<String> getTurns() {
		return turns;
	}

	/**
	 * Sets the turns.
	 * 
	 * @param turns The turns.
	 */
	public void setTurns(Set<String> turns) {
		this.turns = turns;
	}
	
	/**
	 * Adds a turn.
	 * 
	 * @param turn The turn to add.
	 */
	public void addTurn(Turn turn)
	{
		turns.add(turn.getKeyString());
	}
}

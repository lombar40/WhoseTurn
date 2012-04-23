package edu.unlv.cs.whoseturn.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * The turn domain object. 
 */
//@PersistenceCapable
@PersistenceCapable(identityType=IdentityType.APPLICATION,detachable="true")
public class Turn implements IsSerializable  {

    /**
     * Default constructor. Needed for Serializable.
     */
    public Turn() {
    }

    /**
     * The primary key for the object.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String keyString;

    /**
     * Time stampl.
     */
    @Persistent
    private Date turnDateTime;

    /**
     * Category key string.
     */
    @Persistent
    private String categoryKeyString;

    /**
     * List of turn items.
     */
    @Persistent
    private HashSet<String> turnItems;

    // Getters and Setters.
    /**
     * Gets the key string.
     * 
     * @return The key string.
     */
    public final String getKeyString() {
        return keyString;
    }

    /**
     * Sets the key string. 
     * 
     * @param keyString The key string.
     */
    public final void setKeyString(final String keyString) {
        this.keyString = keyString;
    }

    /**
     * Gets the time stamp.
     * 
     * @return The time stamp.
     */
    public final Date getTurnDateTime() {
        return turnDateTime;
    }

    /**
     * Sets the time stamp.
     * 
     * @param turnDateTime The time stamp.
     */
    public final void setTurnDateTime(final Date turnDateTime) {
        this.turnDateTime = turnDateTime;
    }

    /**
     * Gets the category key string.
     * 
     * @return The category key string.
     */
    public final String getCategoryKeyString() {
        return categoryKeyString;
    }

    /**
     * Sets the category key string.
     * 
     * @param categoryKeyString The category key string.
     */
    public final void setCategoryKeyString(final String categoryKeyString) {
        this.categoryKeyString = categoryKeyString;
    }

    /**
     * Gets the turn items.
     * 
     * @return The turn items.
     */
    public final HashSet<String> getTurnItems() {
        return turnItems;
    }

    /**
     * Sets the turn items.
     * 
     * @param turnItems The turn items.
     */
    public final void setTurnItems(final HashSet<String> turnItems) {
        this.turnItems = turnItems;
    }

    /**
     * Add turn items.
     * 
     * @param turnItem A turn item to add.
     */
    public final void addTurnItem(final TurnItem turnItem) {
        turnItems.add(turnItem.getKeyString());
    }

    /**
     * Get the number of users.
     * 
     * @return The number of users.
     */
    public final Integer getNumberOfUsers() {
        return turnItems.size();
    }

}

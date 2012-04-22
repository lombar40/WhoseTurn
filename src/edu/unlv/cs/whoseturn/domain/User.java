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
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
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
    private String ownerKeyString;

    @Persistent
    private Set<String> turnItems;

    @Persistent
    private Set<String> badges;

    public final String getKeyString() {
        return keyString;
    }

    // Getters and Setters.
    public final void setKeyString(final String keyString) {
        this.keyString = keyString;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(final String username) {
        this.username = username;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(final String email) {
        this.email = email;
    }

    public final Boolean getAdmin() {
        return admin;
    }

    public final void setAdmin(final Boolean admin) {
        this.admin = admin;
    }

    public final Boolean getDeleted() {
        return deleted;
    }

    public final void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    public final byte[] getAvatarBlob() {
        return avatarBlob;
    }

    public final void setAvatarBlob(final byte[] avatarBlob) {
        this.avatarBlob = avatarBlob;
    }

    public final Set<String> getTurnItems() {
        return turnItems;
    }

    public final Set<String> getBadges() {
        return badges;
    }

    public final void setBadges(final Set<String> badges) {
        this.badges = badges;
    }

    public final void addBadge(final BadgeAwarded badge) {
        badges.add(badge.getKeyString());
    }

    public final void addTurnItem(final TurnItem turnItem) {
        turnItems.add(turnItem.getKeyString());
    }

    public final void setTurnItems(final Set<String> turnItems) {
        this.turnItems = turnItems;
    }

    public final Integer getPenaltyCount() {
        return penaltyCount;
    }

    public final void setPenaltyCount(final Integer penaltyCount) {
        this.penaltyCount = penaltyCount;
    }

    public final void increasePenaltyCount() {
        this.penaltyCount++;

    }

    public final String getOwnerKeyString() {
        return ownerKeyString;
    }

    public final void setOwnerKeyString(final String ownerKeyString) {
        this.ownerKeyString = ownerKeyString;
    }
}

package edu.unlv.cs.whoseturn.domain;

import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class User {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
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
	private Set<Key> badges;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
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

	public Set<Key> getBadges() {
		return badges;
	}

	public void setBadges(Set<Key> badges) {
		this.badges = badges;
	}
	
	public void addBadge(BadgeAwarded badge)
	{
		badges.add(badge.getKey());
	}
}

package com.battleshipregistration.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.util.Assert;

public abstract class BaseEntity implements Serializable {

	private int version;

	protected String id;

	private Date timeCreated;

	public BaseEntity() {
		this(UUID.randomUUID());
	}

	public BaseEntity(UUID guid) {
		Assert.notNull(guid, "UUID is required");
		id = guid.toString();
		this.timeCreated = new Date();
	}

	public BaseEntity(String guid) {
		Assert.notNull(guid, "UUID is required");
		id = guid;
		this.timeCreated = new Date();
	}

	public String getId() {
		return id;
	}

	public int hashCode() {
		return getId().hashCode();
	}

	public int getVersion() {
		return version;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

}

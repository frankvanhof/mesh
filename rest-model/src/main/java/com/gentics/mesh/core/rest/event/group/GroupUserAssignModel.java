package com.gentics.mesh.core.rest.event.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshEventModel;
import com.gentics.mesh.core.rest.event.MeshEventModelProperties;
import com.gentics.mesh.core.rest.group.GroupReference;
import com.gentics.mesh.core.rest.user.UserReference;

public class GroupUserAssignModel implements MeshEventModel {

	@JsonUnwrapped
	private final MeshEventModelProperties baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the group that was assigned.")
	private final GroupReference group;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the user that was assigned.")
	private final UserReference user;

	public GroupUserAssignModel(MeshEventModelProperties baseProperties, GroupReference group, UserReference user) {
		this.baseProperties = baseProperties;
		this.group = group;
		this.user = user;
	}

	public GroupReference getGroup() {
		return group;
	}

	public UserReference getUser() {
		return user;
	}

	@Override
	public String getOrigin() {
		return baseProperties.getOrigin();
	}

	@Override
	public MeshEvent getEvent() {
		return baseProperties.getEvent();
	}

	@Override
	public EventCauseInfo getCause() {
		return baseProperties.getCause();
	}

	@Override
	public void setCause(EventCauseInfo cause) {
		baseProperties.setCause(cause);
	}
}

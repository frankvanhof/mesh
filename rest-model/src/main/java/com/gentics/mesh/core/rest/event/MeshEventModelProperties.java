package com.gentics.mesh.core.rest.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.gentics.mesh.core.rest.MeshEvent;

public class MeshEventModelProperties implements MeshEventModel {
	@JsonProperty(required = true)
	@JsonPropertyDescription("Name of the mesh node from which the event originates.")
	private final String origin;

	@JsonProperty(required = false)
	@JsonPropertyDescription("Some events will be caused by another action. This object contains information about the cause of the event.")
	private EventCauseInfo cause;

	@JsonIgnore
	private final MeshEvent event;

	public MeshEventModelProperties(String origin, MeshEvent event) {
		this.origin = origin;
		this.event = event;
	}

	@Override
	public String getOrigin() {
		return origin;
	}

	@Override
	public MeshEvent getEvent() {
		return event;
	}

	@Override
	public EventCauseInfo getCause() {
		return cause;
	}

	@Override
	public void setCause(EventCauseInfo cause) {
		this.cause = cause;
	}
}

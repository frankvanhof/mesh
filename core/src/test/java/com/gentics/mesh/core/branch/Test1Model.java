package com.gentics.mesh.core.branch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.gentics.mesh.Mesh;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshEventModel;
import com.gentics.mesh.core.rest.event.MeshEventModelProperties;

import java.util.Objects;

public class Test1Model {
	@JsonProperty(required = true)
	@JsonPropertyDescription("Name of the mesh node from which the event originates.")
	private String origin;

	@JsonProperty(required = false)
	@JsonPropertyDescription("Some events will be caused by another action. This object contains information about the cause of the event.")
	private EventCauseInfo cause;

	private MeshEvent event;

	public Test1Model() {
	}

	public Test1Model(String origin, MeshEvent event) {
		this.origin = Objects.requireNonNull(origin);
		this.event = Objects.requireNonNull(event);
	}

	public String getOrigin() {
		return origin;
	}

	@JsonIgnore
	public MeshEvent getEvent() {
		return event;
	}

	public EventCauseInfo getCause() {
		return cause;
	}

	public void setCause(EventCauseInfo cause) {
		this.cause = cause;
	}

	public Test1Model setOrigin(String origin) {
		this.origin = origin;
		return this;
	}

	public Test1Model setEvent(MeshEvent event) {
		this.event = event;
		return this;
	}
}

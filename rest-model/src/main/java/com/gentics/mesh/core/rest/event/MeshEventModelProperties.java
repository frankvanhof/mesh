package com.gentics.mesh.core.rest.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.gentics.mesh.Mesh;
import com.gentics.mesh.core.rest.MeshEvent;

import java.util.Objects;

public class MeshEventModelProperties implements MeshEventModel {
	@JsonProperty(required = true)
	@JsonPropertyDescription("Name of the mesh node from which the event originates.")
	private String origin;

	@JsonProperty(required = false)
	@JsonPropertyDescription("Some events will be caused by another action. This object contains information about the cause of the event.")
	private EventCauseInfo cause;

	private MeshEvent event;

	public MeshEventModelProperties() {
	}

	public MeshEventModelProperties(String origin, MeshEvent event) {
		this.origin = Objects.requireNonNull(origin);
		this.event = Objects.requireNonNull(event);
	}

	public static MeshEventModelProperties fromCurrentNode(MeshEvent event) {
		return new MeshEventModelProperties(Mesh.mesh().getOptions().getNodeName(), event);
	}

	@Override
	public String getOrigin() {
		return origin;
	}

	@JsonIgnore
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

	public MeshEventModelProperties setOrigin(String origin) {
		this.origin = origin;
		return this;
	}

	public MeshEventModelProperties setEvent(MeshEvent event) {
		this.event = event;
		return this;
	}
}

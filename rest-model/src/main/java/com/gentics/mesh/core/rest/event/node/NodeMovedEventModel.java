package com.gentics.mesh.core.rest.event.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshEventModel;
import com.gentics.mesh.core.rest.event.MeshEventModelProperties;
import com.gentics.mesh.core.rest.user.NodeReference;

public class NodeMovedEventModel implements MeshEventModel {

	@JsonUnwrapped
	private final MeshEventModelProperties baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference of the source node which was moved.")
	private NodeReference source;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference of the target node into which the source node was moved.")
	private NodeReference target;

	public NodeMovedEventModel(MeshEventModelProperties baseProperties, NodeReference source, NodeReference target) {
		this.baseProperties = baseProperties;
		this.source = source;
		this.target = target;
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

	public NodeReference getSource() {
		return source;
	}

	public void setSource(NodeReference source) {
		this.source = source;
	}

	public NodeReference getTarget() {
		return target;
	}

	public void setTarget(NodeReference target) {
		this.target = target;
	}

}

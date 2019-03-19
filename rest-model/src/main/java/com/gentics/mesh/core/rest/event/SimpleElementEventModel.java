package com.gentics.mesh.core.rest.event;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;

public class SimpleElementEventModel implements MeshElementEventModel {

	@JsonUnwrapped
	private final MeshEventModelProperties baseProperties;
	@JsonUnwrapped
	private final MeshElementEventProperties elementProperties;

	public SimpleElementEventModel(MeshEventModelProperties baseProperties, MeshElementEventProperties elementProperties) {
		this.baseProperties = baseProperties;
		this.elementProperties = elementProperties;
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

	@Override
	public String getUuid() {
		return elementProperties.getUuid();
	}

	@Override
	public String getName() {
		return elementProperties.getName();
	}

	@Override
	public void setName(String name) {
		elementProperties.setName(name);
	}

	@Override
	public void setUuid(String uuid) {
		elementProperties.setUuid(uuid);
	}
}

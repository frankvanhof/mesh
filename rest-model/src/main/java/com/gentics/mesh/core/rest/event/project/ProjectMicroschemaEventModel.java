package com.gentics.mesh.core.rest.event.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.ProjectEvent;
import com.gentics.mesh.core.rest.event.ProjectEventModelProperties;
import com.gentics.mesh.core.rest.project.ProjectReference;
import com.gentics.mesh.core.rest.schema.MicroschemaReference;
import com.gentics.mesh.core.rest.schema.impl.MicroschemaReferenceImpl;

public class ProjectMicroschemaEventModel implements ProjectEvent {

	@JsonUnwrapped
	private final ProjectEventModelProperties baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the microschema.")
	@JsonDeserialize(as = MicroschemaReferenceImpl.class)
	private MicroschemaReference microschema;

	public ProjectMicroschemaEventModel(ProjectEventModelProperties baseProperties, MicroschemaReference microschema) {
		this.baseProperties = baseProperties;
		this.microschema = microschema;
	}

	public MicroschemaReference getMicroschema() {
		return microschema;
	}

	public void setMicroschema(MicroschemaReference microschema) {
		this.microschema = microschema;
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
	public ProjectReference getProject() {
		return baseProperties.getProject();
	}

	@Override
	public void setProject(ProjectReference project) {
		baseProperties.setProject(project);
	}
}

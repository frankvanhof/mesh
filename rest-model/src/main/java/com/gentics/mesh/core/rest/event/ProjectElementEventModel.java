package com.gentics.mesh.core.rest.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.project.ProjectReference;

public class ProjectElementEventModel implements MeshProjectElementEventModel {

	@JsonUnwrapped
	private final SimpleElementEventModel baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the project to which the element belonged.")
	private ProjectReference project;


	public ProjectElementEventModel(SimpleElementEventModel baseProperties, ProjectReference project) {
		this.baseProperties = baseProperties;
		this.project = project;
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
		return baseProperties.getUuid();
	}

	@Override
	public String getName() {
		return baseProperties.getName();
	}

	@Override
	public void setName(String name) {
		baseProperties.setName(name);
	}

	@Override
	public void setUuid(String uuid) {
		baseProperties.setUuid(uuid);
	}

	@Override
	public ProjectReference getProject() {
		return project;
	}

	@Override
	public void setProject(ProjectReference project) {
		this.project = project;
	}
}

package com.gentics.mesh.core.rest.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.project.ProjectReference;

import java.util.Objects;

public class ProjectEventModelProperties implements ProjectEvent {
	@JsonUnwrapped
	private MeshEventModelProperties baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the project to which the element belonged.")
	private ProjectReference project;


	public ProjectEventModelProperties() {
		System.out.println("ladskj");
	}

	public ProjectEventModelProperties(ProjectReference project) {
		this.project = Objects.requireNonNull(project);
	}

	public ProjectEventModelProperties(MeshEventModelProperties baseProperties, ProjectReference project) {
		this.baseProperties = Objects.requireNonNull(baseProperties);
		this.project = Objects.requireNonNull(project);
	}

	@Override
	public String getOrigin() {
		return baseProperties.getOrigin();
	}

	@JsonIgnore
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
		return project;
	}

	@Override
	public void setProject(ProjectReference project) {
		this.project = project;
	}
}

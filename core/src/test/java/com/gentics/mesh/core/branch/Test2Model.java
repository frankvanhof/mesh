package com.gentics.mesh.core.branch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshEventModelProperties;
import com.gentics.mesh.core.rest.event.ProjectEvent;
import com.gentics.mesh.core.rest.project.ProjectReference;

import java.util.Objects;

public class Test2Model  {
	@JsonUnwrapped
	private Test1Model baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the project to which the element belonged.")
	private ProjectReference project;


	public Test2Model() {
		System.out.println("ladskj");
	}


	public String getOrigin() {
		return baseProperties.getOrigin();
	}

	@JsonIgnore
	public MeshEvent getEvent() {
		return baseProperties.getEvent();
	}

	@JsonIgnore
	public EventCauseInfo getCause() {
		return baseProperties.getCause();
	}

	@JsonIgnore
	public void setCause(EventCauseInfo cause) {
		baseProperties.setCause(cause);
	}

	public ProjectReference getProject() {
		return project;
	}

	public void setProject(ProjectReference project) {
		this.project = project;
	}
}
